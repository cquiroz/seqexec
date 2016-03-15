package edu.gemini.seqexec.web.client.components

import edu.gemini.seqexec.web.client.services.SeqexecWebClient
import edu.gemini.seqexec.web.common.Sequence
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB}
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.ext.AjaxException
import edu.gemini.seqexec.web.client.mdl._

import scala.concurrent.ExecutionContext.Implicits.global

object SeqexecUI {

  case class State(searching: Boolean, errorMessage: List[String], sequence: Option[Sequence], runningSequence: List[String])

  class Backend($: BackendScope[Unit, State]) {

    def readRemote(id: String): Callback = {
      def handleSearchError: String => Callback = e => {
        $.modState(s => s.copy(searching = false, List(e), None))
      }

      Callback.future(
        SeqexecWebClient.read(id).collect {
          case u: Sequence => $.modState(s => s.copy(searching = false, Nil, Some(u)))
        }.recover {
          case e: AjaxException if e.xhr.status == 404 => handleSearchError(s"Sequence '$id' not found")
          case e                                       => handleSearchError(e.getMessage)
        }
      )
    }

    def searchStart: String => Callback = s => {
      $.modState(s => s.copy(searching = true)) >> readRemote(s)
    }

    def runSequence(seq: Sequence):Callback =
      $.modState(s => s.copy(runningSequence = s.runningSequence :+ seq.id))

    def render(s:State) = {
      <.div(// Don't remove or mdl-js-layout breaks the event bubbling http://stackoverflow.com/questions/31998227/using-material-design-lite-with-react
        <.div(
          ^.className := "demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header",
          NavBar(NavBar.Props(searchStart)),
          SideBar(SideBar.Props(s.runningSequence)),
          SeqexecBody(SeqexecBody.Props(s.searching, s.errorMessage, s.sequence, runSequence))
        )
      ).mdl
    }
  }

  val component = ReactComponentB[Unit]("Seqexec")
    .initialState_P(_ => State(searching = false, Nil, None, Nil))
    .renderBackend[Backend]
    .buildU

  def apply() = component()
}
