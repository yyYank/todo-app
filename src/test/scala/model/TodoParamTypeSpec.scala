package model

import org.scalatest.{Matchers, FunSpec}

/**
  * Created by ryosukeshigetoyo on 2016/05/09.
  */
class TodoParamTypeSpec extends FunSpec with Matchers {

  describe("TodoParamType.PRIORITY") {
    it("1"){
      TodoParamType.PRIORITY.unapply("1") should equal(Some("高"))
    }
    it("2"){
      TodoParamType.PRIORITY.unapply("2") should equal(Some("中"))
    }
    it("3"){
      TodoParamType.PRIORITY.unapply("3") should equal(Some("低"))
    }
  }

  describe("TodoParamType.STATUS") {
    it("1"){
      TodoParamType.STATUS.unapply("1") should equal(Some("未着手"))
    }
    it("2"){
      TodoParamType.STATUS.unapply("2") should equal(Some("着手中"))
    }
    it("3"){
      TodoParamType.STATUS.unapply("3") should equal(Some("完了"))
    }
    it("4"){
      TodoParamType.STATUS.unapply("4") should equal(Some("保留"))
    }
  }
}
