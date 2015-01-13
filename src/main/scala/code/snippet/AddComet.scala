package code.snippet

import net.liftweb.common._
import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml._
import net.liftweb.http.S
import code.comet.JsComet
import scala.xml.NodeSeq
import net.liftweb.builtin.snippet.Comet
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.jquery.JqJE._
import net.liftweb.http.js.jquery.JqJsCmds._

class AddComet {

  def render = "#add-comet *" #> ajaxButton("Add comet", () => {
    val cometHtml = S.findOrCreateComet[JsComet](Full(nextFuncName), NodeSeq.Empty, Map.empty, false).map { jsComet => 
      Comet.containerForCometActor(jsComet, Full(JsComet.template))
    }
    AppendHtml("comet-holder", cometHtml.openOr(<div>ERROR</div>))
  })
  
}