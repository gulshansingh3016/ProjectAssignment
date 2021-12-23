ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.7"

lazy val root = (project in file("."))
  .settings(
    name := "DirectoryMonitorAkka"
  )
val AkkaVersion = "2.6.18"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test
)
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test
)

val AkkaHttpVersion = "10.2.7"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
)
//libraryDependencies += "ch.timo-sch mid" %% "slf4s-api" % "1.7.30.2"

val logging  = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4"
val logback  = "ch.qos.logback" % "logback-classic" % "1.2.9"

//libraryDependencies ++= Seq("org.slf4j" % "slf4j-api" % "1.7.32",
//  "org.slf4j" % "slf4j-simple" % "1.7.32")
libraryDependencies ++= Seq(
"com.typesafe.akka" %% "akka-remote" % AkkaVersion,
"com.typesafe.akka" %% "akka-cluster" % AkkaVersion,
"com.typesafe.akka" %% "akka-cluster-sharding" % AkkaVersion,
"com.typesafe.akka" %% "akka-cluster-tools" % AkkaVersion,
"io.aeron" % "aeron-driver" % "1.37.0",
"io.aeron" % "aeron-client" % "1.37.0"
)