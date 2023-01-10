package com.davidchaves.supplierapi.infra.db.order.item

import com.davidchaves.supplierapi.infra.db.order.OrderEntity
import com.davidchaves.supplierapi.infra.db.product.ProductEntity
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "purchase_order_item")
class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val quantity: BigDecimal,
    val total: BigDecimal,
    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: ProductEntity,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: OrderEntity? = null
)

