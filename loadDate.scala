import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

/**
  * Calculates the load timestamp based on the provided incremental timestamp or the current timestamp as default parameter.
  *
  * @param incremental_ts The input incremental timestamp in the format "yyyy-MM-dd HH:mm:ss."
  *                 If not provided, the current timestamp will be used.
  * @return The load date in format "yyyy-MM-dd."
  */

def getLoadTs(incremental_ts: String = ""): String = {
  val inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  val outputFormat = new SimpleDateFormat("yyyy-MM-dd")

  val parsedDate: Date = if (incremental_ts.isEmpty) {
    new Date() // Use the current timestamp when incremental_ts is not provided
  } else {
    inputFormat.parse(incremental_ts)
  }

  val calendar: Calendar = Calendar.getInstance()
  calendar.setTime(parsedDate)
  calendar.add(Calendar.DAY_OF_MONTH, -2)
  val subtractedDate: Date = calendar.getTime()
  outputFormat.format(subtractedDate)
}

/*
val defaultLoadTs = getLoadTs() // Uses current timestamp as default
val customLoadTs = getLoadTs("2023-10-31 12:00:00") // Provides custom incremental timestamp
*/


//---------------------------------------------------------------------------------------------------
import java.text.SimpleDateFormat

def formatDate(incremental_ts: String): String = {

    import java.text.SimpleDateFormat
    val inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val outputFormat = new SimpleDateFormat("yyyy-MM-dd")
    val formattedDate = outputFormat.format(inputFormat.parse(incremental_ts))

    return formattedDate
  }
