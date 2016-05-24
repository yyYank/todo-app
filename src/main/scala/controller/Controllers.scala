package controller

import skinny._
import skinny.controller.AssetsController

object Controllers {

  def mount(ctx: ServletContext): Unit = {
    todo.mount(ctx)
    root.mount(ctx)
    AssetsController.mount(ctx)
  }

  object root extends RootController with Routes {
    val indexUrl = get("/?")(index).as('index)
  }

  object todo extends _root_.controller.TodoController with Routes {
    // REST用のURLをルーティング
    val version = "v1"
    val readV1Api = get(s"${viewsDirectoryPath}/${version}/items")(read).as('read)
//    val createV1Api = post(s"${viewsDirectoryPath}/${version}/items")(create).as('create)
//    val readByIdV1Api = get(s"${viewsDirectoryPath}/${version}/items/:id")(read).as('read)
//    val updateByIdV1Api = post(s"${viewsDirectoryPath}/${version}/items/:id")(update).as('update)
//    val deleteByIdV1Api = delete(s"${viewsDirectoryPath}/${version}/items/:id")(delete).as('delete)
  }

}
