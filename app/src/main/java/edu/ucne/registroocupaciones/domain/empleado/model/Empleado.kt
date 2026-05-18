package edu.ucne.registroocupaciones.domain.empleado.model
data class Empleado(
    val empleadoId: Int = 0,
    val fechaIngreso: Long,
    val nombres: String,
    val sexo: String,
    val sueldo: Double
)