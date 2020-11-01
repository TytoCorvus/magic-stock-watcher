package com.bcollins.magicstockwatcher

import com.bcollins.magicstockwatcher.web.ThirdPartyPriceSupplier
import java.time.LocalDateTime

data class Position(var card : Card, var history : PriceHistory)
data class Card(var name: String, var set: String, var setName: String, var collectorNumber: Int)
data class PriceHistory(var buyIn: Price, var daily: Price, var weekly: Price, var monthly: Price)

data class Price(var normal: Value, var foil: Value,  var timestamp: LocalDateTime, var source: ThirdPartyPriceSupplier){
   fun isEmpty() : Boolean {
       return normal.isEmpty() && foil.isEmpty()
   }
}

data class Value(val buyVal: Double?, val sellValCash: Double?, val sellValTrade: Double?){
    fun isEmpty() : Boolean{
        return !(buyVal == null && sellValCash == null && sellValTrade == null)
    }
}