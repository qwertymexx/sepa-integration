package com.marmend.sepa.channel

import com.marmend.sepa.channel.file.Pain001

object MainPaymentChannel {

  /**
    * The service generate SEPA valid payload to proceed with SEPA protocols.
    */
  def main (args: Array[String]) {

    val pc = new PaymentExecutor(MyBankAccount, Pain001)

    pc.execute()

  }


  //TODO: Implement MessageBroker integration (Kafka, RabbitMQ) in PaymentExecutor
  //TODO: add verbose logging
  //TODO: Error handling


}
