package com.davidchaves.supplierapi.domain.usecases

import com.davidchaves.supplierapi.domain.model.Supplier

interface GetSupplierByState {
    fun getSupplierByState(state: String): Supplier
}
