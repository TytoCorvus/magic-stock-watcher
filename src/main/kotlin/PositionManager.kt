package com.bcollins.magicstockwatcher

import com.bcollins.magicstockwatcher.DataAccess.CardDao
import com.bcollins.magicstockwatcher.DataAccess.PositionDao

class PositionManager{
    val cardDao : CardDao
    val positionDao : PositionDao

    constructor(cardDao : CardDao, positionDao : PositionDao){
        this.cardDao = cardDao
        this.positionDao = positionDao
    }



}