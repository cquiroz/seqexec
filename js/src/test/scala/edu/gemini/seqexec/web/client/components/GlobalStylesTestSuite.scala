package edu.gemini.seqexec.web.client.components

import utest._
import scalacss.Defaults._

object GlobalStylesTestSuite extends TestSuite {
  val tests = TestSuite {
    'emitcss{
      println(GlobalStyles.render)
      assert(GlobalStyles.styles.nonEmpty)
    }
  }
}
