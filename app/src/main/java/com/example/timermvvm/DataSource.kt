package com.example.timermvvm

interface DataSource {

    fun saveInt(key:String,value: Int)

    fun getInt(key: String): Int
}