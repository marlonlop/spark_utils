import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

/**
  * Calculates the load timestamp based on the provided incremental timestamp or the current timestamp as default parameter.
  *
  * @param incremental_ts The input incremental timestamp in the format "yyyy-MM-dd HH:mm:ss."
  * @param currentTs The current timestamp (optional) in the format "yyyy-MM-dd HH:mm:ss."
  *                 If not provided, the current timestamp will be used.
  * @return The load date in format "yyyy-MM-dd."
  */

def getLoadTs(incremental_ts: String, currentTs: String = ""): String = {
  val inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  val outputFormat = new SimpleDateFormat("yyyy-MM-dd")

  val parsedDate: Date = if (currentTs.isEmpty) {
    // If currentTs is not provided, use the current timestamp
    new Date()
  } else {
    // Parse the provided incremental_ts
    inputFormat.parse(incremental_ts)
  }

  val calendar: Calendar = Calendar.getInstance()
  calendar.setTime(parsedDate)

  // Subtract one day
  calendar.add(Calendar.DAY_OF_MONTH, -1)

  // Get the resulting date
  val subtractedDate: Date = calendar.getTime()

  // Format the subtracted date to "yyyy-MM-dd" format
  val formattedDate = outputFormat.format(subtractedDate)

  formattedDate
}

/*
//e.g.
val incremental_ts = "2023-10-31 12:00:00"
val defaultLoadTs = getLoadTs(incremental_ts) // Uses current timestamp as default
val customLoadTs = getLoadTs(incremental_ts, "2023-10-25 12:00:00") // Provides custom timestamp
*/
