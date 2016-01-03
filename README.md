# vertical-ui
This repository contains the example client and server for the article [Avoid Vertical Limits in Microservice Architectures](http://hardmockcafe.blogspot.de/2016/01/avoid-vertical-limits-in-microservice-architectures.html).

There are three branches:

Branch   | Description
---------| ----------------------------------------------------------------------------------------
`one-ui` | Contains the initial web shop example
`ui-components`   | The UI is divided into components, each with its own API gateway
`master` | The final example after the cart UI as been changed according to the customer's demands

All projects provide both maven and gradle based builds to create the jars. The jars are self contained spring boot apps which you can start easily with e.g. `java -jar router.jar`. You will need Java 8 to run the jars.

If you are not used to Maven or Gradle, you may use the self-installing gradle wrapper. Just the check out the projects and run `gradlew build` (resp. `gradlew.bat build` on Windows) in all projects. This installs gradle and builds the executable jars. For your convenience the jars are available prebuilt in the [jars.zip](jars.zip).

You have to start all server in order to run the application. To access the shop, direct your browser to the router on [http://localhost:9000](http://localhost:9000).
