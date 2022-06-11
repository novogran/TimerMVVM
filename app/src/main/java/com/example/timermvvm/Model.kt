package com.example.timermvvm

import java.util.*

class Model {

    private var timer: Timer? = null
    private val timerTask = object : TimerTask(){
        override fun run() {
            count++
            callback?.updateText(count.toString())
        }
    }

    private var callback: TextCallback? = null
    private var count = 0


    fun start(textCallback: TextCallback){
        callback = textCallback
        if(timer == null){
            timer?.cancel()
            timer = Timer()
            timer?.scheduleAtFixedRate(timerTask, 1000,1000)
        }
    }
}