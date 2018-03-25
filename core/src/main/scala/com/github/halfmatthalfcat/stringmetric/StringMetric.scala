package com.github.halfmatthalfcat.stringmetric

import com.github.halfmatthalfcat.stringmetric.phonetic._
import com.github.halfmatthalfcat.stringmetric.similarity._

import scala.language.implicitConversions

trait StringMetric[A] extends Metric[Array[Char], A] {
	def compare(a: String, b: String): Option[A]
}

object StringMetric {
	val DiceSorensen = DiceSorensenMetric
	val Hamming = similarity.HammingMetric
	val Jaccard = JaccardMetric
	val Jaro = JaroMetric
	val JaroWinkler = JaroWinklerMetric
	val Levenshtein = LevenshteinMetric
	val Metaphone = MetaphoneMetric
	val NGram = similarity.NGramMetric
	val Nysiis = NysiisMetric
	val Overlap = similarity.OverlapMetric
	val RefinedNysiis = RefinedNysiisMetric
	val RefinedSoundex = RefinedSoundexMetric
	val Soundex = SoundexMetric
	val WeightedLevenshtein = similarity.WeightedLevenshteinMetric

	implicit def toStringMetricDecorator[A](sa: StringMetric[A]): StringMetricDecorator[A] =
		new StringMetricDecorator[A](sa)

	def compareWithDiceSorensen(n: Int)(a: Array[Char], b: Array[Char]) = DiceSorensen(n).compare(a, b)

	def compareWithHamming(a: Array[Char], b: Array[Char]) = Hamming.compare(a, b)

	def compareWithJaccard(n: Int)(a: Array[Char], b: Array[Char]) = Jaccard(n).compare(a, b)

	def compareWithJaro(a: Array[Char], b: Array[Char]) = Jaro.compare(a, b)

	def compareWithJaroWinkler(a: Array[Char], b: Array[Char]) = JaroWinkler.compare(a, b)

	def compareWithLevenshtein(a: Array[Char], b: Array[Char]) = Levenshtein.compare(a, b)

	def compareWithMetaphone(a: Array[Char], b: Array[Char]) = Metaphone.compare(a, b)

	def compareWithNGram(n: Int)(a: Array[Char], b: Array[Char]) = NGram(n).compare(a, b)

	def compareWithNysiis(a: Array[Char], b: Array[Char]) = Nysiis.compare(a, b)

	def compareWithOverlap(n: Int)(a: Array[Char], b: Array[Char]) = Overlap(n).compare(a, b)

	def compareWithRefinedNysiis(a: Array[Char], b: Array[Char]) = RefinedNysiis.compare(a, b)

	def compareWithRefinedSoundex(a: Array[Char], b: Array[Char]) = RefinedSoundex.compare(a, b)

	def compareWithSoundex(a: Array[Char], b: Array[Char]) = Soundex.compare(a, b)

	def compareWithWeightedLevenshtein(delete: BigDecimal, insert: BigDecimal, substitute: BigDecimal)
		(a: Array[Char], b: Array[Char]) =

		WeightedLevenshtein(delete, insert, substitute).compare(a, b)
}
