package e.aman.demoproductapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import e.aman.demoproductapp.adapter.ProductAdapter
import e.aman.demoproductapp.dao.ProductDatabase
import e.aman.demoproductapp.databinding.ActivityProductListBinding
import e.aman.demoproductapp.viewmodel.ProductListViewModel

class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding
    lateinit var viewModel : ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProductListViewModel::class.java]

        initRecyclerView()

    }

    private fun initRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL;
        binding.productRecyclerview.layoutManager = linearLayoutManager


        /** hit products get method **/
        viewModel.getProductList(applicationContext)

        /** view model observer to product list changes **/
        viewModel.productList.observe(this) {

            Log.e("productst" , it.size.toString())

            binding.homeProgressBar.visibility = View.VISIBLE
            binding.productRecyclerview.visibility = View.GONE



            /** set recyclerview adpater **/
            val adapter = ProductAdapter(ProductListActivity@this , it , viewModel)
            binding.productRecyclerview.adapter = adapter
            binding.homeProgressBar.visibility = View.GONE
            binding.productRecyclerview.visibility = View.VISIBLE

            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        /** destroy instance of room db **/
        ProductDatabase.destroyInstance()
    }

}