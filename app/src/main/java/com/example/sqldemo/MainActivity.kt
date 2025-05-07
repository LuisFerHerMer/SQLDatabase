package com.example.sqldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sqldemo.ui.theme.SQLDemoTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * `MainActivity` es la actividad principal de la aplicación SQLDemo.
 * Esta actividad se encarga de inicializar la interfaz de usuario y de
 * realizar algunas operaciones iniciales con la base de datos.
 */
class MainActivity : ComponentActivity() {

    /**
     * `onCreate` es el método llamado cuando se crea la actividad por primera vez.
     * Aquí se configuran los componentes de la interfaz de usuario y se realizan
     * operaciones iniciales como acceder a la base de datos.
     *
     * @param savedInstanceState Un objeto Bundle que contiene el estado guardado de la actividad, si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama al método onCreate de la superclase ComponentActivity para realizar las configuraciones iniciales.
        super.onCreate(savedInstanceState)
        /**
         * `GlobalScope.launch` inicia una nueva corrutina en el ámbito global.
         * Esto se utiliza para ejecutar código que puede tomar un tiempo considerable,
         * como las operaciones de acceso a la base de datos, fuera del hilo principal.
         *
         * En este caso, se está accediendo a la base de datos para obtener todos los correos.
         *
         * ¡IMPORTANTE!
         *
         * `GlobalScope` debe utilizarse con precaución, ya que la corrutina se mantiene activa
         * mientras la aplicación esté en ejecución y no está ligada al ciclo de vida de ningún componente.
         * En su lugar, se deben utilizar ámbitos de corrutinas específicos para los componentes,
         * como `lifecycleScope` o `viewModelScope`, para gestionar mejor el ciclo de vida de las corrutinas.
         */
        GlobalScope.launch {
            // Obtiene una instancia de la base de datos y luego el DAO de Email,
            // para finalmente llamar a getAll() y obtener todos los correos electrónicos.
            AppDatabase.getDatabase(applicationContext).emailDao().getAll()
        }
        // Configura el contenido de la actividad utilizando Jetpack Compose.
        setContent {
            // Aplica el tema SQLDemoTheme a la interfaz de usuario.
            SQLDemoTheme {
                // Un contenedor de superficie que usa el color de fondo del tema.
                Surface(
                    modifier = Modifier.fillMaxSize(), // Hace que la superficie ocupe todo el espacio disponible.
                    color = MaterialTheme.colors.background // Usa el color de fondo del tema de Material.
                ) {
                    // Un contenedor Box para centrar el texto.
                    Box(
                        modifier = Modifier.fillMaxSize(), // Hace que el Box ocupe todo el espacio disponible.
                        contentAlignment = Alignment.Center // Centra el contenido dentro del Box.
                    ) {
                        // Muestra un texto indicando que la base de datos está lista.
                        Text("The database is ready!")
                    }
                }
            }
        }
    }
}