package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class TodoControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Todo.deleteAll()
  }

  def createMockController = new TodoController with MockController
  def newTodo = FactoryGirl(Todo).create()

  describe("TodoController") {

    describe("shows todo") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/todo/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/todo/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a todo") {
      it("shows HTML response") {
        val todo = newTodo
        val controller = createMockController
        controller.showResource(todo.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Todo]("item") should equal(Some(todo))
        controller.renderCall.map(_.path) should equal(Some("/todo/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/todo/new"))
      }
    }

    describe("creates a todo") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "title" -> "dummy",
          "tag" -> "dummy",
          "priority" -> "1",
          "limit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
          "content" -> "dummy",
          "status" -> "1")
        controller.createResource()
        controller.status should equal(200)
      }

      it("fails with invalid parameters") {
        val controller = createMockController
        controller.prepareParams() // no parameters
        controller.createResource()
        controller.status should equal(400)
        controller.errorMessages.size should be >(0)
      }
    }

    it("shows a resource edit input form") {
      val todo = newTodo
      val controller = createMockController
      controller.editResource(todo.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/todo/edit"))
    }

    it("updates a todo") {
      val todo = newTodo
      val controller = createMockController
      controller.prepareParams(
        "title" -> "dummy",
        "tag" -> "dummy",
        "priority" -> "1",
        "limit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "content" -> "dummy",
        "status" -> "1")
      controller.updateResource(todo.id)
      controller.status should equal(200)
    }

    it("destroys a todo") {
      val todo = newTodo
      val controller = createMockController
      controller.destroyResource(todo.id)
      println(controller.status)
      controller.status should equal(200)
    }

  }

}
