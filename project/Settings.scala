/**
 * Application settings
 */
object Settings {
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

  /** global dependency versions */
  object versions {
    val scala = "2.11.7"
    val scalaDom = "0.9.0"
    val scalajsReact = "0.10.4"
    val scalaCSS = "0.4.0"
  }
}