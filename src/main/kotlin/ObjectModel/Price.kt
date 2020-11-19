package com.bcollins.magicstockwatcher.ObjectModel

import com.bcollins.magicstockwatcher.web.ThirdPartyPriceSupplier
import java.time.Instant
import java.util.*

data class Price(var normal: Value, var timestamp: Instant, var source: ThirdPartyPriceSupplier){
    fun isEmpty() : Boolean {
        return normal.isEmpty()
    }
}