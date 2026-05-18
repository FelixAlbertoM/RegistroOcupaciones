 package edu.ucne.registroocupaciones.domain.empleado.repository

import edu.ucne.registroocupaciones.domain.empleado.model.Empleado
import kotlinx.coroutines.flow.Flow

interface EmpleadoRepository {

    fun observeEmpleados(): Flow<List<Empleado>>

    suspend fun getEmpleado(id: Int): Empleado?

    suspend fun upsert(empleado: Empleado): Int

    suspend fun delete(id: Int)

    suspend fun getEmpleadosByNombre(nombre: String): List<Empleado>
}