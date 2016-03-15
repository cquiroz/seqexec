package edu.gemini.seqexec.web.client.components

import japgolly.scalajs.react.ReactComponentB
import japgolly.scalajs.react.vdom.prefix_<^._

object SideBar {
  case class Props(sequences: List[String])

  val component = ReactComponentB[Props]("Sidebar")
    .stateless
    .render_P(p =>
      <.div(
        ^.className := "demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50",
        <.header(
          ^.className := "demo-drawer-header",
          <.img(
            ^.src := "images/user.png",
            ^.cls := "demo-avatar"
          ),
          <.div(
            ^.className := "demo-avatar-dropdown",
            <.span("cquiroz@gemini.edu"),
            <.div(
              ^.cls := "mdl-layout-spacer"
            ),
            <.button(
              ^.cls := "mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon",
              ^.id := "accbtn",
              <.i(
                ^.cls := "material-icons",
                ^.role := "presentation",
                "arrow_drop_down"
              ),
              <.span(
                ^.cls := "visuallyhidden",
                "Accounts"
              )
            ),
            <.ul(
              ^.cls := "mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect",
              ^.htmlFor := "accbtn",
              <.li(
                ^.cls := "mdl-menu__item",
                "Logout"
              )
            )
          )
        ),
        <.nav(
          ^.cls := "demo-navigation mdl-navigation mdl-color--blue-grey-800",
          p.sequences.nonEmpty ?= <.span(
            ^.cls := "mdl-typography--caption mdl-typography--text-center",
            "Running"
          ),
          p.sequences.distinct.map( s =>
            <.a(
              ^.cls := "mdl-navigation__link",
              ^.href := "#",
              <.i(
                ^.cls := "mdl-color-text--blue-grey-200 material-icons",
                ^.role := "presentation"
              ),
              s
            )
          ),
          <.div(
            ^.cls := "mdl-layout-spacer"
          )
        )
      )
    ).build

  def apply(p: Props) = component(p)
  /*<div class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
          <header class="demo-drawer-header">
            <img src="images/user.jpg" class="demo-avatar">
            <div class="demo-avatar-dropdown">
              <span>hello@example.com</span>
              <div class="mdl-layout-spacer"></div>
              <button id="accbtn" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                <i class="material-icons" role="presentation">arrow_drop_down</i>
                <span class="visuallyhidden">Accounts</span>
              </button>
              <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">
                <li class="mdl-menu__item">hello@example.com</li>
                <li class="mdl-menu__item">info@example.com</li>
                <li class="mdl-menu__item"><i class="material-icons">add</i>Add another account...</li>
              </ul>
            </div>
          </header>
          <nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">home</i>Home</a>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">inbox</i>Inbox</a>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">delete</i>Trash</a>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">report</i>Spam</a>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">forum</i>Forums</a>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">flag</i>Updates</a>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">local_offer</i>Promos</a>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">shopping_cart</i>Purchases</a>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">people</i>Social</a>
            <div class="mdl-layout-spacer"></div>
            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>
          </nav>
        </div>*/
}
