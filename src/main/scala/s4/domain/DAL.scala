package s4.domain

import scala.slick.driver.JdbcProfile
import scala.slick.backend.DatabaseComponent
import scala.slick.driver.H2Driver
import scala.slick.driver.SQLiteDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
//import s4.domain.PersonComponent.Persons

import scala.slick.driver.JdbcProfile
import scala.slick.driver.H2Driver
import scala.slick.driver.SQLiteDriver

trait Profile {
  val profile: JdbcProfile
}

// The data access layer (DAL) is a cake pattern implementation.
// It is an adaptation of the official Slick example set:
// https://github.com/slick/slick-examples (MultiDBCakeExample.scala)
// Understanding that example will help to understand this code.
class DAL(override val profile: JdbcProfile) extends PersonComponent with Profile {
  import profile.simple._

  val logger: Logger = LoggerFactory.getLogger("s4.domain");
  logger.info("Model class instantiated")

  //def ddl = (PersonComponent.Persons)

  def create(implicit session: Session): Unit = {
    try {
      persons.ddl.create
    } catch {
      case e: Exception => logger.info("Could not create database.... assuming it already exists")
    }
  }

  def drop(implicit session: Session): Unit = {
    try {
      persons.ddl.drop
    } catch {
      case e: Exception => logger.info("Could not drop database")
    }
  }

  def purge(implicit session: Session) = { drop; create }
}