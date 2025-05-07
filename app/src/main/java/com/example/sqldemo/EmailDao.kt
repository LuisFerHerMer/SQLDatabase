/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sqldemo

import androidx.room.Dao
import androidx.room.Query

/**
 * `EmailDao` es una interfaz que define los métodos de acceso a datos para la entidad `Email`.
 * Un DAO (`Data Access Object`) se utiliza para interactuar con la base de datos y realizar
 * operaciones como consultar, insertar, actualizar o eliminar datos.
 *
 * Esta interfaz define métodos que se pueden usar para consultar la tabla de correos electrónicos.
 * Room implementará automáticamente el código necesario para ejecutar estas consultas.
 */
@Dao // Anotación que indica que esta interfaz es un DAO.
interface EmailDao {

    /**
     * `getAll` es una función que consulta todos los correos electrónicos de la tabla "email".
     *
     * @return Una lista de todos los correos electrónicos (`Email`) en la base de datos.
     */
    @Query("SELECT * FROM email") // Define la consulta SQL que se ejecutará.
    suspend fun getAll(): List<Email> // Método para obtener todos los correos. Es una función suspend, porque son operaciones costosas.
}