package com.bcollins.magicstockwatcher.DataAccess

import com.mongodb.BasicDBObject
import com.mongodb.MongoClient
import com.mongodb.MongoException
import com.mongodb.client.MongoDatabase
import org.bson.BsonDocument

class Dao{

    @Throws (MongoException::class)
    fun getDatabase(dbName : String) : MongoDatabase{
        var mongoClient: MongoClient? = null
        var db : MongoDatabase

        mongoClient = MongoClient("localhost", 27017)
        db = mongoClient.getDatabase(dbName)

        return db
    }

}