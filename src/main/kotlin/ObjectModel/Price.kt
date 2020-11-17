package com.bcollins.magicstockwatcher.ObjectModel

import com.bcollins.magicstockwatcher.web.ThirdPartyPriceSupplier
import java.util.*

data class Price(var normal: Value, var timestamp: Date, var source: ThirdPartyPriceSupplier){
    fun isEmpty() : Boolean {
        return normal.isEmpty()
    }
}