package edu.gemini.seqexec.web.client.components

import scalacss.Defaults._

object GlobalStyles extends StyleSheet.Inline {
  import dsl._

  val robotoFont = fontFace("Roboto")(
    _.src("url('fonts/Roboto-Regular-webfont.eot')",
      "url('fonts/Roboto-Regular-webfont.eot?#iefix') format('embedded-opentype')",
      "url('fonts/Roboto-Regular-webfont.woff') format('woff')",
      "url('fonts/Roboto-Regular-webfont.ttf') format('ttf')",
      "url('fonts/Roboto-Regular-webfont.svg#RobotoRegular') format('svg')")
      .fontWeight.normal
      .fontStyle.normal
  )

  val html = style(unsafeRoot("html")(
    fontFamily(robotoFont)
  ))

  val body = style(unsafeRoot("body")(
    margin(0.px),
    fontSize(13.px),
    lineHeight(20.px)
  ))

  val whiteText = style("white-text")(
    color(white)
  )

  val greenBg = style("green-bg")(
    backgroundColor(green)
  )

  //val bootstrapStyles = new BootstrapStyles

  //val iconStyles = new GlyphIconStyles
}
