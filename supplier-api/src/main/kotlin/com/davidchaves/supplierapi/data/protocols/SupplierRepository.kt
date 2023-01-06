package com.davidchaves.supplierapi.data.protocols

import com.davidchaves.supplierapi.domain.model.Supplier

interface SupplierRepository {
    fun getByState(state: String): Supplier?
}
