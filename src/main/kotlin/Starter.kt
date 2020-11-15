package com.bcollins.magicstockwatcher

import com.bcollins.magicstockwatcher.DataAccess.CardDao
import com.bcollins.magicstockwatcher.DataAccess.Dao
import com.bcollins.twilio.TwilioTest
import com.bcollins.magicstockwatcher.web.ScryfallClient
import com.twilio.Twilio

class Starter{
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            println("We started bois")
            //Starter()
            var dao = Dao()

            val cardDao : CardDao = CardDao(dao.getDatabase("magic-stock-watcher"))
            val cards = cardDao.getCards("Thought Erasure")
            println(cards)
        }
    }

    constructor(){
        println("Some stuff")

        val cardData = ScryfallClient().getCardInfoSync("Jeweled Lotus")
        val twilioInstance = TwilioTest()
        twilioInstance.run(buildMessageFromData(cardData))

//        println(ScryfallClient().getCardInfoSync("Lightning Bolt"))
//        println(ScryfallClient().getCardInfoSync("Expansion // Explosion"))
//        println(ScryfallClient().getCardInfoSync("Malakir Rebirth // Malakir Mire"))

        println("Done.")
    }

    fun buildMessageFromData(cardData : Pair<Card, Price>?) : String{
        if (cardData == null)
            return "There was no data available for the card in question"

        return """
            The value of ${cardData.first.name} is currently:
            NORMAL:     $${cardData.second.normal.buyVal}
            FOIL:       $${cardData.second.foil.buyVal}
            According to ${cardData.second.source.properName}
        """.trimIndent()
    }
}
