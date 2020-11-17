package com.bcollins.magicstockwatcher.ObjectModel

import org.bson.Document

interface IMongoDoc{
    fun toDocument() : Document

}