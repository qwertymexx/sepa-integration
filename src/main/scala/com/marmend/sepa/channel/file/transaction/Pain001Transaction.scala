package com.marmend.sepa.channel.file.transaction

final case class Pain001Transaction(
    endToEndId : String,
    instdAmt : Double,
    fullName : String,
    iBAN : String,
    rmtInf : Option[String]) extends ISO20022Transaction {

  override val xml = {
    <CdtTrfTxInf>
      <PmtId>
        <EndToEndId>{ endToEndId }</EndToEndId>
      </PmtId>
      <Amt>
        <InstdAmt Ccy="EUR">{ instdAmt }</InstdAmt>
      </Amt>
      <Cdtr>
        <Nm>{ fullName }</Nm>
      </Cdtr>
      <CdtrAcct>
        <Id>
          <IBAN>{ iBAN }</IBAN>
        </Id>
      </CdtrAcct>
      {
        rmtInf.map { x =>
          <RmtInf>
            <Ustrd> { x } </Ustrd>
          </RmtInf>
        }.getOrElse("")
      }
    </CdtTrfTxInf>
  }
}