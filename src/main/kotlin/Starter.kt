package com.bcollins.magicstockwatcher

import com.bcollins.magicstockwatcher.DataAccess.CardDao
import com.bcollins.magicstockwatcher.DataAccess.Dao
import com.bcollins.magicstockwatcher.ObjectModel.Card
import com.bcollins.magicstockwatcher.ObjectModel.Collector
import com.bcollins.magicstockwatcher.ObjectModel.Position
import com.bcollins.magicstockwatcher.ObjectModel.Price
import com.bcollins.magicstockwatcher.web.ScryfallClient
import com.mongodb.BasicDBObject
import java.time.Instant

class Starter{
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            Starter()
        }
    }

    constructor(){

        val dao = Dao()
        val collection = dao.getDatabase("magic-stock-watcher").getCollection("collectors")

        val position1 = Position("someRandomCardId",  5, false, Instant.now(), 3.49)
        val position2 = Position("otherCard",  89043215, false, Instant.now(), 0.26)
        val position3 = Position("thirdCard",  3, true, Instant.now())
        val positionList = listOf(position1, position2, position3)
        val collector = Collector("test collector", positionList)

        val cursor = collection.insertOne(collector.toDocument())
    }

    fun insertCardsTest(){
        val cardNames = listOf("Lightning Bolt", "Opt", "Thought Erasure", "Shock", "Entomb", "Sol Ring", "Force of Despair", "Fireball", "Chromium, the Mutable")

        val dao = Dao()
        val cardDao = CardDao(dao.getDatabase("magic-stock-watcher"))

        cardNames.forEach{name -> gatherCardByName(cardDao, name)}
    }

    fun gatherCardByName(cardDao : CardDao, name : String){
        println("Finding $name")


        if(cardDao.getCardExistsInCollectionByName(name)){
            println("$name already exists in the database")
            return;
        }

        val cardData = ScryfallClient().getCardInfoSync(name)
        if(cardData != null){
            cardDao.insertCardIntoCollection(cardData.first)
        } else {
            println("The card information for $name was not found")
        }
    }

    fun findCardsInDb(cardList : List<String>){
        var dao = Dao()

        val cardDao : CardDao = CardDao(dao.getDatabase("magic-stock-watcher"))
        cardList.forEach{
            println("$it ${if(!cardDao.getCardExistsInCollectionByName(it)){"does not exist"} else{"currently exists"}} in the database")
        }
    }

    fun buildMessageFromData(cardData : Pair<Card, Price>?) : String{
        if (cardData == null)
            return "There was no data available for the card in question"

        return """
            The value of ${cardData.first.name} is currently:
            NORMAL:     $${cardData.second.normal.buyVal}
            According to ${cardData.second.source.properName}
        """.trimIndent()
    }
}
