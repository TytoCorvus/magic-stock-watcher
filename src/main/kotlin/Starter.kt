package com.bcollins.magicstockwatcher

import com.bcollins.twilio.TwilioTest
import com.bcollins.magicstockwatcher.web.ScryfallClient

class Starter{
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            println("We started bois")
            Starter()
        }
    }

    constructor(){
        println("Some stuff")

        println(ScryfallClient().getCardInfoSync("Sol Ring"))
//        println(ScryfallClient().getCardInfoSync("Lightning Bolt"))
//        println(ScryfallClient().getCardInfoSync("Expansion // Explosion"))
//        println(ScryfallClient().getCardInfoSync("Malakir Rebirth // Malakir Mire"))

        println("Done.")
    }
}
