package com.bcollins.magicstockwatcher.web

import com.bcollins.magicstockwatcher.ObjectModel.Card
import com.bcollins.magicstockwatcher.ObjectModel.Price
import com.bcollins.magicstockwatcher.ObjectModel.Value
import com.google.gson.*
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.LocalDateTime

class ScryfallClient {
    companion object{
        var supplier : ThirdPartyPriceSupplier = ThirdPartyPriceSupplier.SCRYFALL
    }

    fun getCardInfoSync(cardName : String) : Pair<Card, Price> ?{
        val encodedCardName : String = URLEncoder.encode(cardName, StandardCharsets.UTF_8.toString())

        var responseBody : String = HttpWrapper.sendRequest("https://${supplier.base_path}/cards/search?q=${encodedCardName}")

        return parseFromValue(cardName, responseBody)
    }

    private fun parseFromValue(cardName: String, responseAsJson : String) : Pair<Card, Price>?{

        val rootElement = JsonParser.parseString(responseAsJson).asJsonObject

        if(rootElement.getAsJsonPrimitive("total_cards").asInt < 1 )
            return null

        val dataArray = rootElement.asJsonObject.getAsJsonArray("data")

        val cardData : List<JsonElement> = dataArray.filter{it.asJsonObject.getAsJsonPrimitive("name").asString.equals(cardName)}

        if(cardData.size < 1)
            return null

        val cardJson = cardData[0].asJsonObject
        val pricesJson = cardJson.getAsJsonObject("prices")

        val card = parseCardFromJsonObject(cardName, cardJson)
        val price = parsePriceFromJsonObject(pricesJson)
        return Pair(card, price)
    }

    private fun parseCardFromJsonObject(cardName:String, cardJson : JsonObject) : Card {
        return Card(
            cardName,
            cardJson.getAsJsonPrimitive("set").asString,
            cardJson.getAsJsonPrimitive("collector_number").asInt,
            cardJson.getAsJsonPrimitive("foil").asBoolean
        )
    }

    private fun parsePriceFromJsonObject(priceJson : JsonObject) : Price {
        val normalPrice : Double? = if(priceJson.get("usd").isJsonNull) null else priceJson.getAsJsonPrimitive("usd").asDouble
        val foilPrice : Double? = if(priceJson.get("usd_foil").isJsonNull) null else priceJson.getAsJsonPrimitive("usd_foil").asDouble

        return Price(
            Value(normalPrice, null, null),
            Instant.now(),
            ThirdPartyPriceSupplier.SCRYFALL
        )
    }
}