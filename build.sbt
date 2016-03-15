name := "seqexec-web"

// Versions
val reactJsVersion = "0.10.4"

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

// a special crossProject for configuring a JS/JVM/shared structure
lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared"))
  .settings(commonSettings: _*)

lazy val sharedJVM = shared.jvm.settings(
    name := "sharedJVM",
    libraryDependencies +=
      "org.scalaz"        %% "scalaz-core"          % "7.1.6"
  )

lazy val sharedJS = shared.js.settings(
    name := "sharedJS",
    libraryDependencies +=
      "com.github.japgolly.fork.scalaz"   %%% "scalaz-core" % "7.2.0"
  )

def includeInTrigger(f: java.io.File): Boolean =
  f.isFile && {
    val name = f.getName.toLowerCase
    name.endsWith(".scala") || name.endsWith(".js")
  }

lazy val client:Project = project.in(file("js"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-js"                      %%% "scalajs-dom" % "0.8.0",
      "com.github.japgolly.scalajs-react" %%% "core"        % reactJsVersion,
      "com.github.japgolly.scalajs-react" %%% "extra"       % reactJsVersion,
      "com.github.japgolly.scalacss"      %%% "core"        % "0.4.0",
      "com.github.japgolly.scalacss"      %%% "ext-react"   % "0.4.0",
      "com.lihaoyi"                       %%% "utest"       % "0.3.1" % "test"
    )
  )
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(sharedJS)

lazy val server = project.in(file("jvm"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      // http4s
      "org.http4s"        %% "http4s-dsl"           % "0.12.0",
      "org.http4s"        %% "http4s-blaze-server"  % "0.12.0",

      // Play
      "com.typesafe.play" %% "play"                 % "2.4.6",
      "com.typesafe.play" %% "play-netty-server"    % "2.4.6",

      // OCS
      "edu.gemini.ocs"    %% "edu-gemini-seqexec-server" % "2016001.1.1",

      "org.scalaz"        %% "scalaz-concurrent"    % "7.1.6"
    ),

    // Settings to optimize the use of sbt-revolver
    
    // Allows to read the generate JS on client
    resources in Compile += (fastOptJS in (client, Compile)).value.data,
    // Support stopping the running server
    mainClass in reStart := Some("edu.gemini.seqexec.web.server.play.WebServerLauncher"),
    // do a fastOptJS on reStart
    reStart <<= reStart dependsOn (fastOptJS in (client, Compile)),
    // This settings makes reStart to rebuild if a scala.js file changes
    unmanagedResourceDirectories in Compile += (sourceDirectory in client).value,
    // On recompilation only consider changes to .scala and .js files
    watchSources ~= { t:Seq[java.io.File] => {t.filter(includeInTrigger)} }

  )
  .dependsOn(sharedJVM)

