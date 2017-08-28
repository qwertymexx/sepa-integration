package com.marmend.sepa.channel

import com.marmend.sepa.channel.Model.MyBankAccount


object MainPaymentChannel {

  /**
    * The service generate SEPA valid payload to proceed with SEPA protocols.
    */
  def main(args: Array[String]) {


    val transList = List(
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

   val result = new PaymentExecutor(MyBankAccount, transList).executeCreditTransfer()

    print(result.fullXmlFile())
  }


  //TODO: Implement MessageBroker integration (Kafka, RabbitMQ) in PaymentExecutor
  //TODO: add verbose logging
  //TODO: Error handling


}
