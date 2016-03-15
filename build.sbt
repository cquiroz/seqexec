enablePlugins(ScalaJSPlugin)

name := "seqexec-web"

// Versions
val reactJsVersion = "0.4.0"

lazy val commonSettings = Seq(
  organization := "edu.gemini",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.7",
  // Common libraries
  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "upickle" % "0.3.8"
  ),
  // Gemini repository
  resolvers += 
    "Gemini Repository" at "http://sbfswgosxdev-mp1.cl.gemini.edu:8081/artifactory/libs-release-local"
)

lazy val root = project.in(file("."))
  .aggregate(seqexec_web_JS, seqexec_web_JVM)
  .settings(commonSettings: _*)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val seqexec_web = crossProject.in(file("."))
  .settings(commonSettings: _*)
  .jvmSettings(
    libraryDependencies ++= Seq(
      // NOTE this doesn't work on OSGI, http4s is not OSGi friendly
      // http4s
      "org.http4s"        %% "http4s-dsl"           % "0.12.0",
      "org.http4s"        %% "http4s-blaze-server"  % "0.12.0",

      // Play
      "com.typesafe.play" %% "play"                 % "2.4.6",
      "com.typesafe.play" %% "play-netty-server"    % "2.4.6",

      // OCS
      "edu.gemini.ocs"    %% "edu-gemini-seqexec-server" % "2016001.1.1",

      "org.scalaz"        %% "scalaz-core"          % "7.1.6",
      "org.scalaz"        %% "scalaz-concurrent"    % "7.1.6"
    )
  )
  .jsSettings(
    //
    libraryDependencies ++= Seq(
      "org.scala-js"                      %%% "scalajs-dom" % "0.8.0",
      "com.github.japgolly.scalajs-react" %%% "core"        % reactJsVersion,
      "com.github.japgolly.scalajs-react" %%% "extra"       % reactJsVersion,
      "com.github.japgolly.scalacss"      %%% "core"        % "0.4.0",
      "com.github.japgolly.scalacss"      %%% "ext-react"   % "0.4.0",
      "com.github.japgolly.fork.scalaz"   %%% "scalaz-core" % "7.2.0", // note that the scalaz version for scala.js is a bit old
      "com.lihaoyi"                       %%% "utest"       % "0.3.1" % "test"
    )
  )

lazy val seqexec_web_JS = seqexec_web.js
lazy val seqexec_web_JVM = seqexec_web.jvm