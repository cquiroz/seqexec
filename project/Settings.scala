import sbt._

/**
 * Application settings
 */
object Settings {
  object Definitions {
    /** The name of the application */
    val name = "seqexec"

    /** Top level version */
    val version = "1.0.0"

    /** Options for the scala compiler */
    val scalacOptions = Seq(
      "-Xlint",
      "-unchecked",
      "-deprecation",
      "-feature"
    )
  }

  /** global dependency versions */
  object LibraryVersions {
    val scala = "2.11.7"
    val scalaDom = "0.9.0"
    val scalajsReact = "0.10.4"
    val scalaCSS = "0.4.0"

    val ocsVersion = "2016001.1.1"
  }
}
