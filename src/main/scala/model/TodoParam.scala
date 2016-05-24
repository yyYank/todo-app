package model

import skinny.ParamType

/**
  * 優先度ドメイン用ParamType
  */
object TodoParam {
  val PriorityType = ParamType {
    case null => Priority.None.label
    case v: String if v.trim.isEmpty => Priority.None.label
    case v: String => Priority.create(v)
    case _ => Priority.None.label
  }

  val StatusType = ParamType {
    case null => Status.None.label
    case v: String if v.trim.isEmpty => Status.None.label
    case v: String => Status.create(v)
    case _ => Status.None.label
  }
}
