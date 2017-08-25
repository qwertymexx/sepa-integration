package com.marmend.sepa.channel

/**
  * Constant accounts must be registered here.
  * This is primarily to have a tight control over which accounts can be used by SEPA.
  */
sealed abstract case class Account(routingKeyPart : String, name : String, iban : String, bic : String)

final object MyBankAccount extends Account("routing_key", "account_name", "DE12341000000039334349", "Mybank_BIC")