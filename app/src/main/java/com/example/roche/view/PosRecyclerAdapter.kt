package com.example.roche.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roche.R
import com.example.roche.pojo.UserList

class PosRecyclerAdapter(var userList:UserList) : RecyclerView.Adapter<RecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.user_items, null)
        return RecyclerViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return userList.data.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
       holder.userId.text =userList.data[position].userId
        holder.username.text =" ${userList.data[position].firstName} ${userList.data[position].lastName}"
    }

}


class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    var userId:TextView = itemView.findViewById(R.id.userid)
    var username:TextView = itemView.findViewById(R.id.username)


    override fun onClick(v: View?) {

    }

}
