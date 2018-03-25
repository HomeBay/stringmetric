package com.github.halfmatthalfcat.stringmetric.cli.similarity

import com.github.halfmatthalfcat.stringmetric.similarity.NGramMetric
import com.github.halfmatthalfcat.stringmetric.cli._

case object ngrammetric extends Command(
	(opts) =>
		"Compares the similarity of two strings using an N-Gram similarity index." + Ls + Ls +
		"Syntax:" + Ls +
		Tab + "ngrammetric [Options] string1 string2..." + Ls + Ls +
		"Options:" + Ls +
		Tab + "-h, --help" + Ls +
		Tab + Tab + "Outputs description, syntax, and options." +
		Tab + "--n" + Ls +
		Tab + Tab + "The n.",
	(opts) => opts.contains('dashless) && (opts('dashless): Array[String]).length == 2 &&
		opts.contains('n) && (opts('n): Int) >= 1,
	(opts) => {
		val strings: Array[String] = opts('dashless)
		val n: Int = opts('n)

		NGramMetric(n).compare(strings(0), strings(1))
			.map(_.toString)
			.getOrElse("not comparable")
	}
) { override def main(args: Array[String]): Unit = super.main(args) }
