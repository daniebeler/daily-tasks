package com.daniebeler.dailytasks.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.daniebeler.dailytasks.utils.TABLE_TASKS

@Entity(tableName = TABLE_TASKS)
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: Long,
    @ColumnInfo(defaultValue = "0")
    val lastInteracted: Long,
    val name: String,
    var isCompleted: Boolean
)