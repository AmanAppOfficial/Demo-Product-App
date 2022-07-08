package e.aman.demoproductapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import e.aman.demoproductapp.model.Product
import e.aman.demoproductapp.repository.ProductRepository


class ProductListViewModel : ViewModel() {

    private val repository = ProductRepository()
    var productList = MutableLiveData<List<Product>>()

    fun insert(product: Product , context: Context){
        repository.insert(product , context)
        Log.e("ininsert:" ,
            repository.getAllProducts(context = context).size.toString())
        productList.value = repository.getAllProducts(context = context)
    }
    fun getProductList(context : Context){
        productList.value = repository.getAllProducts(context = context)
    }

    fun updateProductList(context: Context , product: Product){
        repository.updateProduct(context , product)
        productList.value = repository.getAllProducts(context = context)
    }

    fun deleteProduct(context: Context , product: Product){
        repository.deleteProduct(context , product)
        productList.value = repository.getAllProducts(context = context)
    }
}