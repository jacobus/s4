S4 - Spray Slick Scala Start
============================

Simple, mostly blank project to help you get started with a [Spray](http://spray.io/) REST service using [Slick](http://slick.typesafe.com/) as persistence broker.

It's just a start, but you immediately get:

   - Spray

   - Akka

   - Slick

   - Logging

   - Testing

   - A slice of cake


This project is _nothing fancy_, but tries to put skeleton code at the right places to start building a more serious project.

The cake pattern is used to allow multiple database backend support without code change. This complicates the code a little, but it will be worth it in the long run. For a basic Slick cake pattern example (and where I got some code from) have a look at [the MultiDBCakeExample](https://github.com/slick/slick-examples/blob/master/src/main/scala/com/typesafe/slick/examples/lifted/MultiDBCakeExample.scala)


To get going:
-------------

1. Clone the project

2. Change into the project directory, type _sbt_ and hit return

3. Wait for stuff to start up and type _test_ 

To run the project as a service:
--------------------------------

1. Have a look at DBConfig.scala to see what database will be used (default PostgreSQL with a database called s4)

2. Make sure you have PostgreSQL running and update the DBConfig.scala file to include your password.

3. You can run the project by typing _run_

4. Verify that it's running by visiting localhost:8080

5. Now you can post some json to it (look at the tests for some valid examples)

Simple sample of file upload:
----------------------------
To test the upload function. With s4 running, open another terminal and go into the s4 directory. Once there, excecute the following command:

    curl -i -F "imageupload=@testpic.png" http://127.0.0.1:8080/upload

This will upload the sample image 'testpic.png' to the server. The server will save the image to the same directory, but this time it will be called 'test.png'.

Using an IDE:
-------------
Both the Scala IDE (Eclipse) project generator and Idea project generator are included in plugins.sbt, so you can type gen-idea or eclipse in the SBT console to generate the project configuration for either of these great IDE's. Personally, I use both: Scala IDE for day-to-day development and it's amazing Scala Worksheet functionality, and Idea for it's superior search and replace.

Contribute:
-----------
Pull requests are welcome! The current project lacks a basic authorise example and file upload example ;-)

Getting help:
-------------
The [spray-user forum](https://groups.google.com/forum/?fromgroups#!forum/spray-user) and [slick forum](https://groups.google.com/forum/?fromgroups#!forum/scalaquery) are both the best places to find help on the specific libraries. For questions or suggestions on specific to this project, please visit the [S4 group](https://groups.google.com/forum/?fromgroups#!forum/spray-slick-scala-start).

Updates:
--------
I will [tweet](https://twitter.com/jacobusreyneke) any significant change to the sample project.

Credits:
--------

This example borrows a great deal from examples provided by both the Spray and Slick authors.

**Disclaimer:** I am hoping this will serve as a nice example to get going, but you are using this code at your own risk.
