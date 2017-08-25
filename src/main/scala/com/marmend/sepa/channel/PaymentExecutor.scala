package com.marmend.sepa.channel
import com.marmend.sepa.channel.file.transaction.ISO20022Transaction
import com.marmend.sepa.channel.file.ISO20022

/**
  * @param account: Your Company account
  * @param isoFileFormat: The requested File Format
  * This class opens a payment com.marmend.sepa.channel.
  * The com.marmend.sepa.channel takes all messages from one message queue, creates an ISO20022 file from it and
  * passes the file to the next message queue.
  * Each com.marmend.sepa.channel is bound to one of your Company Account and one File format (pain001 | pain008).
  * This applies also to the message queue the com.marmend.sepa.channel reads from and writes to.
  */
class PaymentExecutor(account : Account, isoFileFormat : ISO20022) {

  val json = List(
    """ 
     {
      "endToEndId": "45935",
      "instdAmt": 234.29,
      "iBAN": "DE12300800000000001234",
      "rmtInf": "Kundennummer 1234567 Rechnungsnummer 1111 purchase",
      "fullName": "John Doe"
     }
    """,
    """ 
    {
      "endToEndId": "12345", 
      "instdAmt": 234.99,
      "iBAN": "DE12300800000000001234",
      "rmtInf": "Test",
      "fullName": "Jon Doe brother"
    }
    """)

  /**
    * Takes all messages from the queue the PaymentChannel is bound to.
    * Transform the messages into ISO payment information.
    * Then assembles the ISO Payment File and sends it to the next message queue.
    */
  def execute() {

    //The request to SEPA credit transfer (could be an array of transactions)
    //so far mock with json above
    val request = json

    val parsedTransfers = request.map { x => ISO20022Transaction.apply(isoFileFormat, x) }.flatten

    //assemble File from xml transactions
    val xml = ISO20022(isoFileFormat, account, parsedTransfers)

    // send file to SEPA broker, so far just println
    xml.map { x => println(x.fullXmlFile()) }

    //ack original messsages 

  }

}