package com.davidchaves.supplierapi.data.protocols

import com.davidchaves.supplierapi.domain.model.Product

interface ProductRepository {
    fun findByUuidIn(uuids: List<String>): List<Product>
}
