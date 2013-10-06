organization := "com.ssss"

name := "s4"

version := "0.3.0"

scalaVersion := "2.10.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

resolvers ++= Seq(
  "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/",
  "spray repo" at "http://repo.spray.io/"
)

libraryDependencies ++= Seq(
  "io.spray"             %   "spray-can"         % "1.2-M8"
  ,"io.spray"            %   "spray-routing"     % "1.2-M8"
  ,"io.spray"            %   "spray-testkit"     % "1.2-M8"
  ,"io.spray"            %%  "spray-json"        % "1.2.5"
  ,"com.typesafe.akka"   %%  "akka-actor"        % "2.2.0-RC1"
  ,"org.specs2"          %%  "specs2"            % "1.14"    % "test"
  ,"com.typesafe.slick"  %%   "slick"            % "1.0.1"
  ,"com.h2database"      %   "h2"                % "1.3.166"
  ,"postgresql"          %   "postgresql"        % "9.1-901.jdbc4"
  ,"junit"               %   "junit"             % "4.8.1"     % "test"
  ,"ch.qos.logback"      %   "logback-classic"   % "1.0.7"     //% "test"
  ,"org.scalatest"       %%  "scalatest"         % "1.9.1"
//  ,"com.typesafe.akka" %%  "akka-osgi"         % "2.2.0"
//  ,"com.typesafe.akka" %%  "akka-slf4j"        % "2.2.0"
//  ,"com.typesafe.akka" %%  "akka-testkit"      % "2.2.0"
)

EclipseKeys.withSource := true