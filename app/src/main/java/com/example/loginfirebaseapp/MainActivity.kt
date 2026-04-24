package com.example.loginfirebaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    // 1. Declaramos la variable para Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. Inicializamos Firebase Auth
        auth = FirebaseAuth.getInstance()

        // 3. Referenciamos los componentes de la interfaz (XML)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // --- LÓGICA DE LOGIN ---
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Función de Firebase para iniciar sesión
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Login correcto
                            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                            irAHome()
                        } else {
                            // Login fallido (contraseña incorrecta, usuario no existe, etc.)
                            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // --- LÓGICA DE REGISTRO (Opcional por si no tienes usuario aún) ---
        // --- DENTRO DE ONCREATE EN MAINACTIVITY ---
        btnRegister.setOnClickListener {
            // Solo abre la pantalla de registro
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // Comprobamos si el usuario ya está logueado al abrir la app
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            irAHome()
        }
    }

    private fun irAHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // Cerramos MainActivity para que no puedan volver atrás al login
    }
}