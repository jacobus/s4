package s4.rest

import java.io.FileOutputStream

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Promise
import scala.language.postfixOps

import akka.actor.Actor
import s4.domain.DBConfig
import s4.domain.Person
import s4.domain.ProductionDB
import spray.http.MediaTypes.{ `text/html` }
import spray.httpx.SprayJsonSupport.sprayJsonMarshaller
import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller
import spray.json.DefaultJsonProtocol
import spray.routing.Directive.pimpApply
import spray.routing.HttpService
import spray.routing.authentication.BasicAuth
import spray.routing.authentication.UserPass
import spray.routing.authentication.UserPassAuthenticator
import spray.routing.authentication.UserPassAuthenticator
import spray.routing.directives.AuthMagnet.fromContextAuthenticator
import spray.routing.directives.CompletionMagnet.fromObject
import spray.routing.directives.FieldDefMagnet.apply

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class S4ServiceActor extends Actor with S4Service with ProductionDB {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(s4Route)
}

object JsonImplicits extends DefaultJsonProtocol {
  implicit val impPerson = jsonFormat3(Person)
}

// this trait defines our service behavior independently from the service actor
trait S4Service extends HttpService { this: DBConfig =>

  //TODO Extend UserProfile class depending on project requirements
  case class UserProfile(name: String)

  def getUserProfile(name: String, password: String): Option[UserProfile] = {
    //TODO Here you should check if this is a valid user on your system and return his profile
    //I'm just creating one a fake one for now on the assumption that only 'bob' exits
    if (name == "bob" && password == "123")
      Some(UserProfile(s"$name"))
    else
      None
  }

  object CustomUserPassAuthenticator extends UserPassAuthenticator[UserProfile] {
    def apply(userPass: Option[UserPass]) = Promise.successful(
      userPass match {
        case Some(UserPass(user, pass)) => {
          getUserProfile(user, pass)
        }
        case _ => None
      }).future
  }

  val jsonRoute = {
    import spray.httpx.SprayJsonSupport.sprayJsonMarshaller
    import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller
    import JsonImplicits._

    get {
      path("") {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>The <b>S4</b> - <i>Slick Spray Scala Stack</i> is running :-)</h1>
              </body>
            </html>
          }
        }
      } ~
        path("index") {
          respondWithMediaType(`text/html`) {
            complete(html.index().toString)
          }
        } ~
        path("index2") {
          respondWithMediaType(`text/html`) {
            complete(html.index2("Spraying some Bootstrap", "Hello Twirl served by Spray").toString)
          }
        }
    } ~
      //          unmatchedPath { ump =>
      //        redirect("bootstrap/%s" + ump, Found)
      //      }
      get {
        //   path("favicon.ico") {
        //    complete(NotFound)
        //  } ~
        path(Rest) { path =>
          getFromResource("bootstrap/%s" format path)
        } ~
          path("file") {
            getFromResource("application.conf")
          }
      } ~
      path("persons") {
        get { ctx =>
          ctx.complete {
            val result: List[Person] = m.getPersons()
            result
          }
        }
      } ~
      path("person") {
        authenticate(BasicAuth(CustomUserPassAuthenticator, "person-security-realm")) { userProfile =>
          post {
            entity(as[Person]) { person =>
              val result: Person = m.addPerson(person)
              complete(result)
            }
          }
        }
      }
  }

  val uploadRoute = {
    import spray.httpx.encoding.{ NoEncoding, Gzip }
    path("upload") {
      post {
        formField('imageupload.as[Array[Byte]]) { file =>
          // import spray.httpx.SprayJsonSupport._
          val fos: FileOutputStream = new FileOutputStream("test.png");
          try {
            fos.write(file);
          } finally {
            fos.close();
          }
          complete {
            "0"
          }
        }
      }
    }
  }

  val s4Route = jsonRoute ~ uploadRoute

}

class B {
  def persons: List[Person] = {
    List()
  }
}
