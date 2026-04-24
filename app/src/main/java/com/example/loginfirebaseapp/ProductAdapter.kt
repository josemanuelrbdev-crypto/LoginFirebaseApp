package com.example.loginfirebaseapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private var productList: List<Product>,
    private val onEditClick: (Product) -> Unit,    // Para editar al tocar
    private val onDeleteClick: (String) -> Unit    // Para borrar al mantener presionado
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvItemNombre)
        val tvPrecio: TextView = view.findViewById(R.id.tvItemPrecio)
        val tvStock: TextView = view.findViewById(R.id.tvItemStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        holder.tvNombre.text = product.name
        holder.tvPrecio.text = "Precio: $${product.price}"
        holder.tvStock.text = "Stock: ${product.stock}"

        // Configuramos los clics en la tarjetita
        holder.itemView.setOnClickListener {
            onEditClick(product)
        }

        holder.itemView.setOnLongClickListener {
            onDeleteClick(product.id)
            true // Indica que el clic largo fue procesado
        }
    }

    override fun getItemCount() = productList.size

    fun updateData(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }
}