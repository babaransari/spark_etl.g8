name := "$name$"

version := "1.0"

val sparkVersion = "1.6.1"

lazy val commonSettings = Seq(
  organization := "com.csi",
  version := "1.0",
  scalaVersion := "2.10.6",
  resolvers += "Maven SDI Repository" at "file://$localMavenRepo$",
  libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion,
  libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion,
  libraryDependencies += "com.cambridgesemantics.application.sdi.deployment" % "sdi-deployment-utils" % "1.0",
  libraryDependencies += "org.postgresql" % "postgresql" % "42.1.1"
)

lazy val etlProject =
  (project in file("$etlProjectName$"))
    .settings(
      commonSettings,
      name := "$etlProjectName$",
      scalaSource in Compile := file("$etlSourceDirectory$")
    )

lazy val root =
  (project in file("."))
    .aggregate(etlProject)
