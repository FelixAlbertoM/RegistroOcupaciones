package edu.ucne.registroocupaciones.domain.empleado.usecase

import edu.ucne.registroocupaciones.domain.empleado.repository.EmpleadoRepository
import javax.inject.Inject

class DeleteEmpleadoUseCase @Inject constructor(
    private val repository: EmpleadoRepository
) {
    suspend operator fun invoke(id: Int){
        if (id <= 0) throw IllegalArgumentException("El Id debe de ser mayor a 0")
        repository.delete(id)
    }
}