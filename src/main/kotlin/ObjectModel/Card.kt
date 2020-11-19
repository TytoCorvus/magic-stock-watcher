package com.bcollins.magicstockwatcher.ObjectModel

import org.bson.BsonDocument
import org.bson.Document


data class Card(val name: String, val set: String, val collectorNumber: Int, val foil: Boolean, val databaseId: String? = null) : IMongoDoc{
    constructor(name: String, set: String, collectorNumber: Int, foil: Boolean) : this(name, set, collectorNumber, foil, null)

    fun isFromLocalDb() : Boolean {
        return databaseId == null
    }

    override fun toDocument(): Document {
        val doc = Document()
        doc.put("name", name)
        doc.put("set", set)
        doc.put("collector_number", collectorNumber)
        doc.put("foil", foil)

        return doc
    }
}