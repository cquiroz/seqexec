package edu.gemini.seqexec.web.client.components

import edu.gemini.seqexec.web.common.Step
import japgolly.scalajs.react.{BackendScope, ReactComponentB}
import japgolly.scalajs.react.vdom.prefix_<^._
import edu.gemini.seqexec.web.client.mdl._

object StepConfiguration {
  @inline val gs = GlobalStyles

  case class Props(s: Step)

  class Backend($: BackendScope[Props, Unit]) {
    def bg(s: String) = s match {
      case i if i.startsWith("instrument") => "light-green-100"
      case i if i.startsWith("observe") => "cyan-100"
      case i if i.startsWith("telescope") => "amber-200"
      case i if i.startsWith("ocs") => "cyan-100"
      case i => ""
    }

    def render(p: Props) = {
      <.table(
        ^.className := "mdl-data-table mdl-js-data-table mdl-shadow--2dp full-width",
        <.thead(
          <.tr(
            <.th(
              ^.className :="mdl-data-table__cell--non-numeric",
              "Name"
            ),
            <.th(
              ^.className :="mdl-data-table__cell--non-numeric",
              "Value"
            )
          )
        ),
        <.tbody(
          p.s.config.map ( s =>
            <.tr(
              <.td(
                ^.className := s"mdl-data-table__cell--non-numeric mdl-color--${bg(s.key)}",
                s.key
              ),
              <.td(
                ^.className := s"mdl-data-table__cell--non-numeric mdl-color--${bg(s.key)}",
                s.value
              )
            )
          )
        )
      ).mdl
      /*MuiTable(
        key = s"step-config-${p.s.id}",
        selectable = false)(
        MuiTableHeader(
          displaySelectAll = false)(
          colNames
        ),
        MuiTableBody(
          displayRowCheckbox = false)(
          p.s.config.map ( c =>
            MuiTableRow(
              key = s"step-config-item-${c.key}",
              style = js.Dynamic.literal(`padding-left` = "0px", `padding-right` = "0px", `padding-top` = "0px", `padding-bottom` = "0px", height = "20px", background = bg(c.key))
            )(
              MuiTableRowColumn(
                style = js.Dynamic.literal(`padding-left` = "0px", `padding-right` = "0px", `padding-top` = "0px", `padding-bottom` = "0px", height = "20px", background = bg(c.key))
              )(
                <.div(
                  //gs.greenBg,
                  c.key)),
              MuiTableRowColumn()(c.value)
            )
          )
        )
      )*/
    }
  }

  val component = ReactComponentB[Props]("StepConfiguration")
      .renderBackend[Backend]
      .build

  def apply(p: Props) = component(p)

}
