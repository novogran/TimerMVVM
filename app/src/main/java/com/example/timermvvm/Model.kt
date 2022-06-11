package com.example.timermvvm

import java.util.*

class Model(private val textCallBack: TextCallback) {

    private var timer: Timer? = null

    private var count = 0

    fun start(){
        timer?.cancel()
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                count++
                textCallBack.updateText(count.toString())
            }
        },1000,1000)

    }
}