S4 - Spray Slick Starter Stack
==============================

Simple, mostly blank project to help you get started with a [Spray](http://spray.io/) REST service using [Slick](http://slick.typesafe.com/) as persistence broker.

The cake pattern is used to allow multiple database backend support without code change. This complicates the code a little, but it worth it. For a basic example (and where I got some code from) have a look at [the MultiDBCakeExample](https://github.com/slick/slick-examples/blob/master/src/main/scala/scala/slick/examples/lifted/MultiDBCakeExample.scala)

To get going:
1. Clone the project
2. Change into the project directory, type 'sbt' and hit return
3. Wait for stuff to start up and type 'test' 

To run the project as a service:
1. Have a look at DBConfig.scala to see what database will be used (default PostgreSQL with a database called s4)
2. Make sure you have PostgreSQL running and update the DBConfig.scala file to include your password.
3. You can run the project by typing 'run'
4. Verify that it's running by visiting localhost:8080
5. Now you can post some json to it (look at the tests for some valid examples)

This example borrows greatly from examples provided by both the Spray and Slick authors.

Pull requests welcome! Project lacks basic authorise example and file upload example... wink-wink ;-)

Disclaimer: I am hoping this will serve as a nice example to get going, but keep in mind that I'm still learning, so I can't be held responsible for any damages caused by this code.
