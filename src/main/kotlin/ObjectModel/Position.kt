package com.bcollins.magicstockwatcher.ObjectModel

import org.bson.*
import java.time.Instant

data class Position(val cardId: String, val number: Int, val opened: Boolean, val entryDate: Instant, val buyPrice: Double? = null) : IMongoDoc{
    override fun toDocument(): Document {
        val doc = Document()
        doc.put("cardId", cardId)
        doc.put("number", number)
        doc.put("opened", opened)
        doc.put("entryDate", entryDate)
        if(buyPrice != null)
            doc.put("buyPrice", buyPrice)
        return doc
    }

    fun toBsonDocument() : BsonDocument {
        val doc = BsonDocument()
        doc.put("cardId", BsonString(cardId))
        doc.put("number", BsonInt32(number))
        doc.put("opened", BsonBoolean(opened))
        doc.put("entryDate", BsonDateTime(entryDate.toEpochMilli()))
        if(buyPrice != null)
            doc.put("buyPrice", BsonDouble(buyPrice))
        return doc
    }
}





