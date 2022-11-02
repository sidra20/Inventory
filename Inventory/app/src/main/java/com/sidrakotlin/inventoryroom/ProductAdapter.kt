package com.sidrakotlin.inventoryroom

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sidrakotlin.inventoryroom.databinding.ProductItemBinding

class ProductAdapter(val listener:updateClick, val context:Context): RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private val allPro = ArrayList<Product>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(view, parent, false)
        val obj = MyViewHolder(binding, listener)
        return obj
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val products = allPro[position]
        holder.binding.prodName.text = products.name
        holder.binding.prodPrice.text = "$ " + products.price
        holder.binding.prodQty.text = products.qty.toString()

        holder.binding.item.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Update/Delete")
                .setCancelable(true)
                .setMessage("Do you want to update or delete that product?")
                .setPositiveButton("Update", { dialogInterface, i ->

                    listener.update(products)
                })
                .setNegativeButton("Delete", {
                    dialogInterface, i->
                    listener.delete(products)
                })
                .setNeutralButton("Close", {dialogInterface, i->
                    dialogInterface.cancel()
                }).show()


        }

    }

    fun updateList(newList: List<Product>) {

        allPro.clear()
        allPro.addAll(newList)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
       return allPro.size
    }

    class MyViewHolder(val binding: ProductItemBinding, val listener: updateClick):RecyclerView.ViewHolder(binding.root)
    {

    }

    interface updateClick{
        fun update(product: Product)
        fun delete(product: Product)
    }



}