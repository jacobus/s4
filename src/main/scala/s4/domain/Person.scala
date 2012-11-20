package s4.domain

case class Person(fname: String, lname: String, id: Option[Long] = None)

// It is an adaptation of the official Slick example set (Cake Pattern):
// https://github.com/slick/slick-examples (MultiDBCakeExample.scala)
// Understanding that example will help to understand this code.
trait PersonComponent { this: Profile =>
  import profile.simple._

  object Persons extends Table[(String, String, Option[Long])]("PERSON") {
    def id = column[Option[Long]]("ID", O.PrimaryKey, O.AutoInc)
    def fname = column[String]("FNAME", O.NotNull)
    def lname = column[String]("LNAME", O.NotNull)
    def * = fname ~ lname ~ id
    def mapped = fname ~ lname ~ id <> ({ (f, l, i) => Person(f, l, i) }, { x: Person => Some((x.fname, x.lname, x.id)) })

    // Query Definition
    val autoInc = fname ~ lname returning id into { case (c, i) => Person(c._1, c._2, i) }
    def findAll = for (x <- Persons) yield x
 //   def findAllMapped = for (x <- Persons) yield x
    def forInsert = fname ~ lname <>
      ({ (f, l) => Person(f, l, None) }, { x: Person => Some((x.fname, x.lname)) })
      
    // Query Execution
    def findAllPersons(implicit session: Session): List[Person] = { findAll.list map { x => Person(fname = x._1, lname = x._2, id = x._3) } }
 //   def findAllPersons(implicit session: Session): List[Person] = { findAll.list map { x => Person(fname = x._1, lname = x._2, id = x._3) } }
    def insert(person: Person)(implicit session: Session): Person = {
      autoInc.insert(person.fname, person.lname)
    }
  }
}


    
 //   def findByName(n:String)(implicit session: Session) = for(c <- Persons; if c.fname === n) yield {case c => Person(fname = c._1, id = c._2)}
    
  //  def findByName(n: String) = byName yield c => Person(fname = c._1, id = c._2)
    
   // val deleteByName(n: String) = findByName(n).delete

 //   def findAllPersons(implicit session: Session) = findAll.list map { Person(fname = _._1, id = _._2)}
