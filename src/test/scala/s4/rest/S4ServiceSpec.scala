package s4.rest

import s4.domain.TestDB

import org.specs2.Specification //.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._
import MediaTypes.`application/json`
import spray.httpx.SprayJsonSupport.{ sprayJsonMarshaller, sprayJsonUnmarshaller }

@RunWith(classOf[JUnitRunner]) // Only required if testing from within Eclipse
class S4ServiceSpec extends Specification with Specs2RouteTest with S4Service with TestDB {
  def actorRefFactory = system

  def is = {
    var string2NR = () // shadow implicit conversion from Spray Directives trait
    sequential^
    "Template Project REST Specification" ^
      p ^
      "The server should" ^
      "Respond with greeting on root path" ! serverRunning ^
      p ^
      "For PERSON json objects" ^
      "Return an empty list if there are no entities" ! getEmptyPersonList ^
      "Create a new entity" ! createPerson ^
      "Return a non-empty list if there some entities" ! getNonEmptyPersonList ^
      "Read existing" ! todo ^
      "Update existing" ! todo ^
      "Delete existing" ! todo ^
      "Handle missing fields" ! todo ^
      "Handle invalid fields" ! todo ^
      "Return error if the entity does not exist" ! todo ^
      end
  }

  def serverRunning = Get() ~> s4Route ~> check {
    entityAs[String] must contain("S4")
  }

  import s4.domain._
  import JsonImplicits._
  val jsonPerson = """{
      "id": 0, 
      "fname": "Jack",
      "lname": "InABox"}"""
    
  val expectedPerson = Person(fname = "Jack", lname = "InABox", id = Some(1))

  def getEmptyPersonList = {
    Get("/persons") ~> s4Route ~> check {
      entityAs[List[Person]] === List()
      ok
    } 
  }
  
  def getNonEmptyPersonList = {
    Get("/persons") ~> s4Route ~> check {
      entityAs[List[Person]].length > 0
      ok
    }
  }

  def createPerson = {
    Post("/person", HttpBody(`application/json`, jsonPerson)) ~> s4Route ~> check {
      entityAs[Person] === expectedPerson
      ok
    }
  }
}
  /*
  "S4" should {

    "return a greeting for GET requests to the root path" in {
      Get() ~> s4Route ~> check {
        entityAs[String] must contain("S4")
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/stuffthatdoesnotexist") ~> s4Route ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(s4Route) ~> check {
        status === MethodNotAllowed
        entityAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }

    import s4.domain._
    import JsonImplicits._
    val jsonPerson = """{
      "id": 0, 
      "fname": "Jack"}"""
    val expectedPerson = Person(fname = "Jack", id = Some(1))

    "add a client" in {
      Post("/client", HttpBody(`application/json`, jsonPerson)) ~> s4Route ~> check {
        entityAs[Person] === expectedPerson
      }
    }
  }*/


/*
     override def is = {
       var string2NR = () // shadow implicit conversion from Directives trait
       
         "This is a specification to check the 'Hello world' string"                 ^
                                                                                    p^
        "The 'Hello world' string should"                                          ^
          "contain 11 characters"                                                   ! e1^
          "start with 'Hello'"                                                      ! e2^
          "end with 'world'"                                                        ! e3^
                                                                             end
  }

      def e1 = "Hello world" must have size(11)
      def e2 = "Hello world" must startWith("Hello")
      def e3 = "Hello world" must endWith("world")
    
def serverRunning = Get() ~> s4Route ~> check {
        entityAs[String] must contain("S4")
      }
 */
