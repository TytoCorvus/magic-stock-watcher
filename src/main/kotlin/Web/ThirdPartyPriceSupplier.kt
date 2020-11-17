package com.bcollins.magicstockwatcher.web

enum class ThirdPartyPriceSupplier(val properName: String, val base_path: String, val docs: String = "N/A"){
    SCRYFALL("Scryfall","api.scryfall.com", "scryfall.com/docs"),
    CARD_KINGDOM("Card Kingdom","", ""),
    TCGPLAYER("TCG Player",""),
    CHANNEL_FIREBALL("Channel Fireball",""),
    MTGSTOCKS("MTGstocks",""),
    QUIET_SPECULATION("Quiet Speculation","")
}