package csp

package object nQueens {
  type Domain[T] = Stream[T]
  type Solution[T] = List[T]

  type Solver[T] = (List[Domain[T]], Solution[T] => Boolean) => Option[Solution[T]]
//  type Solver[T] = (Problem[T]) => Option[Solution[T]]
}
