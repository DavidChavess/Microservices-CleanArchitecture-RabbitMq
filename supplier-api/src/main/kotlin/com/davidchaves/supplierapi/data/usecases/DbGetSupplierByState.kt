package com.davidchaves.supplierapi.data.usecases

import com.davidchaves.supplierapi.data.exception.SupplierNotFound
import com.davidchaves.supplierapi.data.protocols.SupplierRepository
import com.davidchaves.supplierapi.domain.model.Supplier
import com.davidchaves.supplierapi.domain.usecases.GetSupplierByState
import com.davidchaves.supplierapi.main.annotations.Service

@Service
class DbGetSupplierByState(private val supplierRepository: SupplierRepository) : GetSupplierByState {
    override fun getSupplierByState(state: String): Supplier =
        supplierRepository.getByState(state) ?: throw SupplierNotFound()
}