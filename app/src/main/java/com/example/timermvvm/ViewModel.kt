package com.example.timermvvm

class ViewModel(private val textObservable: TextObservable) {

    private val model = Model(object : TextCallback{
        override fun updateText(text: String) {
            textObservable.postValue(text)
        }
    })

    fun init(){
        model.start()
    }
}