package com.example.newproject

import androidx.lifecycle.LiveData
import androidx.room.*



    @Dao
    interface Notedao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun adduser(note : Note)


        @Query("SELECT*FROM Notes order by id DESC")
        fun Readalldata() : LiveData<List<Note>>

        @Delete
        suspend fun delete(note: Note)


    }
