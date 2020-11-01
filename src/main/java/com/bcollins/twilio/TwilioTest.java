package com.bcollins.twilio;

import com.twilio.*;
import com.twilio.rest.api.v2010.account.Message;

public class TwilioTest {
    // Find your Account Sid and Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static final String MY_PHONE = System.getenv("MY_PHONE");
    public static final String TWILIO_PHONE = System.getenv("TWILIO_PHONE");


    public TwilioTest(){}

    public void run(String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message text = Message.creator(
                new com.twilio.type.PhoneNumber(MY_PHONE),
                new com.twilio.type.PhoneNumber("+18643438319"),
                message)
                .create();

        System.out.println(text.getSid());
    }
}