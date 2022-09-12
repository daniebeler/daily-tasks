package com.daniebeler.dailytasks

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val list: MutableList<ToDoItem>, val listener: OnItemClickListener, val longlistener: OnItemLongclickListener) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(){

    private lateinit var context: Context

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener, View.OnLongClickListener{
        val toDoName : TextView = v.findViewById(R.id.tv_todo_name)

        init {
            v.setOnClickListener(this)
            v.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }

        override fun onLongClick(v: View?): Boolean {
            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                longlistener.onItemLongClick(position)
            }

            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        context = parent.context
        Log.d("state", "TodoAdapter: onCreate")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.toDoName.text = list[position].name

        if(list[position].isCompleted){
            holder.toDoName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
//            holder.toDoName.setTextColor(ContextCompat.getColor(context, R.color.dark))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    interface OnItemLongclickListener{
        fun onItemLongClick(position: Int)
    }

}