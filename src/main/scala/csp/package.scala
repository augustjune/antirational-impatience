package object csp {
  type Domain[T] = Stream[T]
  type Solution[T] = List[T]

  type Solver[T] = (Problem[T]) => Option[Solution[T]]
}
