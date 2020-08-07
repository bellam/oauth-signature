crossScalaVersions := Seq("2.13.1", "2.12.8", "2.11.12")
scalaVersion := crossScalaVersions.value.head

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

pgpPublicRing := file("ci/pubring.asc")
pgpSecretRing := file("ci/secring.asc")
pgpPassphrase := sys.env.get("PGP_PASSPHRASE").map(_.toArray)
