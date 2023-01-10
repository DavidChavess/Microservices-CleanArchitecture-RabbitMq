package com.davidchaves.supplierapi.infra.db.product

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val uuid: String,
    val name: String,
    val description: String?,
    val price: BigDecimal
) {
    constructor(id: Int) : this(id, "", "", "", BigDecimal(0))
}