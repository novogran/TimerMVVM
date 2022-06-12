package com.example.timermvvm

class ViewModel(private val model: Model) {

    private var textObservable: TextObservable? = null

    private val textCallback = object : TextCallback{
        override fun updateText(text: String) {
            textObservable?.postValue(text)
        }
    }

    fun init(textObservable: TextObservable){
        this.textObservable = textObservable
    }

    fun clear(){
        textObservable = null
    }

    fun resumeCounting(){
        model.start(textCallback)
    }

    fun pauseCounting(){
        model.stop()
    }
}