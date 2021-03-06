package migration

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException
import org.junit.runner._
import org.scalatestplus.play.PlaySpec
import org.specs2.runner._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * The test is flexible, and we can use different test framework,
 * and one task, is to test the model
 * to put all models in one test file is better idea, so we can
 * see all of them at same place
 *
 * Usually when we build the modal, we will do CRUD, so thats the
 * test target: which is CRUD
 *
 * This is more like a UnitTest, not a TDD
 */
@RunWith(classOf[JUnitRunner])
class DbSchemaSpec extends PlaySpec {

  "Db migration" must {

    "migrate all table/schema" in {

      // create all

      try {
        Await.result(TopicTable.initialize(), Duration("5 seconds"))
        Await.result(TopicItemTable.initialize(), Duration("5 seconds"))
        Await.result(MemberTable.initialize(), Duration("5 seconds"))
        Await.result(PostTable.initialize(), Duration("5 seconds"))
        Await.result(CommentTable.initialize(), Duration("5 seconds"))
        Await.result(MenuTable.initialize(), Duration("5 seconds"))
        Await.result(LabelTable.initialize(), Duration("5 seconds"))
        Await.result(LabelItemTable.initialize(), Duration("5 seconds"))
        Await.result(AdministratorTable.initialize(), Duration("5 seconds"))
      }

      catch {
        case err: MySQLSyntaxErrorException => {
          Await.result(TopicTable.initialize(true), Duration("5 seconds"))
          Await.result(TopicItemTable.initialize(true), Duration("5 seconds"))
          Await.result(MemberTable.initialize(true), Duration("5 seconds"))
          Await.result(PostTable.initialize(true), Duration("5 seconds"))
          Await.result(CommentTable.initialize(true), Duration("5 seconds"))
          Await.result(MenuTable.initialize(true), Duration("5 seconds"))
          Await.result(LabelTable.initialize(true), Duration("5 seconds"))
          Await.result(LabelItemTable.initialize(true), Duration("5 seconds"))
          Await.result(AdministratorTable.initialize(true), Duration("5 seconds"))
        }
      }


    }

    "continue" in {

      println("To be continuted...")

      //Project.remove

    }
  }

}
