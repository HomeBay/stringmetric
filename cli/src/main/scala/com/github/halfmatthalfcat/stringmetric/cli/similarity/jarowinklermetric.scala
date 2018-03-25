package com.github.halfmatthalfcat.stringmetric.cli.similarity

import com.github.halfmatthalfcat.stringmetric.similarity.JaroWinklerMetric
import com.github.halfmatthalfcat.stringmetric.cli._

case object jarowinklermetric extends Command(
	(opts) =>
		"Compares two strings to calculate the Jaro-Winkler distance." + Ls + Ls +
		"Syntax:" + Ls +
		Tab + "jarowinklermetric [Options] string1 string2..." + Ls + Ls +
		"Options:" + Ls +
		Tab + "-h, --help" + Ls +
		Tab + Tab + "Outputs description, syntax, and opts.",
	(opts) => opts.contains('dashless) && (opts('dashless): Array[String]).length == 2,
	(opts) => {
		val strings: Array[String] = opts('dashless)

		JaroWinklerMetric.compare(strings(0), strings(1))
			.map(_.toString)
			.getOrElse("not comparable")
	}
) { override def main(args: Array[String]): Unit = super.main(args) }
