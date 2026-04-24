package com.example.loginfirebaseapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var drawerLayout: DrawerLayout

    // Variable para la foto del menú lateral
    private lateinit var imgNavLogo: ImageView
    private lateinit var pickImageLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        drawerLayout = findViewById(R.id.drawerLayout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        val tvUserNombre = findViewById<TextView>(R.id.tvUserNombre)

        // --- CONFIGURACIÓN DEL HEADER ---
        val headerView: View = navigationView.getHeaderView(0)
        val tvNavNombre = headerView.findViewById<TextView>(R.id.tvNavNombre)
        val tvNavCorreo = headerView.findViewById<TextView>(R.id.tvNavCorreo)
        imgNavLogo = headerView.findViewById(R.id.imgNavLogo) // ID que pusimos en nav_header.xml

        // 1. Inicializar el lanzador para la galería
        pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                // Aquí se pone la imagen seleccionada en el círculo
                imgNavLogo.setImageURI(uri)
                Toast.makeText(this, "Foto actualizada", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. Clic en la foto para abrir galería
        imgNavLogo.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Cargar datos del usuario
        val userUid = auth.currentUser?.uid
        if (userUid != null) {
            db.collection("usuarios").document(userUid).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val nombre = document.getString("nombre")
                        val correo = document.getString("correo")
                        tvUserNombre.text = "Bienvenido, $nombre"
                        tvNavNombre?.text = nombre
                        tvNavCorreo?.text = correo
                    }
                }
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_logout -> {
                    auth.signOut()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // --- CLICS EN LAS TARJETAS ---
        findViewById<MaterialCardView>(R.id.cardInventario).setOnClickListener {
            startActivity(Intent(this, InventarioActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardAjustes).setOnClickListener {
            showProductDialog()
        }
    }

    // --- DIÁLOGO CRUD (CREAR PRODUCTO) ---
    private fun showProductDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_product, null)

        val etName = dialogLayout.findViewById<EditText>(R.id.etDialogNombre)
        val etPrice = dialogLayout.findViewById<EditText>(R.id.etDialogPrecio)
        val etStock = dialogLayout.findViewById<EditText>(R.id.etDialogStock)

        builder.setTitle("Nuevo Producto")
        builder.setView(dialogLayout)
        builder.setPositiveButton("Guardar") { _, _ ->
            val name = etName.text.toString()
            val price = etPrice.text.toString().toDoubleOrNull() ?: 0.0
            val stock = etStock.text.toString().toIntOrNull() ?: 0

            if (name.isNotEmpty()) createProduct(name, price, stock)
            else Toast.makeText(this, "Nombre obligatorio", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun createProduct(name: String, price: Double, stock: Int) {
        val newProduct = hashMapOf("name" to name, "price" to price, "stock" to stock)
        db.collection("inventario").add(newProduct)
            .addOnSuccessListener { Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show() }
            .addOnFailureListener { Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show() }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START)
        else super.onBackPressed()
    }
}