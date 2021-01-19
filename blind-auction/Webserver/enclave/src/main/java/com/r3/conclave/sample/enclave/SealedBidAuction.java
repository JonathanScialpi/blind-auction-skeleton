package com.r3.conclave.sample.enclave;

import com.r3.conclave.enclave.Enclave;
import com.r3.conclave.mail.EnclaveMail;
import com.r3.conclave.mail.MutableMail;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/**
 * Welcome to the Blind-Auction Conclave Training App!
 */

public class SealedBidAuction extends Enclave {
    /**
     * TASK 1:  First we will implement a method that will be responsible for processing all incoming MAIL objects.
     *
     * Requirements for our Enclave's algorithm:
     *   TODO: The Enclave should be keeping track of how many bids it has received thus far.
     *   Step 1: Create a global variable called "allBids" of type ArrayList which we will use to store the various MAILs.
     *   HINT: Remember that MAIL objects consist of encrypted bytes so take that into account when defining your LIST.
     *
     *   Step 2: Create a global variable called "bidders" of type ArrayList which we will use to store the various Client publicKeys.
     *   By storing the keys, we can recall who the winner was once we find the highest bid.
     *
     *   Step 3: Create a variable called winnerIndex and initialize it to -1. We will use this value to store the index
     *   related to the publicKey of the winning bidder.
     *
     *   TODO: Only after some threshold (let's say 5) of bids is broken should the Enclave do any work.
     *   Step 4: Next let's define a method that can receive byte[]'s as input and return byte[]'s as output. Let's give
     *   a public access modifier and name it "invoke".
     *
     *   Step 5: Upon receiving a new bid, we should first add it to our existing LIST "allBids". We can later use the
     *   size of allBids to check to see if the threshold has been broken. Add a Line that takes the bid and "adds"
     *   it to allBids.
     *
     *   TODO: Once we have received all the necessary bids, we should loop through each bid and return the largest bid.
     *
     *   Step 6: Now that we have a LIST that is growing in size each time a bid is sent from a client, let's add an
     *   IF statement which will check to see if the current allBids.size() is 5 yet.
     *
     *   Step 7: Assuming the IF statement we wrote in the previous step, we have to implement logical block that should
     *   be executed. This block of code will be our algorithm for finding the greatest value in allBids. Try to figure
     *   out a way to do this by iterating over the list.
     *
     *   HINT: you can use ByteBuffer.wrap({The value your iterating on}).getInt() to convert the value to an Integer.
     *
     *   Step 8: Once we find the highest bid, remember to store that index as the winning index by setting
     *   the variable we created earlier (winnerIndex) equal to the index of the current bid.
     *   HINT: for(i=0;...){winnerIndex = i;}
     *
     *   Step 9: Finally, we have to return the highest bid value that we found as an array of bytes.
     *
     *   HINT: To convert back to bytes you can use: ByteBuffer.allocate().putInt.array()
     */

    @Override
    protected void receiveMail(long id, String routingHint, EnclaveMail mail) {
        /**
         * Task 2: Great job on writing your enclave's algorithm! This next task we will work on will actually deliver
         * the MAIL object to the "invoke" method we defined above.
         *
         *  TODO: Implement the receiveMail method which will handle receiving encrypted bytes known as MAIL from the Host.
         *  Step 1: Define a method called receiveMail which has:
         *   --> A protected access modifier
         *   --> returns nothing (void)
         *   --> accepts three params: an "id" of type long, a "routingHint" of type String, and a "mail" of type EnclaveMail
         *
         *   TODO: Call the invoke method we defined in Task1 on the MAIL's bytes
         *   Step 2: Create variable named "submitBid" and set it to store the incoming MAIL bytes being delivered from the Host.
         *
         *  HINT: use the "mail" variable that is being passed to this method as a parameter and check to see if there
         *  are any useful helper methods that it offers by typing adding a '.' to the end of the mail variable in your
         *  variable definition (like this: "mail.") and then hitting CTRL+SPACEBAR to view all the helper methods (this
         *  is an IntelliJ shortcut that lists all of the object's methods).
         *
         *  Step 3: Let's modify the line we just wrote by setting submitBid to be the return value of the invoke() method.
         *  The value that we will be passing to our invoke method will be the MAIL bytes that we wrote out in the previous step.
         *
         *  Step 4: Upon receiving a new MAIL object, we need to add the current bidders publicKey to the list of PublicKeys
         *  we created before so that the enclave can keep track of the possible winners. Write a line of code that will
         *  instruct the enclave to [add] the current MAIL object's Authenticated Sender to the list.
         *  HINT: Refer to the MAIL object's helper method to see if there is a way to GET the PublicKey.
         *
         *  TODO: Once we have seen at least 5 bids, let's send the encrypted MAIL back to the Host so it can be accessed by the clients.
         *
         *  Step 1: Write an IF statement that will check if the size of "allBids" has reached our threshold of 5 bids yet.
         *  The reason why we check here is because we only want to send mail to the host if its worth sending at all which for
         *  this use cases means finding a winning bid out of 5 bids.
         *
         *  Step 2: When the IF statement eventually evaluates to true, we need a block of code that will create a MAIL object and send it
         *  to the host so that it can be available for the clients. Let's create a [MutableMail] object called "reply" and set it equal to
         *  the MAIL object containing our enclave's reply.
         *
         *  HINT: You will have to use the [createMail] function. Examine the function's parameters to see how can leverage our
         *  winnerIndex variable to ensure that only the winner of the auction should be able to decrypt the MAIL.
         *
         *  Step 3: Now that we have a "reply" MAIL for the clients, call the postMail function to deliver it to the Host.
         *  (You can pass a NULL value for the routing hint if you like as it's not necessary for our application)
         *
          */

    }
}