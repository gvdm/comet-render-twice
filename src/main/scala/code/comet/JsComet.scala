package code.comet

import net.liftweb.http.CometActor
import net.liftweb.http.S
import net.liftweb.http.js.JsCmds.Alert
import net.liftweb.common._
import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml._
import net.liftweb.http.S
import scala.xml.NodeSeq
import net.liftweb.builtin.snippet.Comet
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.jquery.JqJE._
import net.liftweb.http.js.jquery.JqJsCmds._

case class Msg(text: String)

object JsComet {
  val template = <div class="comet-button"></div>
}

class JsComet extends CometActor {

  def render = {
    S.appendJs(Alert("should only be here once"))
    ".comet-button" #> ajaxButton("Press here for a closer look", () => {
      this ! Msg("test")
      Noop
    })
  }

  override def lowPriority = {
    case Msg(t) ⇒ {
      S.findOrCreateComet[JsComet](Full(nextFuncName), NodeSeq.Empty, Map.empty, true).map { jsComet ⇒
        println("made comet "+jsComet.name)
        val cometHtml = Comet.containerForCometActor(jsComet, Full(JsComet.template))
        partialUpdate(AppendHtml("comet-holder", cometHtml))
      }
    }
  }

}