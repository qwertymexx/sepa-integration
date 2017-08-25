package com.marmend.sepa.channel.file

import com.marmend.sepa.channel.file.transaction.ISO20022Transaction
import com.marmend.sepa.channel.Account

abstract class ISO20022 {

  val xml = <a></a>
  val xsd = <a></a>
  val routingKeyPart = ""

  def fullXmlFile() : String = {
    return """<?xml version="1.0" encoding="utf-8"?>""" + xml.mkString
  }

}

object ISO20022 {

  def apply(isoFileFormat : ISO20022, account : Account, transactions : List[ISO20022Transaction]) : Option[ISO20022] = {

    //TODO: replace the logic on monad functionality when we do not whant to create a file om empty payload or broken

    if (transactions.length.equals(0)) {
      None
    }

    isoFileFormat match {
      case Pain001 =>
        val xml = new Pain001(account, transactions)
        Some(xml)
      case _ => None
    }
  }
}

