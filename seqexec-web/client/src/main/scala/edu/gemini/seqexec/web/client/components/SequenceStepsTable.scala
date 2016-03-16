package edu.gemini.seqexec.web.client.components

import edu.gemini.seqexec.web.common.{SequenceSteps, Step}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import edu.gemini.seqexec.web.client.mdl._

object SequenceStepsTable {

  case class Props(id: String, steps: SequenceSteps, openStep: Step => Callback)

  class Backend($: BackendScope[Props, Unit]) {

    def openDetails(p: Props, s: Step) = p.openStep(s)

    def render(p: Props) = {
        <.table(
          ^.className := "mdl-data-table mdl-js-data-table mdl-shadow--2dp full-width",
          <.tbody(
            p.steps.steps.map ( s =>
              <.tr(
                <.td(
                  ^.className := "mdl-data-table__cell--non-numeric", f"Step ${s.id}: ${p.id}-${s.id+1}%03d",
                  ^.onClick --> openDetails(p, s),
                  ^.onTouchEnd --> openDetails(p, s)
                )
              )
            )
          )
      ).mdl
    }
  }

  val component = ReactComponentB[Props]("SequenceSteps")
      .renderBackend[Backend]
      .build

  def apply(p: Props) = component(p)
}
