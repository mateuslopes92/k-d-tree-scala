# KDTree with Scala

A KD-Tree (K-Dimensional Tree) is a space-partitioning data structure used to organize points in a space with `k` dimensions.
It is especially useful for efficiently solving nearest neighbor search problems, such as finding the closest point on a plane.

---

## Concepts
- `k` represents the number of dimensions (e.g., 2D, 3D)
- Each level of the tree splits the space using a different axis:
  - depth 0 → axis 0 (x)
  - depth 1 → axis 1 (y)
  - depth 2 → axis 0 again
- The tree is built by recursively selecting the median point on the current axis
- Left subtree contains points smaller on that axis
- Right subtree contains points greater on that axis

---

## Project Setup
Install Scala CLI:

```bash
brew install scala-cli
```

Run the project:
```bash
scala-cli run .
```

Run tests:
```bash
scala-cli test .
```
---

## What is the use case for this DS?

KD-Trees are used when working with multi-dimensional spatial data.

Common use cases:
- Nearest neighbor search (e.g., closest location)
- Geographic systems (latitude/longitude queries)
- Machine learning (K-Nearest Neighbors - KNN)
- Collision detection in games
- Computer vision and robotics

---

## What are the pros and cons?
### Pros
- Efficient nearest neighbor search (O(log n) average)
- Reduces need for brute-force search (O(n))
- Works well for low to medium dimensions

### Cons
- Can degrade to O(n) if the tree becomes unbalanced
- Not ideal for very high dimensions (curse of dimensionality)
- More complex to implement than basic data structures
- Insertions can unbalance the tree

---

## Code

The implementation includes:
- `Point`: represents a point in k-dimensional space
- `KDTree`: algebraic data type (Empty | Node)
- `build`: constructs a balanced KD-Tree
- `distance`: calculates Euclidean distance
- `nearest`: finds the closest point to a target

---

## Tests

Tests are implemented using ScalaTest and cover:

- Tree construction
- Nearest neighbor correctness
- Distance calculation

Run tests with:
```bash
scala-cli test .
```

#### Example

Run the project:
```bash
scala-cli run .
```

Example output:

```
=== KD-Tree Demo ===
Target: Vector(9.0, 2.0)
Nearest: Vector(8.0, 1.0)
Distance: 1.4142
```