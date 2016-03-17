package edu.gemini.seqexec.web.client.components

import org.scalatest.prop.PropertyChecks
import org.scalatest.{FreeSpec, Matchers}

class TrivialClientTest extends FreeSpec with Matchers with PropertyChecks {
  "Test" - {
    "should" in {
      forAll { (n: Int) =>
        whenever (n > 1) { n / 2 should be > 0 }
      }
    }
  }
}
