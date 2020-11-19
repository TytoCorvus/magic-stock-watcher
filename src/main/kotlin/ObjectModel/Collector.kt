package com.bcollins.magicstockwatcher.ObjectModel

import org.bson.Document

data class Collector(val name : String, val positions : List<Position>) : IMongoDoc {
    companion object{
        fun fromDocument(doc : Document) : Collector{
            val positionList = mutableListOf<Position>()
            if(doc.get("positions")  != null){
                val positionsDoc = doc.get("positions")
                positionsDoc.
            }

            Collector(doc.getString("name"), positionList)
        }
    }


    override fun toDocument(): Document {
        val doc = Document()
        doc.put("name", name)
        doc.put("positions", positions.map{it.toDocument()})
        return doc
    }
}