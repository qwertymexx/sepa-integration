package com.marmend.sepa.channel.file.transaction
import org.json4s._
import org.json4s.native.JsonMethods._
import com.marmend.sepa.channel.file.{Pain001,ISO20022}
import scala.util.Try


abstract class ISO20022Transaction {

  val xml = <a></a>
}
/**
  * @author philipp.kepplinger
  * @date 2016.09-23
  * Companion Object mostly used to instantiate ISO compatible transactions in different flavors.
  */
object ISO20022Transaction {

  /**
    * @author philipp.kepplinger
    * @param a String representation of a JSON, representing a transaction
    *
    * Takes a JSON and tries to create a valid xml fragment of an ISO20022 pain file.
    * Any errors will be sent as an error return message
    *
    */
  def apply(isoFileFormat : ISO20022, rawJson : String) : Option[ISO20022Transaction] = {
    //JSON -> JValue 
    implicit val formats = DefaultFormats // Brings in default date formats etc.
    val json = parse(rawJson)

    //JValue -> DOM
    isoFileFormat match {
      case Pain001 => Try {
        Some(json.extract[Pain001Transaction])
      }.getOrElse(None)
      case _ => None
    }

    //Validate DOM
  }

}