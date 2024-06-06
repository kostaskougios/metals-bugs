package game.utils

object Timings:
  def time[R](f: => R): (Long, R) =
    val start = System.currentTimeMillis()
    val r     = f
    val dt    = System.currentTimeMillis() - start
    (dt, r)

  def printTime[R](f: => R): R =
    val (dt, r) = time(f)
    val e       = new RuntimeException
    val st      = e.getStackTrace()
    val at      = st(1)
    println(s"$at : $dt ms")
    r
