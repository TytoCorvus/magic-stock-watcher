package com.bcollins.magicstockwatcher.DataAccess

import com.bcollins.magicstockwatcher.ObjectModel.Collector
import com.bcollins.magicstockwatcher.ObjectModel.Position
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.BsonArray
import org.bson.BsonDocument
import org.bson.BsonString
import org.bson.Document

class CollectorDao{

    val collection : MongoCollection<Document>

    companion object{
        val COLLECTION_NAME : String = "collectors"
    }

    constructor(database : MongoDatabase){
        this.collection = database.getCollection(CardDao.COLLECTION_NAME)
    }

    fun createNewCollector(collector : Collector) : Boolean{
        if(findCollector(collector) != null)
            return false
        collection.insertOne(collector.toDocument())
        return true
    }

    fun findCollector(collector : Collector) : Collector?{
        findCollector(collector.name)
    }

    fun findCollector(name : String) : Collector? {
        val doc = Document()
        doc.put("name", name)
        collection.find(doc).iterator().next()
    }

    fun addPositions(collector : Collector, positions : List<Position>) : Boolean {
        if(findCollector(collector) == null)
            return false

        val bsonDoc = BsonDocument()
        bsonDoc.put("name", BsonString(collector.name))
        //bsonDoc.

        val updateBson = BsonDocument()
        val updateBsonInternal = BsonDocument()
        updateBsonInternal.put("positions", BsonArray(positions.map{it.toBsonDocument()}))
        updateBson.put("\$addToSet", updateBsonInternal)

        collection.updateOne(bsonDoc, updateBson)
        return true
    }
}