package com.bcollins.magicstockwatcher

import com.bcollins.magicstockwatcher.DataAccess.CardDao
import com.bcollins.magicstockwatcher.DataAccess.CollectorDao

class PositionManager{
    val cardDao : CardDao
    val collectorDao : CollectorDao

    constructor(cardDao : CardDao, collectorDao : CollectorDao){
        this.cardDao = cardDao
        this.collectorDao = collectorDao
    }



}