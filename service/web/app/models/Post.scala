package models

import slick.driver.MySQLDriver.api._

class Post(tag: Tag) extends Table[PostEntity](tag, "post") {

  def id = column[Option[Int]]("post_id", O.PrimaryKey, O.AutoInc)

  def memberId = column[Int]("member_id", O.Default(0))

  def title = column[String]("title", O.Length(128), O.Default(""))

  def body = column[String]("body", O.Length(512), O.Default(""))

  def status = column[String]("status", O.SqlType("CHAR"), O.Length(32), O.Default(""))

  def isArchived = column[String]("is_archived", O.SqlType("ENUM('true', 'false')"), O.Default("false"))

  def startedAt = column[Option[String]]("started_at", O.SqlType("DateTime"))

  def finishedAt = column[Option[String]]("finished_at", O.SqlType("DateTime"))

  def createdAt = column[String]("created_at", O.SqlType("DateTime"))

  def updatedAt = column[String]("updated_at", O.SqlType("DateTime"))

  def * =
    (
      id,
      memberId,
      title,
      body,
      status,
      isArchived,
      startedAt,
      finishedAt,
      createdAt,
      updatedAt) <>
      ((PostEntity.apply _).tupled, PostEntity.unapply)
}

case class PostEntity
(
  id: Option[Int],
  memberId: Int,
  title: String,
  body: String,
  status: String,
  isArchived: String,
  startedAt: Option[String],
  finishedAt: Option[String],
  createdAt: String,
  updatedAt: String
  )
