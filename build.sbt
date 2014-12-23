import bintray.Keys._

name := "Spark Pre-Processing"

version := "0.0.1-SNAPSHOT"

organization := "org.apache.spark.preprocessing"

licenses in ThisBuild += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

scalaVersion := "2.11.4"

crossScalaVersions in ThisBuild := Seq("2.10.4", "2.11.4")

scalacOptions += "-deprecation"

scalacOptions += "-feature"


// Resolvers

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

// Library Dependencies

libraryDependencies ++= Seq(
  "org.scalaz"        %% "scalaz-core"   % "7.1.0",
  "org.apache.spark"  %% "spark-core"    % "1.2.0"
)

// Test Dependencies

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest"   % "2.2.1" % "test"
)

// Configure publishing to bintray

bintrayPublishSettings

repository in bintray := "releases"

bintrayOrganization in bintray := None
