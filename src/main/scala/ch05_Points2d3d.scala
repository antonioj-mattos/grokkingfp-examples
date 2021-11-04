/** Given lists of coordinates,
  * create all possible 2D & 3D points.
  */
object ch05_Points2d3d extends App {
  case class Point(x: Int, y: Int)

  val points = List(1).flatMap(x =>
    List(-2, 7).map(y =>
      Point(x, y)
    )
  )
  check(points).expect(List(Point(1, -2), Point(1, 7)))
  check(List(1).flatMap(x => List(-2, 7, 10).map(y => Point(x, y)))).expectThat(_.size == 3)
  check(List(1, 2).flatMap(x => List(-2, 7).map(y => Point(x, y)))).expectThat(_.size == 4)
  check(List(1, 2).flatMap(x => List(-2, 7, 10).map(y => Point(x, y)))).expectThat(_.size == 6)
  check(List.empty[Int].flatMap(x => List(-2, 7).map(y => Point(x, y)))).expectThat(_.size == 0)
  check(List(1).flatMap(x => List.empty[Int].map(y => Point(x, y)))).expectThat(_.size == 0)

  val xs = List(1)
  val ys = List(-2, 7)

  check(xs.flatMap(x =>
    ys.map(y =>
      Point(x, y)
    )
  )).expect(List(Point(1, -2), Point(1, 7)))

  check(for {
    x <- xs
    y <- ys
  } yield Point(x, y)).expect(List(Point(1, -2), Point(1, 7)))

  val zs = List(3, 4)

  case class Point3d(x: Int, y: Int, z: Int)

  check(
    for {
      x <- xs
      y <- ys
      z <- zs
    } yield Point3d(x, y, z)
  ).expect(List(Point3d(1, -2, 3), Point3d(1, -2, 4), Point3d(1, 7, 3), Point3d(1, 7, 4)))

  check(
    xs.flatMap(x =>
      ys.flatMap(y =>
        zs.map(z =>
          Point3d(x, y, z)
        )
      )
    )
  ).expect(List(Point3d(1, -2, 3), Point3d(1, -2, 4), Point3d(1, 7, 3), Point3d(1, 7, 4)))
}
