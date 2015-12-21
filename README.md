# vertical-ui
This repository contains the example client and server for the article [Vertical Limit](https://dzone.com/articles/vertical-limit).

There are three branches:

Branch   | Description
---------| ----------------------------------------------------------------------------------------
`one-ui` | Contains the initial web shop example
`ui-components`   | The UI is divided into components, each with its own API gateway
`master` | The final example after the cart UI as been changed according to the customer's demands

All projects provide both maven and gradle based builds to create the jars. The jars are self contained spring boot apps which you can start easily with e.g. java -jar router.jar.

If you are not used to Maven or Gradle, you may use the self-installing gradle wrapper. Just the check out the projects and run `gradlew build` (resp. `gradlew.bat build` on Windows) in both directories `mtom-client` and `mtom-server`. This installs gradle and builds the executable jars. To run the client, just call `java -jar build/libs/mtom-client.jar` in directory `mtom-client`, resp. to start the server call `java -jar build/libs/mtom-server.jar` in directory `mtom-server`.

You have to start all server in order to run the application. To access the shop, direct your browser to the router on [http://localhost:9000].
