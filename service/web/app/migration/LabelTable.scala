package migration

import java.text.SimpleDateFormat

import core.MigrationTable
import models._
import slick.driver.MySQLDriver.api._

object LabelTable extends MigrationTable[Label]{

  lazy val query = LabelQuery.query
  lazy val db = LabelQuery.db

  /**
   * initialize the table, create its schema, update its schema
   */
  def initialize(drop:Boolean = false) = init(query, db)(drop)

  /**
   * populate the data into the table
   */
  def populate = {

    val dt: java.util.Date = new java.util.Date()

    val sdf: SimpleDateFormat =
      new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    val now: String = sdf.format(dt)


    val setup = DBIO.seq(

      query.map {

        m => (
          m.name,
          m.createdAt,
          m.updatedAt
          )
      } +=(
        faker.Lorem.words(2).mkString(""),
        now,
        now
        )

    )

    val result = db.run(setup)

    result


  }
}
