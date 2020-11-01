package com.bcollins.magicstockwatcher.web

enum class ThirdPartyPriceSupplier(val base_path: String, val docs: String = "N/A"){
    SCRYFALL("api.scryfall.com", "scryfall.com/docs"),
    CARD_KINGDOM(""),
    TCGPLAYER(""),
    CHANNEL_FIREBALL(""),
    MTGSTOCKS(""),
    QUIET_SPECULATION("")
}