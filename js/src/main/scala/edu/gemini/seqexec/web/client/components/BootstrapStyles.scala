package edu.gemini.seqexec.web.client.components

import scalacss.Defaults._
import scalacss.mutable
import Bootstrap.CommonStyle._

/**
  * Define styles to be used with bootstrap
  * @see https://getbootstrap.com/
  */
class BootstrapStyles(implicit r: mutable.Register) extends StyleSheet.Inline()(r)  {
  import dsl._

  val csDomain = Domain.ofValues(default, primary, success, info, warning, danger)

  val contextDomain = Domain.ofValues(success, info, warning, danger)

  def commonStyle[A](domain: Domain[A], base: String) = styleF(domain)(opt =>
    styleS(addClassNames(base, s"$base-$opt"))
  )

  def styleWrap(classNames: String*) = style(addClassNames(classNames: _*))

  // Grid
  val container = styleWrap("container")
  val row = styleWrap("row")

  // Forms
  val formControl = styleWrap("form-control")
  val inputGroup = styleWrap("input-group")

  // Buttons
  val btnDefault = styleWrap("btn", "btn-default")
  val inputGroupButton = styleWrap("input-group-btn")

  // Navbar
  val navBarForm = styleWrap("navbar-form")
  val navBarHeader = styleWrap("navbar-header")
  val navBarBrand = styleWrap("navbar-brand")
  val topNavBar = styleWrap("navbar navbar-inverse navbar-fixed-top")

  // UI
  val pullLeft = styleWrap("pull-left")

  // Responsive
  val hiddenXS = styleWrap("hidden-xs")
  val visibleXS = styleWrap("visible-xs")

  // alert
  val alert = commonStyle(contextDomain, "alert")
}
