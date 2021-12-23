//import akka.actor.{ActorSystem, Props}
//import akka.http.impl.engine.http2.hpack.Http2HeaderParsing.Status
//import akka.http.impl.util.JavaMapping.StatusCode
//import akka.http.javadsl.server.Route
//import akka.http.scaladsl.Http
//import akka.http.scaladsl.model.HttpResponse
//import akka.http.scaladsl.server.Directives._
//import akka.stream.impl.Stages.DefaultAttributes.ask
//
//import java.nio.file.Paths
//import java.util.concurrent.TimeUnit
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.concurrent.Future
//
//object RoutingDirectory {
//  def main(args: Array[String]): Unit = {
//    implicit val act = ActorSystem("act1")
//   // parent actor  ==> val actor = act.actorOf(Props[DirectoryMonitorActor])
//
//    //    val route =
//    //      concat(
//    //        path("tmp") {
//    //          listDirectoryContents("/home/knoldus/DiretoryWatch")
//    //        },
//    //        path("custom") {
//    //          // implement your custom renderer here
//    //          val renderer = new DirectoryRenderer {
//    //            override def marshaller(renderVanityFooter: Boolean): ToEntityMarshaller[DirectoryListing] = ???
//    //          }
//    //          listDirectoryContents("/home/knoldus/DiretoryWatch")(renderer)
//    //        }
//    //      )
//    //  }
//
//    def routeFunction: Future[HttpResponse]= {
//      ask(case response => HttpResponse(write))
//      val system = ActorSystem("WatchFsSystem")
//      system.log.info("Started")
//      val fsActor = system.actorOf(Props[DirectoryMonitorActor], "fileSystem")
//      fsActor ! MonitorDir(Paths get "/home/knoldus/DirectoryMoniterAkka")
//      TimeUnit.SECONDS.sleep(60)
//      system.terminate()
//    }
// val route : Route=
//   pathPrefix("examples") {
//      get {
//        pathEnd {
//          routeFunction
//          //         ask parent actor to watch
//          //        system.log.info("Started")
//          //        val fsActor = system.actorOf(Props[DirectoryMonitorActor], "fileSystem")
//          //        fsActor ! MonitorDir(Paths get "/home/knoldus/DirectoryMoniterAkka")
//          //        getFromResourceDirectory("/home/knoldus/DiretoryWatch")
//          //        //TimeUnit.SECONDS.sleep(60)
//          //        //system.shutdown
//        }
//      }
//
//    }
//    val bindingFuture = Http().bindAndHandle(route, 8080)
//    println(bindingFuture)
//
//  }
//}
//
