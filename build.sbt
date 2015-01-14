organization := "com.ssss"

name := "s4"

version := "0.4.1"

scalaVersion := "2.11.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

unmanagedResourceDirectories in Compile <++= baseDirectory { base =>
    Seq( base / "src/main/webapp" )
}

resolvers ++= Seq(
  "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/",
  "spray repo" at "http://repo.spray.io/"
)

libraryDependencies ++= Seq(
   "io.spray"            %%  "spray-json"        % "1.2.6"
  ,"io.spray"            %% "spray-can"          % "1.3.1-20140423"
 // ,"io.spray"            %   "spray-can"       % "1.3.1"
  ,"io.spray"            %%   "spray-routing"     % "1.3.1-20140423"
  ,"io.spray"            %%   "spray-testkit"     % "1.3.1-20140423"
  ,"com.typesafe.akka"   %%  "akka-actor"      % "2.3.2"
  ,"org.specs2"          %%  "specs2"            % "2.3.11"    % "test"
  ,"com.typesafe.slick"  %   "slick_2.11.0-RC4"  % "2.1.0-M1"
  ,"com.h2database"      %   "h2"                % "1.3.166"
  ,"postgresql"          %   "postgresql"        % "9.1-901.jdbc4"
  ,"junit"               %   "junit"             % "4.8.1"     % "test"
  ,"ch.qos.logback"      %   "logback-classic"   % "1.1.1"
  ,"org.scalatest"       %   "scalatest_2.11"    % "2.1.5"
  ,"org.seleniumhq.selenium" % "selenium-java" % "2.28.0" % "test"
//  ,"com.typesafe.akka" %%  "akka-osgi"         % "2.2.0"
//  ,"com.typesafe.akka" %%  "akka-slf4j"        % "2.2.0"
//  ,"com.typesafe.akka" %%  "akka-testkit"      % "2.2.0"
)

lazy val util = (project in file("util")).
  enablePlugins(SbtTwirl).
  settings(
    name := "s4-util"
  )

//EclipseKeys.withSource := true
