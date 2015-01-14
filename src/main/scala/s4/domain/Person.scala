package s4.domain

case class Person(fname: String, lname: String, id: Option[Long] = None)

// It is an adaptation of the official Slick example set (Cake Pattern):
// https://github.com/slick/slick-examples (MultiDBCakeExample.scala)
// Understanding that example will help to understand this code.
trait PersonComponent { this: Profile =>
  import profile.simple._

  class Persons(tag: Tag) extends Table[(String, String, Option[Long])](tag, "PERSON") {
    def id = column[Option[Long]]("ID", O.PrimaryKey, O.AutoInc)
    def fname = column[String]("FNAME", O.NotNull)
    def lname = column[String]("LNAME", O.NotNull)
    def * = (fname, lname, id)
    // def mapped = fname ~ lname ~ id <> ({ (f, l, i) => Person(f, l, i) }, { x: Person => Some((x.fname, x.lname, x.id)) })

    // Query Definition
    //val autoInc = fname ~ lname returning id into { case (c, i) => Person(c._1, c._2, i) }

    //def findAll = for (x <- Persons) yield x

    //def forInsert = fname ~ lname <>
    //  ({ (f, l) => Person(f, l, None) }, { x: Person => Some((x.fname, x.lname)) })

    // Query Execution
    //def findAllPersons(implicit session: Session): List[Person] = { findAll.list map { x => Person(fname = x._1, lname = x._2, id = x._3) } }
    // def insert(person: Person)(implicit session: Session): Person = {
    //  autoInc.insert(person.fname, person.lname)
    // }
  }

  val persons = TableQuery[Persons]

  private val usersAutoInc = persons.map(u => (u.fname, u.lname)) returning persons.map(_.id) into {
    case (_, id) => id
  }

  def insert(person: Person)(implicit session: Session): Person = {
    // val picture = if (user.picture.id.isEmpty) { //if no picture id...
    //   insert(user.picture) //...insert
    //  } else user.picture //else return current picture
    val id = usersAutoInc.insert(person.fname, person.lname)
    person.copy(id = id)
  }
  
  def findAllPersons(implicit session: Session): List[Person] = { persons.list map { x => Person(fname = x._1, lname = x._2, id = x._3) } }
}