package e.aman.demoproductapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import e.aman.demoproductapp.Utils.FakeData
import e.aman.demoproductapp.dao.ProductDatabase
import e.aman.demoproductapp.databinding.ActivityMainBinding
import e.aman.demoproductapp.model.Product
import e.aman.demoproductapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*** add listeners to views*/
        addListeners()


    }

    private fun insertFakeData() {
        /** insert fake data **/
        var editable = Editable.Factory.getInstance()
        insertIntoDb(editable.newEditable(FakeData.getProduct1().description), editable.newEditable(FakeData.getProduct1().name),
            editable.newEditable(FakeData.getProduct1().regularPrice.toString()),editable.newEditable(FakeData.getProduct1().salePrice.toString()))

        insertIntoDb(editable.newEditable(FakeData.getProduct2().description), editable.newEditable(FakeData.getProduct2().name),
            editable.newEditable(FakeData.getProduct2().regularPrice.toString()),editable.newEditable(FakeData.getProduct2().salePrice.toString()))

        insertIntoDb(editable.newEditable(FakeData.getProduct3().description), editable.newEditable(FakeData.getProduct3().name),
            editable.newEditable(FakeData.getProduct3().regularPrice.toString()),editable.newEditable(FakeData.getProduct3().salePrice.toString()))

    }

    private fun addListeners() {

        binding.insertMockBtn.setOnClickListener{
            /** insert fake data **/
            insertFakeData()
        }

        binding.showProductBtn.setOnClickListener{
            val listIntent = Intent(this,ProductListActivity::class.java)
            startActivity(listIntent)
        }

        binding.createProductBtn.setOnClickListener{
            binding.createLayout.visibility = View.VISIBLE
            clearFields()
        }

        binding.cancelDialogText.setOnClickListener {
            clearFields()
        }

        binding.saveDialogText.setOnClickListener{
            /** function to call insert request from view model **/
            insertIntoDb(binding.productDescEdittext.text , binding.productNameEdittext.text ,
                binding.productRegularPriceEdittext.text , binding.productSalePriceEdittext.text)
        }


    }

    /** clear text fields **/
    private fun clearFields(){
        binding.productSalePriceEdittext.setText("")
        binding.productRegularPriceEdittext.setText("")
        binding.productNameEdittext.setText("")
        binding.productDescEdittext.setText("")
    }


    /** function to call insert request from view model **/
    private fun insertIntoDb(desc: Editable?, name: Editable?, regularPrice: Editable?, salePrice: Editable?) {
        if(desc.isNullOrBlank() || name.isNullOrBlank() || regularPrice.isNullOrBlank() || salePrice.isNullOrBlank()){
            Toast.makeText(applicationContext , "Fields can't be empty!" , Toast.LENGTH_SHORT).show()
        }
        else{
            val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
            var colorList = ArrayList<String>();
            colorList.add("Red")
            colorList.add("Blue")
            colorList.add("Green")

            var storeList = ArrayList<String>();
            storeList.add("Store 1")
            storeList.add("Store 2")
            storeList.add("Store 3")

            var product = Product(name = name.toString() , description = desc.toString() , regularPrice = regularPrice.toString().toDouble() , salePrice = salePrice.toString().toDouble() , colors = colorList , productPhoto = R.drawable.image_watch , stores = storeList)
            viewModel.insert(product , applicationContext)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        /** destroy instance of room db **/
        ProductDatabase.destroyInstance()
    }

}