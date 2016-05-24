package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class TodoController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.todo, "/*")

  override def afterAll() {
    super.afterAll()
    Todo.deleteAll()
  }

  def newTodo = FactoryGirl(Todo).create()

  it should "show todo" in {
    get("/todo") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/todo/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/todo.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/todo.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a todo in detail" in {
    get(s"/todo/${newTodo.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/todo/${newTodo.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/todo/${newTodo.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/todo/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a todo" in {
    post(s"/todo",
      "title" -> "dummy",
      "tag" -> "dummy",
      "priority" -> "dummy",
      "limit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "content" -> "dummy",
      "status" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/todo",
        "title" -> "dummy",
        "tag" -> "dummy",
        "priority" -> "dummy",
        "limit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "content" -> "dummy",
        "status" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Todo.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/todo/${newTodo.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a todo" in {
    put(s"/todo/${newTodo.id}",
      "title" -> "dummy",
      "tag" -> "dummy",
      "priority" -> "dummy",
      "limit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "content" -> "dummy",
      "status" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/todo/${newTodo.id}",
        "title" -> "dummy",
        "tag" -> "dummy",
        "priority" -> "dummy",
        "limit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "content" -> "dummy",
        "status" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a todo" in {
    delete(s"/todo/${newTodo.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/todo/${newTodo.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }


  it should "findAll todo's json" in {
    get(s"/todo/v1/items") {
      logBodyUnless(200)
      status should equal(200)
    }
    withSession("csrf-token" -> "valid_token") {
      get(s"/todo/v1/items") {
        logBodyUnless(200)
        status should equal(200)
      }
   }
  }

}
