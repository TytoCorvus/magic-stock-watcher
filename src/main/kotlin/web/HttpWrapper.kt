package com.bcollins.magicstockwatcher.web

import java.net.URI
import java.net.http.*


object HttpWrapper{
    val httpClient = HttpClient.newHttpClient()

    fun sendRequest(uri : String) : String{
        val httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build()
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body()
    }

    fun buildProvider(){

    }
}