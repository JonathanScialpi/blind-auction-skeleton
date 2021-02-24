# Welcome to the Conclave Training Course: Developing a Blind-Auction

## Blind-Auction Conclave App

This project is used in the instructor led course where Conclave students are guided in building a Blind-Auction where bids are confidentially submitted
to an auction by leveraging [Conclave Beta 3](https://docs.conclave.net/#beta-4). 

All bids submitted are delivered as Conclave [Mail](https://docs.conclave.net/architecture.html#mail) via HTTP to the Spring Host which is running an [Enclave](https://docs.conclave.net/enclaves.html).

Each of these bids are confidentially decrypted by the Enclave to not let the host or anyone else know of the value. Once The enclave has received the required amount of bids, it calculates which of the submitted is the highest and sends the winning encrypted bid back to host.

This winning bid is saved in host memory so that it can be queried to reveal the winning bid using the [reveal_winner](https://github.com/JonathanScialpi/Sealed-Bid-Auction-CorDapp-Beta3/blob/master/sealed-bid-auction/Webserver/host/src/main/java/com/r3/conclave/sample/host/HostController.java#L65) GET endpoint. 

### Creating the Docker Image
1. Install Docker
2. `docker run --name blind-auction -p 8080:8080 -it -d -v C:\ws\blind-auction\:/sdk -w /sdk ubuntu bash`
3. `docker exec -ti blind-auction apt update`
4. `docker exec -ti blind-auction apt install -y openjdk-8-jdk`

### Starting the Spring Server
1. Clone this project to your local machine.
2. Open the project in IntelliJ and run the Webserver->Host->Tasks->build->assemble configuration to create the Spring Server jar file.
3. `docker exec -ti blind-auction cp /sdk/Webserver/host/build/libs/host.jar /tmp/`
4. `docker exec -ti blind-auction java -jar /tmp/host.jar`

### Running the Application
Run `Client.main()`

(If you receive an `InvalidEnclaveException`, copy the key hash that is "acceptable" and overwrite the existing one on [line 37 of Client.java](https://github.com/JonathanScialpi/ConclaveBeta3-SealedBidAuction/blob/master/sealed-bid-auction/Client/client/src/main/java/com/r3/conclave/sample/client/Client.java#L37))
