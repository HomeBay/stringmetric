package com.github.halfmatthalfcat.stringmetric.phonetic

import com.github.halfmatthalfcat.stringmetric.Alphabet.Alpha
import com.github.halfmatthalfcat.stringmetric.StringMetric

case object RefinedSoundexMetric extends StringMetric[Boolean] {
	override def compare(a: Array[Char], b: Array[Char]): Option[Boolean] =
		if (a.length == 0 || !(Alpha isSuperset a.head) || b.length == 0 || !(Alpha isSuperset b.head)) None
		else if (a.head.toLower != b.head.toLower) Some(false)
		else RefinedSoundexAlgorithm.compute(a).filter(_.length > 0).flatMap { rse1 =>
			RefinedSoundexAlgorithm.compute(b).filter(_.length > 0).map(rse1.sameElements(_))
		}

	override def compare(a: String, b: String): Option[Boolean] = compare(a.toCharArray, b.toCharArray)
}
