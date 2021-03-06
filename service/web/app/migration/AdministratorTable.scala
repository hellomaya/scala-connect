package migration

import java.text.SimpleDateFormat

import core.MigrationTable
import models.{AdministratorQuery, Administrator}
import slick.driver.MySQLDriver.api._


object AdministratorTable extends MigrationTable[Administrator] {

  lazy val query = AdministratorQuery.query
  lazy val db = AdministratorQuery.db

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

          m.username,
          m.email,
          m.firstName,
          m.lastName,
          m.displayName,
          m.introduction,
          m.createdAt,
          m.updatedAt
          )
      } +=(
        faker.Name.name,
        faker.Internet.email,
        faker.Name.first_name,
        faker.Name.last_name,
        faker.Name.name,
        faker.Lorem.paragraph(3),
        now,
        now
        )

    )

    val result = db.run(setup)


    result
  }
}
