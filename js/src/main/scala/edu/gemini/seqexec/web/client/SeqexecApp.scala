package edu.gemini.seqexec.web.client

import edu.gemini.seqexec.web.client.components.{SeqexecUI, SequenceSearch, GlobalStyles}
import japgolly.scalajs.react.ReactDOM

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

import scalacss.Defaults._
import scalacss.ScalaCssReact._

import org.scalajs.dom.document

@JSExport("SeqexcApp")
object SeqexecApp extends JSApp {

  def main(): Unit = {
    //GlobalStyles.addToDocument()

    ReactDOM.render(SeqexecUI(), document.getElementById("content"))
  }
}
