package edu.gemini.seqexec.web.client.components

import edu.gemini.seqexec.web.common.SequenceSteps
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, ReactComponentB}

object SequenceStepsDetails {

  case class Props(steps: SequenceSteps)

  class Backend($: BackendScope[Props, Unit]) {
    def render(p: Props) = {
      <.div(
        /*p.steps.steps.map ( si =>
          MuiListItem(key = s"step-${si.id}", leftIcon = ActionInput()())(s"Step ${si.id}")
        )*/
      )
    }
  }

  val component = ReactComponentB[Props]("SequenceSteps")
      .renderBackend[Backend]
      .build

  def apply(p: Props) = component(p)
}
