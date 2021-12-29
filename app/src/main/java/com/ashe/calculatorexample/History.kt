package com.ashe.calculatorexample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    var data : String
){
    @PrimaryKey(autoGenerate = true) var id = 0
}
