import sbt._

object Versions {

  def specs2(scalaBinaryVersion: String): ModuleID = scalaBinaryVersion match {
    case "2.12" => "org.specs2" %% "specs2-core" % "4.0.2" % "test"
    case _ => "org.specs2" %% "specs2-core" % "3.1.1" % "test"
  }

}
