package com.davidchaves.supplierapi.infra.db.order

import com.davidchaves.supplierapi.main.annotations.Repository
import org.springframework.data.jpa.repository.JpaRepository


@Repository
interface OrderJpaRepository : JpaRepository<OrderEntity, Int>