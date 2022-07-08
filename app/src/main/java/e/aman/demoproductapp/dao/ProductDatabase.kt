package e.aman.demoproductapp.dao

import android.content.Context
import androidx.room.*
import e.aman.demoproductapp.Utils.Constants
import e.aman.demoproductapp.model.ColorTypeConverter
import e.aman.demoproductapp.model.Product

@Database(entities = [Product::class] , version = 1)
@TypeConverters(ColorTypeConverter::class)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao() :ProductDao

    /** get singleton instance of database **/
    companion object{
        private var instance : ProductDatabase? = null

        fun getInstance(context: Context) : ProductDatabase?{
            if(instance==null){
                synchronized(ProductDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext ,
                        ProductDatabase::class.java , Constants.DATABASE_NAME).allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance(){
            instance = null
        }
    }
}