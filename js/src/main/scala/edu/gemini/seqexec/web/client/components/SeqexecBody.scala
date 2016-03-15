package edu.gemini.seqexec.web.client.components

import edu.gemini.seqexec.web.common.{Sequence, Step}
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB}
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react._
import edu.gemini.seqexec.web.client.mdl._

/**
  * Created by cquiroz on 2/22/16.
  */
object SeqexecBody {
  case class Props(searching: Boolean, errorMessages: List[String], sequence: Option[Sequence], runSequence: Sequence => Callback) {
    def empty = !searching && sequence.isEmpty && errorMessages.isEmpty
    def sequenceName: Option[String] = sequence.map(_.id)
    def run = sequence.map(runSequence).getOrElse(Callback.empty)
  }

  case class State(step: Option[Step])

  class Backend($: BackendScope[Props, State]) {

    def openStep(s: Step): Callback =
      $.modState(u => u.copy(step = Some(s)))

    def backToSteps =
      $.modState(u => u.copy(step = None))

    def run =
      $.props >>= {_.run}

    def render(p: Props, st: State) = {

      <.main(
        ^.className := "mdl-layout__content mdl-color--grey-100",
        <.div(
          ^.className := "mdl-grid demo-content",
          <.div(
            ^.className := "demo-charts mdl-color--white mdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-grid",
            <.div(
              ^.className := "mdl-card  full-width",
              <.div(
                ^.className := "mdl-card__title",
                p.empty ?= <.h2(
                  ^.className := "mdl-card__title-text full-width",
                    if (p.searching) "Searching..." else "No sequence loaded"),
                p.sequenceName.isDefined ?= <.a(
                  ^.className := "mdl-card__title-text back-link",
                  ^.onClick --> backToSteps,
                  ^.onTouchStart --> backToSteps,
                  ^.href := "#",
                  p.sequenceName.get
                ),
                <.div(
                  ^.className :="mdl-layout-spacer"),
                p.sequenceName.isDefined ?= <.button(
                  ^.className := "mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-js-ripple-effect right",
                  ^.onClick --> run,
                  ^.onTouchStart --> run,
                  "run",
                  <.i(
                    ^.className := "material-icons",
                    "play_arrow")
                ).mdl,
                p.errorMessages.map(s =>
                  <.h2(
                    ^.className := "mdl-card__title-text full-width mdl-color-text--red",
                    s
                  )
                )
              ),
              <.div(
                ^.className := "mdl-card__supporting-text full-width no-padding",
                  <.div(
                    ^.display := {if (p.searching) "flex" else "none"},
                    ^.alignItems := "center",
                    <.div(
                      ^.cls := "mdl-layout-spacer"),
                    <.div(
                      ^.cls := s"mdl-spinner mdl-js-spinner is-active",
                      ^.height := "200px",
                      ^.width := "200px"
                    ),
                    <.div(
                      ^.cls := "mdl-layout-spacer")
                  ),
                p.sequence.map( s =>
                  st.step.map(u => StepConfiguration(StepConfiguration.Props(u))).getOrElse(SequenceStepsTable(SequenceStepsTable.Props(s.id, s.steps, openStep)))
                )
              )
            )
          )
        )
      ).mdl
    }
  }

  val component = ReactComponentB[Props]("SeqexecBody")
      .initialState_P(_ => State(None))
      .renderBackend[Backend]
      .build

  def apply(p: Props) = component(p)
}
