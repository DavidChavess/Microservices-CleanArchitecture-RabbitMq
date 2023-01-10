package com.davidchaves.supplierapi.infra.db.product

import com.davidchaves.supplierapi.data.protocols.ProductRepository
import com.davidchaves.supplierapi.domain.model.Product
import com.davidchaves.supplierapi.main.annotations.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface ProductJpaRepository : ProductRepository, JpaRepository<ProductEntity, Int> {
    override fun findByUuidIn(uuids: List<String>): List<Product>
}
