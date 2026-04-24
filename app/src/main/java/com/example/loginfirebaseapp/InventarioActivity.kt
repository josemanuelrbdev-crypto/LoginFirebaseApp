package com.example.loginfirebaseapp

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class InventarioActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)

        db = FirebaseFirestore.getInstance()
        val rv = findViewById<RecyclerView>(R.id.rvInventario)

        val snack = com.google.android.material.snackbar.Snackbar.make(
            findViewById(R.id.rvInventario),
            "Tip: Toca para editar o mantén  para borrar",
            com.google.android.material.snackbar.Snackbar.LENGTH_LONG
        )
        snack.show()

        rv.layoutManager = LinearLayoutManager(this)

        // Inicializamos el Adapter con las funciones de Editar y Borrar
        adapter = ProductAdapter(
            productList = emptyList(),
            onEditClick = { producto -> mostrarDialogoEditar(producto) },
            onDeleteClick = { id -> confirmarEliminacion(id) }
        )

        rv.adapter = adapter
        consultarProductos()
    }

    private fun consultarProductos() {
        db.collection("inventario").addSnapshotListener { snapshot, e ->
            if (e != null) return@addSnapshotListener

            if (snapshot != null) {
                // Mapeamos los documentos para incluir el ID de Firestore
                val listaProductos = snapshot.documents.mapNotNull { doc ->
                    val p = doc.toObject(Product::class.java)
                    p?.copy(id = doc.id) // Importante para saber qué borrar/editar
                }
                adapter.updateData(listaProductos)
            }
        }
    }

    private fun mostrarDialogoEditar(product: Product) {
        val builder = AlertDialog.Builder(this)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_product, null)

        val etName = dialogLayout.findViewById<EditText>(R.id.etDialogNombre)
        val etPrice = dialogLayout.findViewById<EditText>(R.id.etDialogPrecio)
        val etStock = dialogLayout.findViewById<EditText>(R.id.etDialogStock)

        // Llenamos los campos con los datos actuales
        etName.setText(product.name)
        etPrice.setText(product.price.toString())
        etStock.setText(product.stock.toString())

        builder.setTitle("Actualizar Producto")
        builder.setView(dialogLayout)
        builder.setPositiveButton("Actualizar") { _, _ ->
            val name = etName.text.toString()
            val price = etPrice.text.toString().toDoubleOrNull() ?: 0.0
            val stock = etStock.text.toString().toIntOrNull() ?: 0

            val data = mapOf(
                "name" to name,
                "price" to price,
                "stock" to stock
            )

            db.collection("inventario").document(product.id).update(data)
                .addOnSuccessListener { Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show() }
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun confirmarEliminacion(id: String) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Producto")
            .setMessage("¿Estás seguro de que quieres eliminar este producto?")
            .setPositiveButton("Eliminar") { _, _ ->
                db.collection("inventario").document(id).delete()
                    .addOnSuccessListener { Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show() }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}