package com.bcollins.magicstockwatcher.DataAccess

import com.bcollins.magicstockwatcher.Card
import com.mongodb.BasicDBObject
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
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
            var item = it.next()
            val set = item.getString("set")
            val foil = item.getBoolean("foil")
            val num = item.getDouble("collector_number").toInt()
            val id = item.getObjectId("_id").toHexString()
            result.add(Card(name, set, num, foil, id))
        }

        return result
    }
}