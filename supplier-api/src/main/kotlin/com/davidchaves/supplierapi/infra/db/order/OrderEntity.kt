package com.davidchaves.supplierapi.infra.db.order

import com.davidchaves.supplierapi.infra.db.order.item.ItemEntity
import java.math.BigDecimal
import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GenerationType
import javax.persistence.GeneratedValue
import javax.persistence.OneToMany
import javax.persistence.CascadeType
import javax.persistence.FetchType

@Entity
@Table(name = "purchase_order")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val uuid: String,
    val total: BigDecimal,
    val createdOn: OffsetDateTime? = OffsetDateTime.now(),
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val items: List<ItemEntity>
)