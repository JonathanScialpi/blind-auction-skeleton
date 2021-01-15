package com.r3.conclave.sample.host;

import com.r3.conclave.host.AttestationParameters;
import com.r3.conclave.host.EnclaveHost;
import com.r3.conclave.host.EnclaveLoadException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

/**
 *  Welcome to the Host side of the Blind-Auction Application and congratulations for making it this far!
 *
 *  Task 3: The first thing we are going to work on is the HostController's Constructor so that as soon as the
 *  WebServer starts up, so does our Enclave ready to process any incoming bids on our auction.
 *
 *  TODO: Load and Start our Enclave on WebServer startup
 *
 *  Step 1: Create a Global variable named "enclave" of type EnclaveHost
 *
 *  Step 2: Create a variable named "requestToDeliver" of type AtomicReference that will be responsible for storing
 *  bytes of a MAIL object sent from our Enclave.
 *
 *  Step 3: Modify the HostController constructor by setting the enclave variable we created in the first step equal to
 *  the value of our loaded enclave.
 *
 *  HINT: See if the [EnclaveHost] class has any helper methods which take our enclave's class path as a String parameter.
 *
 *  Step 4: Start the enclave! Being that our enclave variable is of type [EnclaveHost] you use the "start" helper method.
 *  --> For the first parameter, let's give it a new instance of the AttestationParameters.DCAP() which is a generic datacenter attestation primitives protocol.
 *  --> For the second parameter, let's supply a new instance of EncalveHost.MailCallbacks()
 *
 *  Step 5: Inside the block of  MailCallbacks(), override the [postMail] method which is the method we will use to send mail to the enclave.
 *  --> For the first parameter, create a byte array named "encrypted bytes"
 *  --> For the second parameter, create a string named "routingHint"
 *  --> Inside this postMail block, we need to set the value of requestToDeliver to the encryptedBytes parameter this method is receiving.
 *
 */

@RestController
public class HostController {

	public HostController() throws EnclaveLoadException {

	}

	/**
	 * Task 4: Now that our enclave is loaded and running waiting for bids, we need a way for clients to retrieve the
	 * remote attestation so that they can verify the integrity of the enclave measurement as well as retrieve the Enclave's
	 * public key. This public key is what each client will use to encrypt the bytes of their bids which will make only the enclave
	 * will be able to view/decrypt.
	 *
	 * TODO: Define a GET endpoint that a Client can use to retrieve the Remote Attestation of the Enclave
	 *
	 * Step 1: Use our enclave variable to return the enclave's serialized Remote Attestation.
	 *
	 * HINT: Check the enclave object for any helper methods to get the RA and also serialize it.
	 *
	 */
	// A GET endpoint used to retrieve the remote attestation.
	@GetMapping(path="/sealed_bid_ra")
	public void get_sealed_bid_ra() {
	}

	/**
	 * Task 5: Clients are now able to retrieve the RA but we need to provide them with an endpoint they can use to POST
	 * their encrypted bids so that the Host can receive and forward them to the enclave.
	 *
	 * TODO: Define a POST endpoint which will accept MAIL as a byte array and deliver it to the enclave
	 *
	 * Step 1: Use our enclave object's deliverMail method to forward the mail to the enclave.
	 * --> For the first parameter, you can simply define a global long variable initialized to zero called "mailID"
	 * --> The second parameter should be MAIL's byte data which we can reference as "bid"
	 * --> Lastly, the thirs parameter should be any String that might be useful for the enclave. We can just put "routingHint" for now.
	 *
	 * Step 2: Create a variable called "reply" which will be used to store the enclave's reply as a byte array. Set the value of this
	 * variable equal to the requestToDeliver callback we passed to the enclave's start method earlier. Make sure to call the .get() method.
	 *
	 * Step 3: While the threshold hasn't been broken (we haven't received 5 bids yet) the enclave will not reply and therefore
	 * the reply value should be NULL. However, whenever it isn't null, we want to make sure to save that value as our WINNER.
	 * Write an IF statement that will check if the replay variable is null.
	 *
	 * Step 4: If the condition is true, let's set the reply (which contains the winning bid) equal to a global variable
	 * by the name of winner of type byte array.
	 *
	 * Step 5: Since we have met our threshold and found the winning bid, our auction has finally ended. Let's close our
	 * enclave using the .close() method.
	 *
	 */
	// A POST endpoint which accepts raw encrypted bytes sent by a client to deliver to an enclave.
	@PostMapping(path = "/send_bid")
	public void sendBid(@RequestBody byte[] bid){
	}

	/**
	 *  Task 6: To finish the WebServer we will simply define an endpoint which returns the winner	 *
	 */
	// A GET endpoint used to retrieve the remote attestation.
	@GetMapping(path="/reveal_winner")
	public void get_winner() {
	}
}