package com.bcollins.magicstockwatcher

import com.bcollins.TwilioTest

class Starter{
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            println("We started bois")
            Starter()
        }
    }

    constructor(){
        println("Some stuff")
        TwilioTest().run("This is a test message!")
        println("Done.")
    }
}
