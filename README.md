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

## Nuevo Diseño
<img width="299" height="553" alt="image" src="https://github.com/user-attachments/assets/e9de026a-63cd-4946-8687-c13b1ee5c6fe" />
<img width="280" height="548" alt="image" src="https://github.com/user-attachments/assets/4031f6cf-3227-458b-8055-60b8628323b3" />
<img width="282" height="555" alt="image" src="https://github.com/user-attachments/assets/13365bea-302e-4c32-9f42-9f574cbb4454" />
<img width="282" height="554" alt="image" src="https://github.com/user-attachments/assets/d2ee5bf5-3913-4aed-9943-0ed90eee9cc6" />
<img width="286" height="554" alt="image" src="https://github.com/user-attachments/assets/37c642e3-e0fa-4d2f-a94a-98779d4fd40b" />


## Link para descargar el APK
https://josemanuelrbdev-crypto.github.io/LoginFirebaseApp/





## ⚙️ Instalación y Configuración

Para ejecutar este proyecto localmente, sigue estos pasos:

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/josemanuelrbdev-crypto/LoginFirebaseApp.git
   
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
