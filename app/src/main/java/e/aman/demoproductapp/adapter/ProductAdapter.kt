package e.aman.demoproductapp.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import e.aman.demoproductapp.ProductActivity
import e.aman.demoproductapp.Utils.Constants
import e.aman.demoproductapp.databinding.ProductItemLayoutBinding
import e.aman.demoproductapp.databinding.UpdateDialogLayoutBinding
import e.aman.demoproductapp.model.Product
import e.aman.demoproductapp.viewmodel.ProductListViewModel


/** recycler view adapter for displaying all products **/
class ProductAdapter(private var ctx : Context, private val list: List<Product>, private var productListViewModel: ProductListViewModel) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.context) , parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

     /** current item in holder **/
        val item = list[position]

        holder.productImageView.setImageResource(item.productPhoto)
        holder.productName.text = item.name
        holder.productDesc.text = item.description
        holder.regularPrice.text = item.regularPrice.toString() + "$"
        holder.salePrice.text = item.salePrice.toString() + "$"

        holder.edit.setOnClickListener{
            var product = item
            showUpdateDialog(product)
            notifyItemChanged(position)
        }

        holder.delete.setOnClickListener{
            productListViewModel.deleteProduct(ctx , item)
            notifyItemChanged(position)
        }

        holder.itemView.setOnClickListener{

            val gson = Gson()
            val myJson = gson.toJson(item)
            val displayIntent = Intent(ctx , ProductActivity::class.java)
            displayIntent.putExtra(Constants.PRODUCT , myJson)
            ctx.startActivity(displayIntent)

        }

    }

    /** function to update the product details**/
    private fun showUpdateDialog(product: Product) {

        var item = product
        val dialog = Dialog(ctx)

        /** get binding of item layout **/
        val dialogBinding = UpdateDialogLayoutBinding.inflate(LayoutInflater.from(ctx))
        dialog.setContentView(dialogBinding.root)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        var clearText = dialogBinding.cancelDialogText
        var okayText = dialogBinding.saveDialogText


        /** set product details on view **/
        dialogBinding.productDescEdittext.setText(item.description)
        dialogBinding.productNameEdittext.setText(item.name)
        dialogBinding.productRegularPriceEdittext.setText(item.regularPrice.toString())
        dialogBinding.productSalePriceEdittext.setText(item.salePrice.toString())


        clearText.setOnClickListener{
            dialog.dismiss()
        }

        okayText.setOnClickListener{

            var name = dialogBinding.productNameEdittext.text
            var desc = dialogBinding.productDescEdittext.text
            var regularPrice = dialogBinding.productRegularPriceEdittext.text
            var salePrice = dialogBinding.productSalePriceEdittext.text


            if(name.isNullOrBlank() || desc.isNullOrBlank() || regularPrice.isNullOrBlank() || salePrice.isNullOrBlank()){
                Toast.makeText(ctx.applicationContext , "Fields can't be empty!" , Toast.LENGTH_SHORT).show()
            }
            else{
                item.name = name.toString()
                item.description = desc.toString()
                item.regularPrice = regularPrice.toString().toDouble()
                item.salePrice = salePrice.toString().toDouble()

                /** call update request from view model **/
                productListViewModel.updateProductList(ctx , item)
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView : ProductItemLayoutBinding) : RecyclerView.ViewHolder(itemView.root){
        var productImageView = itemView.productImage
        var productName = itemView.productName
        var productDesc = itemView.productDesc
        var delete = itemView.productDelete
        var edit = itemView.productEdit
        var regularPrice = itemView.productRegularPrice
        var salePrice = itemView.productSalePrice
    }

}