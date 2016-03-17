import Settings._

name := "seqexec-web"

lazy val commonSettings = Seq(
  scalaVersion := LibraryVersions.scala,
  // Common libraries
  libraryDependencies ++= Seq(
    "com.lihaoyi"    %%% "upickle"     % "0.3.8",
    "org.scalaz"     %%% "scalaz-core" % "7.2.1",
    "org.scalatest"  %%% "scalatest"   % "3.0.0-M15" % "test",
    "org.scalacheck" %%% "scalacheck"  % "1.12.5" % "test"
  ),
  // Gemini repository
  resolvers += 
    "Gemini Repository" at "http://sbfswgosxdev-mp1.cl.gemini.edu:8081/artifactory/libs-release-local"
)

// a special crossProject for configuring a JS/JVM/shared structure
lazy val seqexec_web_shared = (crossProject.crossType(CrossType.Pure) in file("shared"))
  .settings(commonSettings: _*)
  .jvmSettings(
    name := "seqexec_web_shared_JVM"
  )

lazy val seqexec_web_shared_JVM = seqexec_web_shared.jvm

lazy val seqexec_web_shared_JS = seqexec_web_shared.js.settings(
  )

def includeInTrigger(f: java.io.File): Boolean =
  f.isFile && {
    val name = f.getName.toLowerCase
    name.endsWith(".scala") || name.endsWith(".js")
  }

lazy val seqexec_web = project.in(file("."))
  .aggregate(seqexec_web_server, seqexec_web_client, seqexec_web_shared_JS, seqexec_web_shared_JVM)

lazy val seqexec_web_client:Project = project.in(file("client"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-js"                      %%% "scalajs-dom" % LibraryVersions.scalaDom,
      "com.github.japgolly.scalajs-react" %%% "core"        % LibraryVersions.scalajsReact,
      "com.github.japgolly.scalajs-react" %%% "extra"       % LibraryVersions.scalajsReact,
      "com.github.japgolly.scalacss"      %%% "core"        % LibraryVersions.scalaCSS,
      "com.github.japgolly.scalacss"      %%% "ext-react"   % LibraryVersions.scalaCSS
    )
  )
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(seqexec_web_shared_JS)

// Reference to parent project. TODO put them in a single location
lazy val seqexec_server = project.in(file("common/edu.gemini.seqexec.server"))

lazy val seqexec_web_server = project.in(file("server"))
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
      //"edu.gemini.ocs"    %% "edu-gemini-seqexec-server" % "2016001.1.1",

      "org.scalaz"        %% "scalaz-concurrent"    % "7.1.6"
    ),

    // Settings to optimize the use of sbt-revolver
    
    // Allows to read the generate JS on client
    resources in Compile += (fastOptJS in (seqexec_web_client, Compile)).value.data,
    // Support stopping the running server
    mainClass in reStart := Some("edu.gemini.seqexec.web.server.play.WebServerLauncher"),
    // do a fastOptJS on reStart
    reStart <<= reStart dependsOn (fastOptJS in (seqexec_web_client, Compile)),
    // This settings makes reStart to rebuild if a scala.js file changes
    unmanagedResourceDirectories in Compile += (sourceDirectory in seqexec_web_client).value,
    // On recompilation only consider changes to .scala and .js files
    watchSources ~= { t:Seq[java.io.File] => {t.filter(includeInTrigger)} }

  )
  .dependsOn(seqexec_web_shared_JVM, seqexec_server)

