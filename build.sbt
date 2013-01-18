organization := "com.ssss"

name := "s4"

version := "0.2"

scalaVersion := "2.10.0"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

resolvers ++= Seq(
  "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/",
  "spray repo" at "http://repo.spray.io/"
)

libraryDependencies ++= Seq(
  "io.spray"           %   "spray-can"         % "1.1-M7"
  ,"io.spray"          %   "spray-routing"     % "1.1-M7"
  ,"io.spray"          %   "spray-testkit"     % "1.1-M7"
  ,"io.spray"          %%  "spray-json"        % "1.2.3"
  ,"com.typesafe.akka" %%  "akka-actor"        % "2.1.0" 
  ,"org.specs2"        %%  "specs2"            % "1.13"    % "test"
  ,"com.typesafe"      %   "slick_2.10"        % "1.0.0-RC1"
  ,"com.h2database"    %   "h2"                % "1.3.166"
  ,"postgresql"        %   "postgresql"        % "9.1-901.jdbc4"
  ,"org.xerial"        %   "sqlite-jdbc"       % "3.6.20"
  ,"junit"             %   "junit"             % "4.8.1"     % "test"
  ,"ch.qos.logback"    %   "logback-classic"   % "1.0.7"     //% "test"
)

// Uncomment if you use revolver:   seq(Revolver.settings: _*)
