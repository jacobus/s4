package s4.rest

import akka.actor.{Props, ActorSystem}
import spray.can.server.HttpServer
import spray.io._
import akka.actor.actorRef2Scala
//import S4.rest.ApointServiceActor


object Boot extends App {
  // we need an ActorSystem to host our application in
  val system = ActorSystem("S4")

  // every spray-can HttpServer (and HttpClient) needs an IOBridge for low-level network IO
  // (but several servers and/or clients can share one)
  //val ioBridge = new IOBridge(system).start()
  private val ioBridge = IOExtension(system).ioBridge()
  // create and start our service actor
  val service = system.actorOf(Props[S4ServiceActor], "s4-service")

  // create and start the spray-can HttpServer, telling it that
  // we want requests to be handled by our singleton service actor
  val httpServer = system.actorOf(
    Props(new HttpServer(ioBridge, SingletonHandler(service))),
    name = "http-server"
  )

  // a running HttpServer can be bound, unbound and rebound
  // initially to need to tell it where to bind to
  httpServer ! HttpServer.Bind("localhost", 8080)
}
