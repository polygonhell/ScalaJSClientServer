
import NativePackagerKeys._
import spray.revolver.RevolverPlugin._

val app = crossProject.settings(
  unmanagedSourceDirectories in Compile +=
    baseDirectory.value  / "shared" / "main" / "scala",
  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "scalatags" % "0.4.5",
    "com.lihaoyi" %%% "upickle" % "0.2.6",
    "com.lihaoyi" %%% "autowire" % "0.2.4"
  ),
  scalaVersion := "2.11.5"
).jsSettings(
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.0"
  )
).jvmSettings(
  libraryDependencies ++= Seq(
    "io.spray" %% "spray-can" % "1.3.2",
    "io.spray" %% "spray-routing" % "1.3.2",
    "com.typesafe.akka" %% "akka-actor" % "2.3.6"
  )
)

val revolverSettings = Revolver.settings


lazy val appJS = app.js
lazy val appJVM = app.jvm
  .settings(revolverSettings :_*)
  .settings((resources in Compile) += (fastOptJS in (appJS, Compile)).value.data)

