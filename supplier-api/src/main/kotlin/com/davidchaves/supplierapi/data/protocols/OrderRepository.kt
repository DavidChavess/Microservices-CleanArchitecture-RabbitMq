package com.davidchaves.supplierapi.data.protocols

import com.davidchaves.supplierapi.domain.model.Order

interface OrderRepository {
    fun save(order: Order)
}
