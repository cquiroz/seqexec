package edu.gemini.seqexec.web.server.play

import org.specs2.mutable.Specification
import play.api.http.{Status, HeaderNames, HttpProtocol, HttpVerbs}
import play.api.routing.Router
import play.api.test._

//import play.api.test._
import play.api.routing.sird._

class BackendSpecs extends Specification with ResultExtractors with HeaderNames with Status with RouteInvokers with Writeables with DefaultAwaitTimeout {
  val r: Router.Routes = {
    case GET(p"/") => {
      println("here")
      // Index
      CustomAssets.at("./src/main/resources", "index-bt.html", "/")
    }
  }


  val mockApplication = new FakeApplication(withRoutes = r)

  "Backend" should {
      "respond to the index Action" in new WithApplication(mockApplication) {
        val Some(result) = route(FakeRequest(HttpVerbs.GET, "/"))

        status(result) must equalTo(OK)
      }
  }
}
