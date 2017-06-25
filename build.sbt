name := "$name$"

version := "1.0"

val sparkVersion = "1.6.1"

lazy val commonSettings = Seq(
  organization := "com.csi",
  version := "1.0",
  scalaVersion := "2.10.6",
  libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion,
  libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion,
  libraryDependencies += "org.apache.spark" %% "spark-hive" % sparkVersion
)

lazy val sdiDeploymentUtils =
  (project in file("sdi_deployment_utils"))
    .settings(
        commonSettings,
        libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.0",
        libraryDependencies += "org.apache.httpcomponents" % "httpcore" % "4.3.2",
        libraryDependencies += "org.apache.httpcomponents" % "httpcore-nio" % "4.3.2",
        libraryDependencies += "org.apache.httpcomponents" % "httpasyncclient" % "4.0",
        libraryDependencies += "org.scalatest" %% "scalatest" % "2.3.0-SNAP2",
        name := "sdi_deployment_utils"
    )

lazy val etlProject =
  (project in file("$etlJobName$"))
    .settings(
      commonSettings,
      name := "$etlJobName$",
      libraryDependencies += "org.postgresql" % "postgresql" % "42.1.1",
      scalaSource in Compile := file("$etlDirectory$/src")
    ).dependsOn(sdiDeploymentUtils)

lazy val root =
  (project in file("."))
    .aggregate(etlProject)
