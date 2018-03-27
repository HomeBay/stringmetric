import sbt.Keys._
import sbt._
import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

lazy val commonSettings: Seq[Setting[_]] = Defaults.coreDefaultSettings ++ Seq(
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
  publishTo := Some(
    if (isSnapshot.value)
      Opts.resolver.sonatypeSnapshots
    else
      Opts.resolver.sonatypeStaging
  ),
  resolvers ++= Seq(DefaultMavenRepository),
  scalaVersion := "2.12.4",
  crossScalaVersions := Seq("2.12.4", "2.11.1", "2.10.4"),
  crossVersion := CrossVersion.binary,
  releaseCrossBuild := true,
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    releaseStepCommandAndRemaining("+publishSigned"),
    setNextVersion,
    commitNextVersion,
    releaseStepCommand("sonatypeReleaseAll"),
    pushChanges
  ),
  releasePublishArtifactsAction := PgpKeys.publishSigned.value
)

lazy val root = (project in file("."))
  .settings(commonSettings) aggregate (core, cli)

lazy val core: Project = (project in file("core"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2-core" % "2.4.17" % "test"
    ),
    name := "stringmetric-core"
  )

lazy val cli: Project = (project in file("cli"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2-core" % "2.4.17" % "test"
    ),
    name := "stringmetric-cli"
  ) dependsOn core
