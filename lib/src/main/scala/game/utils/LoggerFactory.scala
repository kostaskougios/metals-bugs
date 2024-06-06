package game.utils

import org.slf4j.Logger

object LoggerFactory:
  def loggerFor(a: Any): Logger = org.slf4j.LoggerFactory.getLogger(a.getClass)
