package s4.domain

import scala.slick.driver.ExtendedProfile
import scala.slick.session.Session
import scala.slick.driver.H2Driver
import scala.slick.driver.SQLiteDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

trait Profile {
  val profile: ExtendedProfile
}

// The data access layer (DAL) is a cake pattern implementation.
// It is an adaptation of the official Slick example set:
// https://github.com/slick/slick-examples (MultiDBCakeExample.scala)
// Understanding that example will help to understand this code.
class DAL(override val profile: ExtendedProfile) extends PersonComponent with Profile {
  import profile.simple._

  val logger: Logger = LoggerFactory.getLogger("s4.domain");
  logger.info("Model class instantiated")

  def ddl = (Persons.ddl)

  def create(implicit session: Session): Unit = {
    try {
      ddl.create
    } catch {
      case e: Exception => logger.info("Could not create database.... assuming it already exists")
    }
  }

  def drop(implicit session: Session): Unit = {
    try {
      ddl.drop
    } catch {
      case e: Exception => logger.info("Could not drop database")
    }
  }

  def purge(implicit session: Session) = { drop; create }
}