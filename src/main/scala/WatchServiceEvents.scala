import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.event.Logging

import java.nio.file.StandardWatchEventKinds._
import java.nio.file._
import java.nio.file.attribute.BasicFileAttributes
import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

case class StartWorker(path: Path)
case class GetPath(path:Path)

class WatchServiceMaster extends Actor with ActorLogging {

  override def receive: Receive = {
    case GetPath(path) => val workerChild = context.actorOf(Props[WorkerClass])
      println("In a master", path)
      workerChild ! StartWorker(path)
  }
}

class WorkerClass extends Actor with ActorLogging{
  class WatchServiceEvents(notifyActor: ActorRef) extends Runnable {
    private val watchService = FileSystems.getDefault.newWatchService()

    def watchRecursively(root: Path) {
      watch(root)
      Files.walkFileTree(root, new SimpleFileVisitor[Path] {
        override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes) = {
          watch(dir)
          FileVisitResult.CONTINUE
        }
      })
    }

    private def watch(path: Path) =
      path.register(watchService, ENTRY_CREATE, ENTRY_DELETE)

    def run() {
      try {
        println("Waiting for file system events...")
        while (!Thread.currentThread().isInterrupted) {
          val key = watchService.take()
          key.pollEvents() foreach {
            event =>
              val relativePath = event.context().asInstanceOf[Path]
              val path = key.watchable().asInstanceOf[Path].resolve(relativePath)
              event.kind() match {
                case ENTRY_CREATE =>
                  if (path.toFile.isDirectory) {
                    watchRecursively(path)
                  }
                  notifyActor ! Created(path.toFile)
                case ENTRY_DELETE =>
                  notifyActor ! Deleted(path.toFile)

                case x =>
                  println(s"Unknown event $x")
              }
          }
          key.reset()
        }
      } catch {
        case e: InterruptedException =>
          println("Interrupting, bye!")
      } finally {
        watchService.close()
      }
    }

  }
  val log1 = Logging(context.system, this)
  val watchServiceTask = new WatchServiceEvents(self)
  val watchThread = new Thread(watchServiceTask, "WatchService")
  override def preStart() {
    watchThread.setDaemon(true)
    watchThread.start()
  }

  override def postStop() {
    watchThread.interrupt()
  }

  override def receive: Receive = {
    case StartWorker(path)=>
      watchServiceTask watchRecursively path
    case Created(file) => println(s"File is created $file")
    case Deleted(fileOrDir) => println(s"File is deleted $fileOrDir ")
  }
}
