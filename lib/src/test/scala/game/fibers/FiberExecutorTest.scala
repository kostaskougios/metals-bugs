package game.fibers

import game.fibers.FiberExecutor.fiberExecutor
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers._

import java.util.concurrent.ExecutionException

class FiberExecutorTest extends AnyFunSuiteLike:
  test("get()"):
    FiberExecutor.withFiberExecutor: executor =>
      executor.submit(5).get() should be(5)

  test("get() when exception"):
    FiberExecutor.withFiberExecutor: executor =>
      an[ExecutionException] should be thrownBy
        executor
          .submit:
            throw new IllegalArgumentException()
          .get()

  test("runAllAndAwait(3)"):
    fiberExecutor.runAllAndAwait(10, 20, 30) should be((10, 20, 30))
  test("runAllAndAwait(4)"):
    fiberExecutor.runAllAndAwait(10, 20, 30, 40) should be((10, 20, 30, 40))
  test("runAllAndAwait(5)"):
    fiberExecutor.runAllAndAwait(10, 20, 30, 40, 50) should be((10, 20, 30, 40, 50))
