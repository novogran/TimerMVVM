package com.example.timermvvm

class Model(private val dataSource: DataSource,
            private val timeTicker: TimeTicker
) {

    companion object{
        private const val COUNTER_KEY = "counterKey"
    }


    private val tickerCallback
        get() = object : TimeTicker.Callback{
            override fun tick() {
                count++
                callback?.updateText(count.toString())
            }
        }

    private var callback: TextCallback? = null
    private var count = -1


    fun start(textCallback: TextCallback){
        callback = textCallback
        if(count<0)
            count = dataSource.getInt(COUNTER_KEY)
        timeTicker.start(tickerCallback)

    }

    fun stop(){
        dataSource.saveInt(COUNTER_KEY,count)
        timeTicker.stop()
    }
}