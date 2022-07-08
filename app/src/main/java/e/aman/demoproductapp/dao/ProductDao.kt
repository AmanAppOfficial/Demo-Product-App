package e.aman.demoproductapp.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import e.aman.demoproductapp.model.Product

@Dao
interface ProductDao {

    @Insert
    fun insert(product: Product)

    @Query("Select * FROM product")
    fun getAllProducts() : List<Product>

    @Delete
    fun delete(product: Product)

    @Update
    fun update(product: Product)

}