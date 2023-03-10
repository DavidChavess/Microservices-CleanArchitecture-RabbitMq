package com.davidchaves.supplierapi.data.usecases

import com.davidchaves.supplierapi.data.exception.SupplierNotFound
import com.davidchaves.supplierapi.data.protocols.SupplierRepository
import com.davidchaves.supplierapi.domain.model.Supplier
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [MockitoExtension::class])
class DbGetSupplierByStateTest {

    @Mock
    private lateinit var supplierRepository: SupplierRepository

    @InjectMocks
    private lateinit var dbGetSupplierByState: DbGetSupplierByState

    @Test
    @DisplayName("Deve buscar fornecedor por estado")
    fun shouldFindSupplierByState() {
        given(supplierRepository.getByState("SP"))
            .willReturn(Supplier("Supplier 1", "Rua teste", "145", "SP"))

        val supplier: Supplier = dbGetSupplierByState.getSupplierByState("SP")

        assertEquals(Supplier("Supplier 1", "Rua teste", "145", "SP"), supplier)
    }

    @Test
    @DisplayName("Deve retornar fornecedor não encontrado")
    fun shouldReturnSupplierNotFound() {
        given(supplierRepository.getByState("SP")).willReturn(null)
        assertThrows<SupplierNotFound> { dbGetSupplierByState.getSupplierByState("SP") }
    }
}