package edu.ucne.registroocupaciones.domain.empleado.usecase


import edu.ucne.registroocupaciones.domain.empleado.model.Empleado
import edu.ucne.registroocupaciones.domain.empleado.repository.EmpleadoRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ObserveEmpleadoUseCaseTest {

    private lateinit var repository: EmpleadoRepository
    private lateinit var observeEmpleadoUseCase: ObserveEmpleadoUseCase

    @Before
    fun setup() {
        repository = mockk()
        observeEmpleadoUseCase = ObserveEmpleadoUseCase(repository)
    }

    @Test
    fun `retorna lista de empleados`() = runTest {
        val empleados = listOf(
            Empleado(
                empleadoId = 1,
                fechaIngreso = 1700000000000L,
                nombres = "Marcos Taveras",
                sexo = "Masculino",
                sueldo = 34000.0
            ),
            Empleado(
                empleadoId = 2,
                fechaIngreso = 1700100000000L,
                nombres = "Isabel Montero",
                sexo = "Femenino",
                sueldo = 41000.0
            )
        )
        every { repository.observeEmpleados() } returns flowOf(empleados)

        val result = observeEmpleadoUseCase().first()

        assertEquals(2, result.size)
        assertEquals("Marcos Taveras", result[0].nombres)
        assertEquals("Isabel Montero", result[1].nombres)
    }

    @Test
    fun `retorna lista vacia cuando no hay empleados`() = runTest {
        every { repository.observeEmpleados() } returns flowOf(emptyList())

        val result = observeEmpleadoUseCase().first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `retorna empleados con datos correctos`() = runTest {
        val empleados = listOf(
            Empleado(
                empleadoId = 5,
                fechaIngreso = 1700500000000L,
                nombres = "Raquel Duarte",
                sexo = "Femenino",
                sueldo = 55000.0
            )
        )
        every { repository.observeEmpleados() } returns flowOf(empleados)

        val result = observeEmpleadoUseCase().first()

        assertEquals(1, result.size)
        assertEquals(5, result[0].empleadoId)
        assertEquals("Raquel Duarte", result[0].nombres)
        assertEquals("Femenino", result[0].sexo)
        assertEquals(55000.0, result[0].sueldo)
    }
}