package com.bcollins.magicstockwatcher.DataAccess

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document

class CollectorDao{

    val collection : MongoCollection<Document>

    companion object{
        val COLLECTION_NAME : String = "collectors"
    }

    constructor(database : MongoDatabase){
        this.collection = database.getCollection(CardDao.COLLECTION_NAME)
    }

}