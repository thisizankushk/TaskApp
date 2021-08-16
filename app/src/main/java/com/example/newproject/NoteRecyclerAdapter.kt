package com.example.newproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class NoteRecyclerAdapter(val context: Context, val listener: INoteAdapter):
    RecyclerView.Adapter<NoteRecyclerAdapter.NoteViewHolder>() {
    val allnotes = ArrayList<Note>()
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val textView = itemView.findViewById<TextView>(R.id.textView)
        val deletebutton = itemView.findViewById<ImageView>(R.id.deletebutton)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val v =  NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent, false))

        v.deletebutton.setOnClickListener {
        listener.onItemClicked(allnotes[v.adapterPosition])
        }
        return v
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    val currentNode = allnotes[position]
        holder.textView.text = currentNode.task_name

    }
    fun updateList(newList : List<Note>){
        allnotes.clear()
        allnotes.addAll(newList)

        notifyDataSetChanged()
        //TODO change to diffutil

    }

    override fun getItemCount(): Int {
        return allnotes.size
    }

    interface INoteAdapter{

        fun onItemClicked(note: Note)
    }


}