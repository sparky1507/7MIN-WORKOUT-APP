package com.example.a7minworkoutapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Histroy-table")
data class historyentity(
    @PrimaryKey
    val date:String
)
