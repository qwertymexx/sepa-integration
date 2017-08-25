package com.marmend.sepa.channel.util

import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory
import scala.util.Try
import java.io.ByteArrayInputStream


object XMLValidation {

  def validate(xml: String, xsd: String): Boolean = {

    Try {
      val schemaLang = "http://www.w3.org/2001/XMLSchema"
      val factory = SchemaFactory.newInstance(schemaLang)
      val schema = factory.newSchema(stringToStreamSource(xsd))
      val validator = schema.newValidator()
      validator.validate(stringToStreamSource(xsd))
    }.isSuccess
  }

  def stringToStreamSource(text: String): StreamSource = {
    new StreamSource(new ByteArrayInputStream(text.getBytes("UTF-8")))
  }
}