package com.marmend.sepa.channel

import com.marmend.sepa.channel.Model.{Account, Pain001, Pain001Transaction, Transaction}
import org.json4s.DefaultFormats
import org.json4s.JsonAST.JValue
import org.json4s.native.JsonMethods.parse

/**
  * @param account : Your Company account
  *                This class opens a payment com.marmend.sepa.channel.
  *                The com.marmend.sepa.channel takes all messages from one message queue, creates an ISO20022 file from it and
  *                passes the file to the next message queue.
  *                Each com.marmend.sepa.channel is bound to one of your Company Account and one File format (pain001 | pain008).
  *                This applies also to the message queue the com.marmend.sepa.channel reads from and writes to.
  */
class PaymentExecutor(account: Account, transaction: List[String]) {


  private def jsonObject(jsonString: String): JValue = {
    parse(jsonString)
  }

  def executeCreditTransfer(): Pain001 = {

    implicit val formats = DefaultFormats

      Pain001(account, transaction.map(
        json => Pain001Transaction(jsonObject(json).extract[Transaction])
      ))

  }

}