package com.github.halfmatthalfcat.stringmetric

trait Tokenizer[A] {
	def tokenize(a: A): Option[Array[A]]
}
