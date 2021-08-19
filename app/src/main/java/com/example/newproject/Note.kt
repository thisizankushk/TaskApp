package com.example.newproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(@ColumnInfo(name = "Tasks") val task_name: String?) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}


