package com.bcollins.magicstockwatcher.DataAccess

import com.bcollins.magicstockwatcher.ObjectModel.Card
import com.mongodb.BasicDBObject
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.InsertOneModel
import org.bson.Document

class CardDao {
    companion object{
        val COLLECTION_NAME : String = "cards"
    }

    val collection : MongoCollection<Document>

    constructor(database : MongoDatabase){
        this.collection = database.getCollection(COLLECTION_NAME)
    }

    fun getCards(name : String) : List<Card> {
        val searchQry = BasicDBObject()
        searchQry.put("name", name)
        val cursor = collection.find(searchQry)

        var it = cursor.iterator()
        var result = mutableListOf<Card>()
        while(it.hasNext()){
            var item: Document = it.next()
            val set = item.getString("set")
            val foil = item.getBoolean("foil")
            val num = item.getDouble("collector_number").toInt()
            val id = item.getObjectId("_id").toHexString()
            result.add(Card(name, set, num, foil, id))
        }

        return result
    }

    fun getCardExistsInCollection(card : Card) : Boolean{
        if(card.isFromLocalDb())
            return true;

        val searchQry = BasicDBObject()
        searchQry.put("name", card.name)
        searchQry.put("collector_number", card.collectorNumber)
        searchQry.put("foil", card.foil)
        searchQry.put("set", card.set)
        val cursor = collection.find(searchQry)

        var it = cursor.iterator()
        var count = 0
        while(it.hasNext()){
            it.next()
            count++
        }

        return count != 0
    }

    fun getCardExistsInCollectionByName(name : String) : Boolean{
        val searchQry = BasicDBObject()
        searchQry.put("name", name)
        val cursor = collection.find(searchQry)

        return cursor.iterator().hasNext()
    }

    fun insertCardIntoCollection(card : Card){
        collection.insertOne(card.toDocument())
    }
}