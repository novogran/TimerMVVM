package com.example.timermvvm

class TextObservable {

    private lateinit var callback: TextCallback

    fun observe(callback: TextCallback){
        this.callback = callback
    }
    fun postValue(text:String){
        callback.updateText(text)
    }
}