package com.davidchaves.supplierapi.infra.db.order

import com.davidchaves.supplierapi.data.protocols.OrderRepository
import com.davidchaves.supplierapi.domain.model.Order
import com.davidchaves.supplierapi.infra.db.order.item.ItemEntity
import com.davidchaves.supplierapi.infra.db.product.ProductEntity
import com.davidchaves.supplierapi.main.annotations.Repository
import java.util.UUID

@Repository
class OrderRepositoryImpl(private val orderJpaRepository: OrderJpaRepository) : OrderRepository {
    override fun save(order: Order) {
        val itemsEntity = order.items.map {
            ItemEntity(
                quantity = it.quantity,
                total = it.total,
                product = ProductEntity(it.product.id)
            )
        }
        val orderEntity =
            OrderEntity(
                uuid = UUID.randomUUID().toString(),
                total = order.total,
                items = itemsEntity
            )

        itemsEntity.forEach { it.order = orderEntity }
        orderJpaRepository.save(orderEntity)
    }
}