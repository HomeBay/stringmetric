import sbt._
import Keys._

object CoreBuild extends Build {
	lazy val root = Project("stringmetric", file("."),
		settings = Defaults.defaultSettings ++ Seq(
			credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
			name := "stringmetric",
			organization := "com.github.halfmatthalfcat",
			pomExtra :=
				<url>http://rockymadden.com/stringmetric/</url>
					<licenses>
						<license>
							<name>MIT</name>
							<distribution>repo</distribution>
						</license>
					</licenses>
					<scm>
						<url>git@github.com:halfmatthalfcat/stringmetric.git</url>
						<connection>scm:git:git@github.com:halfmatthalfcat/stringmetric.git</connection>
					</scm>
					<developers>
						<developer>
							<id>rockymadden</id>
							<name>Rocky Madden</name>
							<url>http://rockymadden.com/</url>
						</developer>
						<developer>
							<id>halfmatthalfcat</id>
							<name>Matt Oliver</name>
							<url>http://mjoliver.co/</url>
						</developer>
					</developers>,
			publishMavenStyle := true,
			publishTo := Some("Sonatype" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"),
			resolvers ++= Seq(DefaultMavenRepository),
			scalaVersion := "2.12.4",
			crossScalaVersions := Seq("2.12.4", "2.11.1", "2.10.4"),
			crossVersion := CrossVersion.binary,
			version := "0.28.0"
		)
	).aggregate(core, cli)

	lazy val core: Project = Project("core", file("core"),
		settings = (root.settings: Seq[sbt.Def.Setting[_]]) ++ Seq(
			libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.0.2" % "test"),
			name := "stringmetric-core"
		)
	)

	lazy val cli: Project = Project("cli", file("cli"),
		settings = (root.settings: Seq[sbt.Def.Setting[_]]) ++ Seq(
			libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.0.2" % "test"),
			name := "stringmetric-cli"
		)
	).dependsOn(core)
}
