package com.example.loginfirebaseapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 1. Inicializar Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // 2. Referencias de la UI
        drawerLayout = findViewById(R.id.drawerLayout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        val tvUserNombre = findViewById<TextView>(R.id.tvUserNombre)

        // 3. Referencias del Header del Menú Lateral
        val headerView: View = navigationView.getHeaderView(0)
        val tvNavNombre = headerView.findViewById<TextView>(R.id.tvNavNombre)
        val tvNavCorreo = headerView.findViewById<TextView>(R.id.tvNavCorreo)

        // 4. Configurar la Hamburguesa para abrir el menú
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // 5. Cargar datos del usuario desde Firestore
        val userUid = auth.currentUser?.uid
        if (userUid != null) {
            db.collection("usuarios").document(userUid).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val nombre = document.getString("nombre")
                        val correo = document.getString("correo")

                        // Actualizar saludo y menú lateral
                        tvUserNombre.text = "Bienvenido, $nombre"
                        tvNavNombre?.text = nombre
                        tvNavCorreo?.text = correo
                    }
                }
        }

        // 6. Clics en el Menú Lateral (Drawer)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_logout -> {
                    auth.signOut()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "Perfil presionado desde el menú", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // 7. Clics en las Tarjetas (Cards) del Dashboard
        val cardPerfil = findViewById<MaterialCardView>(R.id.cardPerfil)
        val cardAjustes = findViewById<MaterialCardView>(R.id.cardAjustes)

        cardPerfil.setOnClickListener {
            Toast.makeText(this, "Abriendo tu Perfil...", Toast.LENGTH_SHORT).show()
        }

        cardAjustes.setOnClickListener {
            Toast.makeText(this, "Abriendo Ajustes...", Toast.LENGTH_SHORT).show()
        }
    }

    // Cerrar el menú si se presiona el botón "Atrás" del sistema
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}