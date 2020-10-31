package com.bcollins.magicstockwatcher

import java.time.LocalDateTime


data class Card(var name: String, var set: String, var collectorNumber: Int)
data class Price(var normal: Int, var foil: Int, var timestamp: LocalDateTime)