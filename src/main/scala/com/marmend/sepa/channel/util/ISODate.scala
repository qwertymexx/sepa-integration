package com.marmend.sepa.channel.util

import java.text.SimpleDateFormat
import java.util.Date

/**
  * Helper Class. Gives Dates in their String representation in ISO Date Format
  */
object ISODate {

  def getCurrentDate() : String = {
    val df = new SimpleDateFormat("yyyy-MM-dd")
    return df.format(new Date())
  }

}