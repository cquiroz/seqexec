
name := Settings.Definitions.name

version := Settings.Definitions.version

scalaVersion := Settings.LibraryVersions.scala

organization in Global := "edu.gemini.ocs"

scalaVersion := Settings.LibraryVersions.scala

lazy val common = project.in(file("common"))
lazy val seqexec_web = project.in(file("seqexec-web"))

