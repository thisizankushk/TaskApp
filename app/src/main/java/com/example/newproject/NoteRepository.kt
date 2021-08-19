package com.example.newproject

import androidx.lifecycle.LiveData

class NoteRepository(private val notedao: Notedao) {

    var allNotes: LiveData<List<Note>> = notedao.Readalldata()
    suspend fun insert(note: Note) {
        notedao.adduser(note)

    }

    suspend fun delete(note: Note) {

        notedao.delete(note)

    }


}