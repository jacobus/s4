package s4.domain

import org.specs2._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner]) // Only required if testing from Eclipse
class DomainAcceptanceSpec extends Specification {
  def is =
    "S4 Model should:" ^
      p ^
      "Return an empty list if asked for all clients"
      "Add a client" ! addPerson ^
      end

  import scala.slick.driver.H2Driver
  import scala.slick.session.{ Database, Session }
  val m = new Model("H2", new DAL(H2Driver),
    Database.forURL("jdbc:h2:mem:testdb", driver = "org.h2.Driver"))
  m.createDB

  def addPerson = m.addPerson(Person("Bob", "TheBuilder")) === (Person(id = Some(1), fname = "Bob", lname = "TheBuilder"))
 // def findByName = m.findByName("Bob") === (Person(id = Some(1), fname = "Bob"))
}
