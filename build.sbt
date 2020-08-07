scalaVersion := "2.13.1"

name := "oauth-signature"
organization := "com.github.bellam"
licenses := Seq(
  "APL2" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt")
)
description := "Generate OAuth1.0 signature using HMAC-SHA algorithm in Scala"

// publish to the sonatype repository
import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(
  GitHubHosting("bellam", "oauth-signature", "mihir.bell@gmail.com")
)
publishTo := sonatypePublishTo.value

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test"
