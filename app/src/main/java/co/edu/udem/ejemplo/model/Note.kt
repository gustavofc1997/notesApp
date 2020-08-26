package co.edu.udem.ejemplo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    var mId: Int? = null

    var date: String? = ""
    var description: String? = ""
    var reminderDate: String? = ""
    var title: String? = ""
    var reminder: Boolean = false
}
