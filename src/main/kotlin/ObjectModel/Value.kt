package com.bcollins.magicstockwatcher.ObjectModel

data class Value(val buyVal: Double?, val sellValCash: Double?, val sellValTrade: Double?){
    fun isEmpty() : Boolean{
        return !(buyVal == null && sellValCash == null && sellValTrade == null)
    }
}