package e.aman.demoproductapp.model

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "product")
data class Product(

    @PrimaryKey(autoGenerate = true)
    val id: Long=0,

    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "regularPrice")
    var regularPrice: Double,
    @ColumnInfo(name = "salePrice")
    var salePrice: Double,
    @ColumnInfo(name = "productPhoto")
    var productPhoto: Int,
    @ColumnInfo(name = "colors")
    var colors : ArrayList<String>,
    @ColumnInfo(name = "Stores")
    var stores : ArrayList<String>,



    )


class ColorTypeConverter{
    @TypeConverter
    fun fromString(value : String?): ArrayList<String>{
        val listType = object : TypeToken<ArrayList<String>>(){}.type
        return Gson().fromJson(value , listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>?): String{
        return Gson().toJson(list)
    }
}