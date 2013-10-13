package s4.rest

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http

object Main extends App {

  implicit val system = ActorSystem()

  // the handler actor replies to incoming HttpRequests
  val handler = system.actorOf(Props[S4ServiceActor], name = "s4-service")

  IO(Http) ! Http.Bind(handler, interface = "localhost", port = 8080)
}


//import spray.can.server.SprayCanHttpServerApp
//import akka.actor.Props
/*
trait WebApp extends ServerFrontend {//SprayCanHttpServerApp {

  // create and start our service actor
  val service = system.actorOf(Props[MyServiceActor], "my-service")

  // create a new HttpServer using our handler tell it where to bind to
  newHttpServer(service) ! Bind(interface = "localhost", port = 8080)
}

object Boot extends App with WebApp {

}*/