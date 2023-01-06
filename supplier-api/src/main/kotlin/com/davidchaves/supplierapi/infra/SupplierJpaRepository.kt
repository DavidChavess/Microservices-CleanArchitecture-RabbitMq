package com.davidchaves.supplierapi.infra

import com.davidchaves.supplierapi.data.protocols.SupplierRepository
import com.davidchaves.supplierapi.domain.model.Supplier
import com.davidchaves.supplierapi.main.annotations.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface SupplierJpaRepository : SupplierRepository, JpaRepository<SupplierEntity, Int> {
    override fun getByState(state: String): Supplier?
}
