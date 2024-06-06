package game.utils

object OSDetection:
  val isMac: Boolean = System.getProperty("os.name").toLowerCase.contains("mac")
