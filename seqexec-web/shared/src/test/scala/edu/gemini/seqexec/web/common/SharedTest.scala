package edu.gemini.seqexec.web.common

import org.scalatest.{FreeSpec, Matchers}

class SharedTest extends FreeSpec with Matchers {
  "Sequence" - {
    "should have an id" in {
      Sequence("id", SequenceSteps(Nil)).id shouldEqual "id"
    }
  }
}
