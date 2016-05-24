package model

import org.scalatest.{FunSpec, Outcome, Matchers}
import org.scalatest.fixture.FlatSpec
import scalikejdbc.scalatest.AutoRollback
import skinny.DBSettings

/**
  * Created by ryosukeshigetoyo on 2016/05/09.
  */
class TodoComboBoxSpec extends FunSpec with Matchers {
  describe("Priority create") {
    it("1") {
      Priority.create("1") should equal("高")
    }

    it("2") {
      Priority.create("2") should equal("中")
    }

    it("3") {
      Priority.create("3") should equal("低")
    }

    it("4") {
      Priority.create("4") should equal("")
    }
  }

  describe("Priority toAll") {
    val priorities = Priority.toAll
    priorities.size should equal(3)
    priorities(0) should equal(("1", "高"))
    priorities(1) should equal(("2", "中"))
    priorities(2) should equal(("3", "低"))
  }

  describe("Status create") {
    it("1") {
      Status.create("1") should equal("未着手")
    }

    it("2") {
      Status.create("2") should equal("着手中")
    }

    it("3") {
      Status.create("3") should equal("完了")
    }

    it("4") {
      Status.create("4") should equal("保留")
    }
  }

  describe("Status toAll") {
    val statuses = Status.toAll
    statuses.size should equal(4)
    statuses(0) should equal(("1", "未着手"))
    statuses(1) should equal(("2", "着手中"))
    statuses(2) should equal(("3", "完了"))
    statuses(3) should equal(("4", "保留"))
  }
}
