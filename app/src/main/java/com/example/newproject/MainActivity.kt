package com.example.newproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NoteRecyclerAdapter.INoteAdapter {

    private lateinit var viewmodel: NoteViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NoteRecyclerAdapter.NoteViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = NoteRecyclerAdapter(this, this)
        recyclerView.adapter = adapter

        viewmodel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        viewmodel.allNotes.observe(this, { list ->
            list?.let {
                (adapter as NoteRecyclerAdapter).updateList(it)
            }
        })
        val submitbutton = findViewById<Button>(R.id.submitbutton)
        submitbutton.setOnClickListener {
            submitbutton.animate().apply {
                duration = 500
                rotationXBy(360f)
            }.start()
            submitData()
        }
    }

    override fun onItemClicked(note: Note) {
        viewmodel.deleteNote(note)
//        val deletebutton = findViewById<ImageView>(R.id.deletebutton)
//        val cardView = findViewById<CardView>(R.id.cardview)
//        deletebutton.setOnClickListener{
//            deletebutton.animate().apply {
//                duration = 500
//                rotationXBy(360f)
//            }.start()
//        }
//        cardView.animate().apply {
//            duration = 1000
//            translationXBy(100f)
//        }.start()
        Toast.makeText(this, "Task deleted!!", Toast.LENGTH_SHORT).show()


    }

    private fun submitData() {
        val addedText: EditText = findViewById(R.id.editText)
        val noteText = addedText.text.toString()
        if (noteText.isNotEmpty()) {
            viewmodel.insertNote(Note(noteText))
            Toast.makeText(this, "Task Added!!", Toast.LENGTH_SHORT).show()
            addedText.setText("")
        }
    }
}