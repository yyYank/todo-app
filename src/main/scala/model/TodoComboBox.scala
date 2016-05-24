package model

/**
  * 優先度抽象クラス
  *
  * @param value 値
  */
sealed abstract class ComboValue(val value: String, val label: String) {
  def toPair = (value, label)
}
/**
  * 優先度object
  */
object Priority extends ComboOperation {
  /** 優先度高 */
  case object High extends ComboValue("1", "高")
  /** 優先度中 */
  case object Middle extends ComboValue("2", "中")
  /** 優先度低 */
  case object Low extends ComboValue("3", "低")
  /** 優先度設定なし */
  case object None extends ComboValue("","")

  override def toAll = List(High.toPair, Middle.toPair,Low.toPair)
  override def create(v: String) = v match {
    case High.value => High.label
    case Middle.value => Middle.label
    case Low.value => Low.label
    case _ => None.label
  }
}

/**
  * ステータスobject
  */
object Status extends ComboOperation {

  case object NotStarted extends ComboValue("1", "未着手")
  case object Started extends ComboValue("2", "着手中")
  case object Complete extends ComboValue("3", "完了")
  case object OnHold extends ComboValue("4", "保留")
  case object None extends ComboValue("", "")
  override def toAll = List(NotStarted.toPair, Started.toPair, Complete.toPair, OnHold.toPair)
  override def create(v: String) = v match {
    case NotStarted.value => NotStarted.label
    case Started.value => Started.label
    case OnHold.value => OnHold.label
    case Complete.value => Complete.label
    case _ => None.label
  }
}

/**
  * コンボボックスのオペレーション
  */
trait ComboOperation {
  def toAll : List[(String, String)]
  def create(v: String) : String
}
