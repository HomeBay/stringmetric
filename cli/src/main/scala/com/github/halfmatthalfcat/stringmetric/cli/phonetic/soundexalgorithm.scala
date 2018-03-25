package com.github.halfmatthalfcat.stringmetric.cli.phonetic

import com.github.halfmatthalfcat.stringmetric.cli._
import com.github.halfmatthalfcat.stringmetric.phonetic.SoundexAlgorithm

case object soundexalgorithm extends Command(
	(opts) =>
		"Returns the phonetic representation of the passed string, per the Soundex algorithm." + Ls + Ls +
		"Syntax:" + Ls +
		Tab + "soundexalgorithm [Options] string..." + Ls + Ls +
		"Options:" + Ls +
		Tab + "-h, --help" + Ls +
		Tab + Tab + "Outputs description, syntax, and opts.",
	(opts) => opts.contains('dashless) && (opts('dashless): Array[String]).length == 1,
	(opts) => SoundexAlgorithm.compute(opts('dashless)).getOrElse("not computable")
) { override def main(args: Array[String]): Unit = super.main(args) }
