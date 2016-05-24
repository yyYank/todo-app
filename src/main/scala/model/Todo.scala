package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Todo(
  id: Long,
  title: String,
  tag: String,
  priority: String,
  limitDate: Option[LocalDate] = None,
  content: String,
  status: String,
  createdAt: DateTime,
  updatedAt: DateTime
)

object Todo extends SkinnyCRUDMapper[Todo] with TimestampsFeature[Todo] {
  override lazy val tableName = "todo"
  override lazy val defaultAlias = createAlias("t")

  /*
   * If you're familiar with ScalikeJDBC/Skinny ORM, using #autoConstruct makes your mapper simpler.
   * (e.g.)
   * override def extract(rs: WrappedResultSet, rn: ResultName[Todo]) = autoConstruct(rs, rn)
   *
   * Be aware of excluding associations like this:
   * (e.g.)
   * case class Member(id: Long, companyId: Long, company: Option[Company] = None)
   * object Member extends SkinnyCRUDMapper[Member] {
   *   override def extract(rs: WrappedResultSet, rn: ResultName[Member]) =
   *     autoConstruct(rs, rn, "company") // "company" will be skipped
   * }
   */
  override def extract(rs: WrappedResultSet, rn: ResultName[Todo]): Todo = new Todo(
    id = rs.get(rn.id),
    title = rs.get(rn.title),
    tag = rs.get(rn.tag),
    priority = rs.get(rn.priority),
    limitDate = rs.get(rn.limitDate),
    content = rs.get(rn.content),
    status = rs.get(rn.status),
    createdAt = rs.get(rn.createdAt),
    updatedAt = rs.get(rn.updatedAt)
  )
}
