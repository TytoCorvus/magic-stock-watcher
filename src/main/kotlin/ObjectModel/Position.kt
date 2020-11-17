package com.bcollins.magicstockwatcher.ObjectModel

import com.bcollins.magicstockwatcher.web.ThirdPartyPriceSupplier
import org.bson.Document
import java.time.LocalDateTime
import java.util.*

data class Position (val cardId : String , val number : Integer, val opened : Boolean, val buyPrice : Integer? = null, val entryDate : Date) : IMongoDoc{
    override fun toDocument(): Document {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}





