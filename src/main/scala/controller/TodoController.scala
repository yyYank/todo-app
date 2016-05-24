package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model._

class TodoController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  /** 優先度コンボボックス用 */
  val priorities = Priority.toAll
  /** ステータスコンボボックス用 */
  val statuses = Status.toAll
  override def model = Todo
  override def resourcesName = "todo"
  override def resourceName = "todo"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDate("limit_date")
  override def createForm = validation(createParams,
    paramKey("title") is required & maxLength(512),
    paramKey("tag") is required & maxLength(512),
    paramKey("priority") is required & maxLength(512),
    paramKey("limit_date") is dateFormat,
    paramKey("content") is required & maxLength(512),
    paramKey("status") is required & maxLength(512)
  )
  override def createFormStrongParameters = Seq(
    "title" -> ParamType.String,
    "tag" -> ParamType.String,
    "priority" -> TodoParam.PriorityType,
    "limit_date" -> ParamType.LocalDate,
    "content" -> ParamType.String,
    "status" -> TodoParam.StatusType
  )

  override def updateParams = Params(params).withDate("limit_date")
  override def updateForm = validation(updateParams,
    paramKey("title") is required & maxLength(512),
    paramKey("tag") is required & maxLength(512),
    paramKey("priority") is required & maxLength(512),
    paramKey("limit_date") is dateFormat,
    paramKey("content") is required & maxLength(512),
    paramKey("status") is required & maxLength(512)
  )
  override def updateFormStrongParameters = Seq(
    "title" -> ParamType.String,
    "tag" -> ParamType.String,
    "priority" -> TodoParam.PriorityType,
    "limit_date" -> ParamType.LocalDate,
    "content" -> ParamType.String,
    "status" -> TodoParam.StatusType
  )

  def read = {
    contentType = formats("json")
    logger.debug("params=" + params)
    import scalikejdbc._
    val m = model.defaultAlias
    val result =  model.findAll()
    toJSONString(Map('result -> result))
  }
}
