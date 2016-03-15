package edu.gemini.seqexec.web.client.components

import scalacss.Defaults._
import scalacss.mutable

/**
  * Define styles to be used with glyphicons
  * @see https://glyphicons.com/
  */
class GlyphIconStyles(implicit r: mutable.Register) extends StyleSheet.Inline()(r)  {
  import dsl._

  def commonStyle[A](domain: Domain[A], base: String) = styleF(domain)(opt =>
    styleS(addClassNames(base, s"$base-$opt"))
  )

  def styleWrap(classNames: String*) = style(addClassNames(classNames: _*))

  // Icons
  val searchIcon = styleWrap("glyphicon", "glyphicon-search")
}
