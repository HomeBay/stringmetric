package com.github.halfmatthalfcat.stringmetric

trait Algorithm[A] {
	def compute(a: A): Option[A]
}
