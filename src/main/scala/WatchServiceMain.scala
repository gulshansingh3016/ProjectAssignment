
import akka.actor.{ActorSystem, Props}
import java.nio.file._
import java.util.concurrent.TimeUnit

object WatchServiceMain extends App {
  val system = ActorSystem("WatchFsSystem")
  system.log.info("Started")
  val fsActor = system.actorOf(Props[DirectoryMonitorActor], "fileSystem")
  fsActor ! MonitorDir(Paths get "/home/knoldus/DirectoryMoniterAkka")
  TimeUnit.SECONDS.sleep(60)
  system.terminate()
}
