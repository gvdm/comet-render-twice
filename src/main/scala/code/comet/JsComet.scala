package code.comet

import net.liftweb.http.CometActor
import net.liftweb.http.S
import net.liftweb.http.js.JsCmds.Alert

object JsComet {
  val template = <div class="text"></div>
}

class JsComet extends CometActor {
  
  def render = {
    S.appendJs(Alert("should only be here once"))
    ".text" #> "Comet rendered"
  }

}