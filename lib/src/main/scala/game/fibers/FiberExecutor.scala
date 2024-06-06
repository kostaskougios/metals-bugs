package game.fibers

import java.util.concurrent._
import scala.util.Using.Releasable

// https://wiki.openjdk.org/display/loom/Getting+started
// https://openjdk.org/jeps/444
class FiberExecutor private (executorService: ExecutorService):
  def submit[R](f: => R): Fiber[R] =
    val c: Callable[R] = () => f
    val fiber          = executorService.submit(c)
    Fiber(fiber)

  def runAllAndAwait[R1, R2, R3](f1: => R1, f2: => R2, f3: => R3): (R1, R2, R3) =
    val fib1 = submit(f1)
    val fib2 = submit(f2)
    val fib3 = submit(f3)
    (fib1.get(), fib2.get(), fib3.get())

  def runAllAndAwait[R1, R2, R3, R4](f1: => R1, f2: => R2, f3: => R3, f4: => R4): (R1, R2, R3, R4) =
    val fib1 = submit(f1)
    val fib2 = submit(f2)
    val fib3 = submit(f3)
    val fib4 = submit(f4)
    (fib1.get(), fib2.get(), fib3.get(), fib4.get())

  def runAllAndAwait[R1, R2, R3, R4, R5](f1: => R1, f2: => R2, f3: => R3, f4: => R4, f5: => R5): (R1, R2, R3, R4, R5) =
    val fib1 = submit(f1)
    val fib2 = submit(f2)
    val fib3 = submit(f3)
    val fib4 = submit(f4)
    val fib5 = submit(f5)
    (fib1.get(), fib2.get(), fib3.get(), fib4.get(), fib5.get())

  def shutdown(): Unit = executorService.shutdown()

object FiberExecutor:
  val fiberExecutor: FiberExecutor = apply()

  def apply(): FiberExecutor =
    val executor = Executors.newVirtualThreadPerTaskExecutor
    new FiberExecutor(executor)

  given Releasable[FiberExecutor] = resource => resource.shutdown()

  def withFiberExecutor[R](f: FiberExecutor => R): R =
    val executor = Executors.newVirtualThreadPerTaskExecutor
    try
      val fe = new FiberExecutor(executor)
      f(fe)
    finally executor.shutdown()
