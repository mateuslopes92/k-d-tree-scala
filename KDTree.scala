//> using scala "3.3.1"

// Represents a point in k-dimensional space
case class Point(coords: Vector[Double]):
  def dimension: Int = coords.length

// Algebraic data type for KD-Tree
sealed trait KDTree
case object Empty extends KDTree

// Node contains a point, left/right subtrees, and the axis used to split
case class Node(
  point: Point,
  left: KDTree,
  right: KDTree,
  axis: Int // 0 comparing x and 1 comparing y
) extends KDTree

object KDTree:

  // Builds a balanced KD-Tree by recursively selecting the median point
  def build(points: Seq[Point], depth: Int = 0): KDTree =
    if points.isEmpty then Empty
    else
      val k = points.head.dimension
      val axis = depth % k // alternate axis at each level

      // Sort points by current axis and pick median
      val sorted = points.sortBy(_.coords(axis))
      val medianIndex = sorted.length / 2

      Node(
        point = sorted(medianIndex),
        left = build(sorted.take(medianIndex), depth + 1),
        right = build(sorted.drop(medianIndex + 1), depth + 1),
        axis = axis
      )

  // Euclidean distance between two points
  def distance(a: Point, b: Point): Double =
    math.sqrt(
      a.coords.zip(b.coords).map {
        case (x, y) => math.pow(x - y, 2)
      }.sum
    )

  // Finds the nearest point in the KD-Tree to the target
  def nearest(
    tree: KDTree,
    target: Point,
    best: Option[Point] = None
  ): Point =
    tree match
      case Empty =>
        // Return best candidate found so far
        best.get

      case Node(point, left, right, axis) =>
        // Update best if current node is closer
        val nextBest = best match
          case None => point
          case Some(b) =>
            if distance(target, point) < distance(target, b) then point else b

        // Choose which branch to explore first based on axis comparison
        val (nearBranch, farBranch) =
          if target.coords(axis) < point.coords(axis) then (left, right)
          else (right, left)

        // Explore the more promising side first
        val bestAfterNear = nearest(nearBranch, target, Some(nextBest))

        // Check if we need to explore the other side
        // Only if the splitting plane is closer than current best distance
        val shouldCheckFar =
          math.abs(target.coords(axis) - point.coords(axis)) <
            distance(target, bestAfterNear)

        if shouldCheckFar then
          nearest(farBranch, target, Some(bestAfterNear))
        else
          bestAfterNear

object Main:
  def main(args: Array[String]): Unit =
    val points = Seq(
      Point(Vector(2, 3)),
      Point(Vector(5, 4)),
      Point(Vector(9, 6)),
      Point(Vector(4, 7)),
      Point(Vector(8, 1)),
      Point(Vector(7, 2))
    )

    val tree = KDTree.build(points)

    val target = Point(Vector(9, 2))

    val nearestPoint = KDTree.nearest(tree, target)

    println("=== KD-Tree Demo ===")
    println(s"Target: ${target.coords}")
    println(s"Nearest: ${nearestPoint.coords}")
    println(s"Distance: ${KDTree.distance(target, nearestPoint)}")