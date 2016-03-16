package edu.gemini.seqexec.web.client.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import edu.gemini.seqexec.web.client.mdl._

/**
  * Component for the search form at the top of the page
  */
object NavBar {

  case class Props(searchStart: String => Callback)

  val component = ReactComponentB[Props]("SeqexecAppBar")
    .render(P =>
      <.header(
        ^.className := "demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600",
        <.div(
          ^.className := "mdl-layout__header-row",
          <.span(^.className := "mdl-layout-title", "Seqexec"),
          <.div(^.className := "mdl-layout-spacer"),
          SequenceSearch(SequenceSearch.Props(P.props.searchStart)),
          <.button(
            ^.className :="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon",
            <.i(
              ^.className := "material-icons", "more_vert")
          )
        )
      )
    )
    .build
  /*
  <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
    <div class="mdl-layout__header-row">
      <span class="mdl-layout-title">Home</span>
      <div class="mdl-layout-spacer"></div>
      <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
        <label class="mdl-button mdl-js-button mdl-button--icon" for="search">
          <i class="material-icons">search</i>
        </label>
        <div class="mdl-textfield__expandable-holder">
          <input class="mdl-textfield__input" type="text" id="search">
          <label class="mdl-textfield__label" for="search">Enter your query...</label>
        </div>
      </div>
      <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
        <i class="material-icons">more_vert</i>
      </button>
      <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
        <li class="mdl-menu__item">About</li>
        <li class="mdl-menu__item">Contact</li>
        <li class="mdl-menu__item">Legal information</li>
      </ul>
    </div>
  </header>
  */
  def apply(p: Props) = component(p)
}
