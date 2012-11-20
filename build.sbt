organization := "com.ssss"

name := "s4"

version := "0.1"

scalaVersion := "2.10.0-RC1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
  "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/",
  "spray repo" at "http://repo.spray.io/"
)

libraryDependencies ++= Seq(
  "io.spray"           %   "spray-can"         % "1.1-M4.2"
  ,"io.spray"          %   "spray-routing"     % "1.1-M4.2"
  ,"io.spray"          %   "spray-testkit"     % "1.1-M4.2"
  ,"io.spray"          %%  "spray-json"        % "1.2.2"     cross CrossVersion.full
  ,"com.typesafe.akka" %%  "akka-actor"        % "2.1.0-RC1" cross CrossVersion.full
  ,"org.specs2"        %%  "specs2" % "1.12.2" % "test"      cross CrossVersion.full
  ,"com.typesafe"      %   "slick_2.10.0-RC1"  % "0.11.2"
  ,"com.h2database"    %   "h2"                % "1.3.166"
  ,"postgresql"        %   "postgresql"        % "9.1-901.jdbc4"
  ,"org.xerial"        %   "sqlite-jdbc"       % "3.6.20"
  ,"org.specs2"        %   "specs2_2.10.0-RC1" % "1.12.2"    % "test"
  ,"junit"             %   "junit"             % "4.8.1"     % "test"
//  ,"org.slf4j"         %   "slf4j-api"         % "1.6.4"     % "test"
//  ,"org.slf4j"         %   "slf4j-nop"         % "1.6.4"   //  % "runtime"
  ,"ch.qos.logback"    %   "logback-classic"   % "1.0.7"     //% "test"
)

seq(Revolver.settings: _*)
