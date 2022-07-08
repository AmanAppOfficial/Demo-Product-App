package e.aman.demoproductapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import e.aman.demoproductapp.Utils.Constants
import e.aman.demoproductapp.dao.ProductDatabase
import e.aman.demoproductapp.databinding.ActivityProductBinding
import e.aman.demoproductapp.databinding.ProductImageDialogBinding
import e.aman.demoproductapp.databinding.UpdateDialogLayoutBinding
import e.aman.demoproductapp.model.Product

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gson = Gson()
        val item = gson.fromJson(intent.getStringExtra(Constants.PRODUCT), Product::class.java)

        binding.productName.text = item.name
        binding.productDesc.text = item.description
        binding.productSalePrice.text = item.salePrice.toString()
        binding.productRegularPrice.text = item.regularPrice.toString()
        binding.productImage.setImageResource(item.productPhoto)

        var stores = ""
        for(store in item.stores){
            stores = "$stores $store"
        }

        var colors = ""
        for(color in item.colors){
            colors = "$colors $color"
        }

        binding.productStores.text = stores
        binding.productColors.text = colors


        binding.productImage.setOnClickListener{
            showImageDialog(image = item.productPhoto)
        }


    }


    private fun showImageDialog(image : Int) {


        val dialog = Dialog(this)

        /** get binding of item layout **/
        val dialogBinding = ProductImageDialogBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(dialogBinding.root)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        dialogBinding.productImage.setImageResource(image)

        var closeText = dialogBinding.closeText

        closeText.setOnClickListener{
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()

        /** destroy instance of room db **/
        ProductDatabase.destroyInstance()
    }

}