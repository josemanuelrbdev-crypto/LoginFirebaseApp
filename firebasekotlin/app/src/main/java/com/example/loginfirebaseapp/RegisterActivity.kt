package com.example.loginfirebaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    // Instancias de Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Referencias del XML
        val etName = findViewById<EditText>(R.id.etRegisterName)
        val etEmail = findViewById<EditText>(R.id.etRegisterEmail)
        val etPassword = findViewById<EditText>(R.id.etRegisterPassword)
        val btnFinalizar = findViewById<Button>(R.id.btnFinalizarRegistro)

        btnFinalizar.setOnClickListener {
            val nombre = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (nombre.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

                // PASO 1: Crear usuario en Auth
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Obtenemos el ID del usuario recién creado
                            val userId = auth.currentUser?.uid

                            // PASO 2: Guardar nombre en Firestore
                            guardarUsuarioEnFirestore(userId, nombre, email)
                        } else {
                            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun guardarUsuarioEnFirestore(uid: String?, nombre: String, email: String) {
        if (uid == null) return

        // Creamos un mapa de datos
        val userMap = hashMapOf(
            "nombre" to nombre,
            "correo" to email
        )

        // Guardamos en la colección "usuarios" usando el UID como nombre del documento
        db.collection("usuarios").document(uid)
            .set(userMap)
            .addOnSuccessListener {
                Toast.makeText(this, "Registro completo", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finishAffinity() // Evita que el usuario regrese al registro
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al guardar datos: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}