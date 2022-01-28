package com.dominic.contactapp.MyRecycleView

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.dominic.contactapp.Data.Contact
import com.dominic.contactapp.MyPreference
import com.dominic.contactapp.R
import kotlinx.android.synthetic.main.item_contact.view.*

class MyRecycleView(val context: Context, var list:ArrayList<Contact>): RecyclerView.Adapter<MyRecycleView.VH>() {

    inner class VH(itemView:View): RecyclerView.ViewHolder(itemView){
        fun onBind(contact: Contact){
            itemView.text_name.text = contact.name
            itemView.text_number.text = contact.number
            itemView.img_remove.setImageResource(contact.remove_image!!)
            itemView.img_call.setImageResource(contact.call_image!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return  VH(LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
        holder.itemView.img_remove.setOnClickListener {
            MyPreference.init(context)
            list.removeAt(position)
            Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
            MyPreference.objectString = list
            notifyDataSetChanged()
        }
        holder.itemView.img_call.setOnClickListener {
            Toast.makeText(context, "Calling...", Toast.LENGTH_LONG).show()
            val calling = Intent(Intent.ACTION_DIAL)
            val callingNumbe = list[position].number
            calling.data = Uri.parse("tel:$callingNumbe")
            startActivity(context,calling, Bundle())
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
}