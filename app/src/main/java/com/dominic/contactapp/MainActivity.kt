package com.dominic.contactapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dominic.contactapp.Data.Contact
import com.dominic.contactapp.MyRecycleView.MyRecycleView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_contact.*

class MainActivity : AppCompatActivity() {
    lateinit var myRecycleView: MyRecycleView
    lateinit var contactme: Contact
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyPreference.init(this)
        val list = MyPreference.objectString
    //    list.addAll(MyPreference.objectString)
        btn_save.setOnClickListener {
            val name = edt_name.text.toString()
            val number = edt_number.text.toString()
            val image = R.drawable.ic_baseline_delete_forever_24
            val imageCall = R.drawable.phone
            val contact = Contact(name,number,image,imageCall)
            if (name != "" && number != ""){
                contactme = contact
                list.add(contact)
                MyPreference.objectString = list
                myRecycleView.notifyItemInserted(list.size)
                edt_name.text.clear()
                edt_number.text.clear()
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
                btn_save.isClickable = false
                Thread.sleep(300)
                btn_save.isClickable = true
            }else if(name == ""){
                Toast.makeText(this, "Write any name", Toast.LENGTH_SHORT).show()
            }else if(number ==""){
                Toast.makeText(this, "Write any numbe", Toast.LENGTH_SHORT).show()
            }


        }
        myRecycleView = MyRecycleView(this,list)
        my_recycle.adapter = myRecycleView
    }
}