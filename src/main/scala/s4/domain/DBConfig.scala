package s4.domain

trait DBConfig {
  def m: Model
}

//import scala.slick.driver.H2Driver
//import scala.slick.session.{ Database, Session }
//import scala.slick.driver.JdbcProfile
import scala.slick.driver.H2Driver
import scala.slick.driver.PostgresDriver
import scala.slick.jdbc.JdbcBackend.Database

trait TestDB extends DBConfig {
  val m = new Model("H2", new DAL(H2Driver),
    Database.forURL("jdbc:h2:mem:servicetestdb", driver = "org.h2.Driver"))
  m.createDB
}

trait ProductionDB extends DBConfig {
  val m = new Model("PostgreSQL", new DAL(PostgresDriver),
    Database.forURL("jdbc:postgresql:test:s4",
                           driver="org.postgresql.Driver",
                           user="postgres",
                           password="xxxxx"))
  m.createDB
}
