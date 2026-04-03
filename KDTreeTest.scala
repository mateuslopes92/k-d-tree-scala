//> using scala "3.3.1"
//> using dep "org.scalatest::scalatest:3.2.18"

import org.scalatest.funsuite.AnyFunSuite

class KDTreeTest extends AnyFunSuite {

  val points = Seq(
    Point(Vector(2, 3)),
    Point(Vector(5, 4)),
    Point(Vector(9, 6)),
    Point(Vector(4, 7)),
    Point(Vector(8, 1)),
    Point(Vector(7, 2))
  )

  val tree = KDTree.build(points)

  test("KDTree should build without errors") {
    assert(tree != Empty)
  }

  test("Nearest neighbor should be correct") {
    val target = Point(Vector(9, 2))
    val nearest = KDTree.nearest(tree, target)

    assert(nearest == Point(Vector(8, 1)))
  }

  test("Distance calculation should be correct") {
    val a = Point(Vector(0, 0))
    val b = Point(Vector(3, 4))

    assert(KDTree.distance(a, b) == 5.0)
  }
}