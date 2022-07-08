package e.aman.demoproductapp.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import e.aman.demoproductapp.model.Product
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ProductDatabaseTest : TestCase(){

    private lateinit var db: ProductDatabase
    private lateinit var dao: ProductDao

    @Before
    public override fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context , ProductDatabase::class.java).build()
        dao = db.productDao()
    }

    @After
    public fun closeDb(){
        db.close()
    }

    @Test
    public fun writeAndRead(){

        val product = Product(1L , "Product 1" , "Test description" ,
        200.0 , 100.0 , 1 , ArrayList<String>() , ArrayList<String>())

        dao.insert(product)
        val list = dao.getAllProducts()
        assertTrue(list.contains(product))
    }

}