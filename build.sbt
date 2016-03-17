name := Settings.name

organization in Global := "edu.gemini.ocs"

scalaVersion := Settings.versions.scala

lazy val seqexec_web = project.in(file("seqexec-web"))
