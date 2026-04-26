# 📱 Login Firebase App - Android Kotlin

Este repositorio contiene una aplicación de Android desarrollada en **Kotlin** que implementa un sistema de autenticación completo utilizando **Firebase**. El proyecto está diseñado como una base sólida para aplicaciones que requieren gestión de usuarios y un panel de control funcional.

## 🚀 Características

* **Autenticación con Firebase:** Registro de nuevos usuarios y validación de credenciales existentes.
* **Persistencia de Sesión:** El usuario permanece logueado incluso después de cerrar la aplicación.
* **UI Moderna:** * **Navigation Drawer:** Menú lateral desplegable para navegación fluida.
    * **Material Design Cards:** Interfaz basada en tarjetas para mostrar información en el Dashboard.
* **Configuración Avanzada:** Uso de **Gradle Kotlin DSL (.kts)** para una gestión de dependencias más limpia y tipada.

## 🛠️ Tecnologías y Herramientas

* **Lenguaje:** [Kotlin](https://kotlinlang.org/)
* **Backend:** [Firebase Authentication](https://firebase.google.com/docs/auth)
* **Componentes de Jetpack:** Navigation Component, ViewModel, y View Binding.
* **Build System:** Gradle con Kotlin DSL.

## 📸 Screenshots
<img width="314" height="524" alt="image" src="https://github.com/user-attachments/assets/90806014-0261-455b-9fc3-9afbf73b1f66" />
<img width="303" height="527" alt="image" src="https://github.com/user-attachments/assets/c6985827-a491-470d-b91c-ee0ccb42d3b5" />
<img width="308" height="523" alt="image" src="https://github.com/user-attachments/assets/1e6df7c4-f972-451d-bbe4-c76a1655174e" />
<img width="298" height="540" alt="image" src="https://github.com/user-attachments/assets/5c756ffa-bf6d-4a98-8950-dd27259a92d8" />



## ⚙️ Instalación y Configuración

Para ejecutar este proyecto localmente, sigue estos pasos:

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/tu-usuario/LoginFirebaseApp.git](https://github.com/tu-usuario/LoginFirebaseApp.git)
   
2. Configurar Firebase:

    Crea un proyecto en Firebase Console.
    
    Añade una aplicación Android con el nombre de paquete del proyecto.
    
    Descarga el archivo google-services.json y colócalo en el directorio app/ de tu proyecto.
    
    Habilita Email/Password en la sección de Authentication de Firebase.

3. Compilar y Ejecutar:

    Abre el proyecto en Android Studio.
    
    Sincroniza los archivos de Gradle.
    
    Ejecuta la app en un emulador o dispositivo físico.

📂 Estructura del Proyecto
    firebasekotlin/: Contiene la lógica principal de la aplicación, actividades y adaptadores.
    
    build.gradle.kts: Configuración de dependencias utilizando el lenguaje Kotlin.
