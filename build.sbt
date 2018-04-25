val Http4sVersion = "0.18.8"
val Specs2Version = "4.0.3"
val LogbackVersion = "1.2.3"

lazy val root = (project in file("."))
  .settings(
    organization := "com.stephenn",
    name := "http4s-index",
    scalaVersion := "2.12.4",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "org.specs2" %% "specs2-core" % Specs2Version % "test",
      "ch.qos.logback" % "logback-classic" % LogbackVersion
    ),
    homepage := Some(
      url("https://github.com/stephennancekivell/http4s-index")),
    scmInfo := Some(
      ScmInfo(url("https://github.com/stephennancekivell/http4s-index"),
              "git@github.com:stephennancekivell/http4s-index.git")),
    developers := List(
      Developer("stephennancekivell",
                "Stephen Nancekivell",
                "stephennancekivell@gmail.com",
                url("https://stephenn.com"))),
    licenses += ("Apache-2.0", url(
      "http://www.apache.org/licenses/LICENSE-2.0")),
    publishMavenStyle := true,
    publishTo := sonatypePublishTo.value
  )

releasePublishArtifactsAction := PgpKeys.publishSigned.value
