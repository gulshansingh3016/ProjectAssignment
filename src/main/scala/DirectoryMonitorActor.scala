import WatchServiceMain.system
import akka.actor.{Actor, ActorLogging, Props}
import akka.event.LoggingReceive


class DirectoryMonitorActor extends Actor with ActorLogging {

  val actorMaster = system.actorOf(Props[WatchServiceMaster])
  def receive: Receive = LoggingReceive {
    case MonitorDir(path) =>
      //watchServiceTask watchRecursively path
      log.info("Get path", path)
      actorMaster ! GetPath(path)
  }
}