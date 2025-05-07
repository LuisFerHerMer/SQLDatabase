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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * `Email` es una clase de datos (data class) que representa la tabla "email" en la base de datos.
 * Cada instancia de esta clase representa una fila en la tabla de correos electrónicos.
 *
 * Esta clase se utiliza para mapear los datos de la tabla de la base de datos a objetos de Kotlin,
 * facilitando el manejo de la información de los correos electrónicos en la aplicación.
 *
 * @property id Identificador único del correo electrónico. Se genera automáticamente.
 * @property subject Asunto del correo electrónico.
 * @property sender Remitente del correo electrónico.
 * @property folder Carpeta a la que pertenece el correo electrónico (ej. "Inbox", "Sent", etc.).
 * @property starred Indica si el correo electrónico está marcado como destacado (true) o no (false).
 * @property read Indica si el correo electrónico ha sido leído (true) o no (false).
 * @property received Fecha y hora de recepción del correo electrónico, representada como un entero.
 */
@Entity(tableName = "email") // Anotación que define que esta clase es una entidad de Room y la mapea a la tabla "email".
data class Email(
    /**
     *`@PrimaryKey` define que la variable va a ser una clave primaria
     * `autoGenerate = true` indica que la clave primaria se genera automáticamente.
     */
    @PrimaryKey(autoGenerate = true) val id: Int, // ID del correo electrónico (clave primaria, autogenerada).
    /**
     * `@ColumnInfo` especifica los detalles de la columna.
     * `name = "subject"` se especifica el nombre de la columna.
     */
    @ColumnInfo(name = "subject") val subject: String, // Asunto del correo electrónico.
    @ColumnInfo(name = "sender") val sender: String, // Remitente del correo electrónico.
    @ColumnInfo(name = "folder") val folder: String, // Carpeta a la que pertenece el correo.
    @ColumnInfo(name = "starred") val starred: Boolean, // Indica si el correo está marcado como favorito.
    @ColumnInfo(name = "read") val read: Boolean, // Indica si el correo ha sido leído.
    @ColumnInfo(name = "received") val received: Int // Fecha/hora de recepción (puede ser un timestamp).
)