package e.aman.demoproductapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import e.aman.demoproductapp.model.Product
import e.aman.demoproductapp.repository.ProductRepository

class MainViewModel : ViewModel() {

    private val repository = ProductRepository()

    fun insert(product: Product , context: Context){
        repository.insert(product , context)
    }

}