package e.aman.demoproductapp.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import e.aman.demoproductapp.dao.ProductDao
import e.aman.demoproductapp.dao.ProductDatabase
import e.aman.demoproductapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductRepository(){

    /** insert data **/
    fun insert(product: Product , context : Context){
        val instance = ProductDatabase.getInstance(context)

        /** background thread to insert data **/
        CoroutineScope(IO).launch {
            instance!!.productDao().insert(product)
            withContext(Main){
                Toast.makeText(context.applicationContext , "inserted successfully" ,  Toast.LENGTH_SHORT).show()
            }
        }
    }

    /** get list of all products **/
    fun getAllProducts(context : Context) : List<Product>{
        val instance = ProductDatabase.getInstance(context)
        return instance!!.productDao().getAllProducts()
    }


    /** update product **/
    fun updateProduct(context: Context , product: Product){
        val instance = ProductDatabase.getInstance(context)
         instance!!.productDao().update(product)
    }

    /** delete product **/
    fun deleteProduct(context: Context , product: Product){
        val instance = ProductDatabase.getInstance(context)
        instance!!.productDao().delete(product)
    }


}