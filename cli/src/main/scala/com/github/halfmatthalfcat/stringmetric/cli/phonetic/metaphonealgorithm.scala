package com.github.halfmatthalfcat.stringmetric.cli.phonetic

import com.github.halfmatthalfcat.stringmetric.cli._
import com.github.halfmatthalfcat.stringmetric.phonetic.MetaphoneAlgorithm

case object metaphonealgorithm extends Command(
	(opts) =>
		"Returns the phonetic representation of the passed string, per the Metaphone algorithm." + Ls + Ls +
		"Syntax:" + Ls +
		Tab + "metaphonealgorithm [Options] string..." + Ls + Ls +
		"Options:" + Ls +
		Tab + "-h, --help" + Ls +
		Tab + Tab + "Outputs description, syntax, and opts.",
	(opts) => opts.contains('dashless) && (opts('dashless): Array[String]).length == 1,
	(opts) => MetaphoneAlgorithm.compute(opts('dashless)).getOrElse("not computable")
) { override def main(args: Array[String]): Unit = super.main(args) }
