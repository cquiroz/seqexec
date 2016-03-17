
name := "common"

lazy val common = project.in(file("."))
  .aggregate(seqexec_server)

lazy val seqexec_server:Project = project.in(file("edu.gemini.seqexec.server"))
