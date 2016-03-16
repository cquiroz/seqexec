package edu.gemini.seqexec.web.client.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import edu.gemini.seqexec.web.client.mdl._

/**
  * Component for the search form at the top of the page
  */
object SequenceSearch {
  @inline val gs = GlobalStyles

  case class State(searchFieldOpen: Boolean, searchText: String)
  case class Props(searchStart: String => Callback)

  class Backend($: BackendScope[Props, State]) {

    def onSubmit:Callback =
      $.props.zip($.state) >>= {case (p, s) => Callback.ifTrue(s.searchText.nonEmpty, p.searchStart(s.searchText))}

    val onSearchTouch: ReactEvent => Callback = e => {
      e.preventDefaultCB >> $.modState(s => s.copy(searchFieldOpen = !s.searchFieldOpen))
    }

    val closeText: ReactEventI => Callback = e => {
      $.modState(s => s.copy(searchFieldOpen = e.currentTarget.value.nonEmpty))
    }
    val keyDown: ReactKeyboardEventI => Callback = e => {
      Callback.ifTrue(e.charCode == 13, onSubmit)
    }

    def onChange(e: ReactEventI): Callback = {
      val v = e.target.value // TODO check why we need this
      $.modState(s => s.copy(searchText = v))
    }

    def render(p: Props, s: State) = {
      <.div(
        ^.className := "mdl-textfield mdl-js-textfield mdl-textfield--expandable",
        <.label(
          ^.className := "mdl-button mdl-js-button mdl-button--icon",
          ^.onClick --> onSubmit,
          ^.htmlFor := "search",
          <.i(
            ^.className := "material-icons",
            ^.onClick --> onSubmit,
            "search"
          )
        ),
        <.div(
          ^.className := "mdl-textfield__expandable-holder",
          <.input(
            ^.id := "search",
            ^.className := "mdl-textfield__input",
            ^.`type`    := "text",
            ^.value := s.searchText,
            ^.onKeyPress ==> keyDown,
            ^.onChange ==> onChange),
          <.label(
            ^.className := "mdl-textfield__label",
            ^.htmlFor := "search",
            "Enter an obs id..."
          )
        )
      )
    }
  }

  val component = ReactComponentB[Props]("SeqexecAppBar")
    .initialState(State(searchFieldOpen = false, ""))
    .renderBackend[Backend]
    .build

  def apply(p: Props) = component(p)
}
