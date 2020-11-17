package com.bcollins.magicstockwatcher.ObjectModel

import org.bson.Document

data class Collector(val name : String, val positions : List<Position>) : IMongoDoc {
    override fun toDocument(): Document {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}