package com.example.sqldemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * `AppDatabase` es una clase abstracta que representa la base de datos de la aplicación.
 * Utiliza Room para proporcionar una capa de abstracción sobre SQLite.
 *
 * @property entities Un array de las clases de entidad que representan las tablas en la base de datos.
 *                  En este caso, se define una sola entidad: `Email::class`.
 * @property version La versión actual del esquema de la base de datos.
 */
@Database(entities = arrayOf(Email::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Proporciona un método para obtener el DAO (`Data Access Object`) de `Email`.
     *
     * @return Una instancia de `EmailDao` para realizar operaciones en la tabla de `Email`.
     */
    abstract fun emailDao(): EmailDao

    /**
     * `companion object` contiene miembros estáticos que pertenecen a la clase `AppDatabase` en lugar de
     * a una instancia específica de la clase.
     */
    companion object {
        /**
         * `INSTANCE` mantiene una referencia a la instancia única de `AppDatabase`.
         *
         * `@Volatile` asegura que los cambios en `INSTANCE` sean visibles inmediatamente para todos los hilos.
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * `getDatabase` devuelve la instancia de la base de datos. Si no existe, crea una nueva.
         *
         * @param context El contexto de la aplicación.
         * @return Una instancia de `AppDatabase`.
         */
        fun getDatabase(
            context: Context
        ): AppDatabase {
            // Si INSTANCE no es null, se devuelve, en caso contrario, se sincroniza el acceso para que solo un hilo
            // pueda crear una instancia al mismo tiempo.
            return INSTANCE ?: synchronized(this) {
                // Se crea la base de datos.
                val instance = Room.databaseBuilder(
                    context, // Proporciona el contexto de la aplicación.
                    AppDatabase::class.java, // Especifica la clase de la base de datos.
                    "app_database" // Nombre del archivo de la base de datos.
                )
                    // Utiliza un archivo de base de datos preexistente en los recursos "assets".
                    .createFromAsset("database/Email.db")
                    .build() // Construye y devuelve la instancia de la base de datos.
                INSTANCE = instance // Guarda la instancia en INSTANCE para futuras llamadas.

                instance // Devuelve la instancia de la base de datos.
            }
        }
    }
}