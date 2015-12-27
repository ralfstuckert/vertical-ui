# vertical-ui
This repository contains the example client and server for the article [Vertical Limit]().

There are three branches:

Branch   | Description
---------| ----------------------------------------------------------------------------------------
`one-ui` | Contains the initial web shop example
`ui-components`   | The UI is divided into components, each with its own API gateway
`master` | The final example after the cart UI as been changed according to the customer's demands

All projects provide both maven and gradle based builds to create the jars. The jars are self contained spring boot apps which you can start easily with e.g. `java -jar router.jar`.

If you are not used to Maven or Gradle, you may use the self-installing gradle wrapper. Just the check out the projects and run `gradlew build` (resp. `gradlew.bat build` on Windows) in all projects. This installs gradle and builds the executable jars. 

You have to start all server in order to run the application. To access the shop, direct your browser to the router on [http://localhost:9000](http://localhost:9000).
