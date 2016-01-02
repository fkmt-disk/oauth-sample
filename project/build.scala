import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._

object AppBuild extends Build {
  
  val Organization = "orz"
  val Name = "oauth-sample"
  val Version = "1.0.0-SNAPSHOT"
  val ScalaVersion = "2.11.7"
  val ScalatraVersion = "2.3.1"
  
  lazy val project = Project(
    Name,
    file("."),
    settings = ScalatraPlugin.scalatraSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      
      scalaVersion := ScalaVersion,
      
      scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked"),
      
      resolvers += Classpaths.typesafeReleases,
      resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
      
      libraryDependencies += "org.scalatra" %% "scalatra" % ScalatraVersion,
      libraryDependencies += "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
      libraryDependencies += "org.scalatra" %% "scalatra-json" % ScalatraVersion,
      libraryDependencies += "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
      
      libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.2.11",

      libraryDependencies += "org.scalaz" % "scalaz-core_2.11" % "7.2.0",

      libraryDependencies += "com.typesafe" % "config" % "1.3.0",

      //libraryDependencies += "com.google.apis" % "google-api-services-oauth2" % "v2-rev98-1.21.0",
      libraryDependencies += "com.google.api-client" % "google-api-client" % "1.21.0",

      libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2" % "runtime",
      
      libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "9.2.10.v20150310" % "container",
      
      libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
      
      mainClass in (Compile, packageBin) := None
    )
  )
  
}
