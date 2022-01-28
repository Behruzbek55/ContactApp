package com.dominic.contactapp

import android.content.Context
import android.content.SharedPreferences
import com.dominic.contactapp.Data.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MyPreference {
    private const val NAME = "Cache"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var mySharedPreferences: SharedPreferences
    fun init(context: Context){
            mySharedPreferences = context.getSharedPreferences(NAME, MODE)

        }
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor)-> Unit){
            val editor = edit()
            operation(editor)
            editor.apply()
        }
    var objectString:ArrayList<Contact>
        get() = gsonStringToArray(mySharedPreferences.getString("data","[]")!!)
        set(value) = mySharedPreferences.edit{
            if (value != null){
                it.putString("data",arrayToGsonString(value))
            }
        }
    fun arrayToGsonString(arrayList: ArrayList<Contact>):String{

        return Gson().toJson(arrayList)
    }
    fun gsonStringToArray(gsonString:String):ArrayList<Contact>{
        val typeToken = object: TypeToken<ArrayList<Contact>>(){}.type
        return Gson().fromJson(gsonString,typeToken)
    }



}