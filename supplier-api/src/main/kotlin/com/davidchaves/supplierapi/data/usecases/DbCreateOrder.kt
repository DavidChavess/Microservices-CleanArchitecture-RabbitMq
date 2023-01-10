package com.davidchaves.supplierapi.data.usecases

import com.davidchaves.supplierapi.data.exception.ProductNotFoundException
import com.davidchaves.supplierapi.data.protocols.OrderRepository
import com.davidchaves.supplierapi.data.protocols.ProductRepository
import com.davidchaves.supplierapi.domain.model.Order
import com.davidchaves.supplierapi.domain.model.OrderItem
import com.davidchaves.supplierapi.domain.model.Product
import com.davidchaves.supplierapi.domain.usecases.CreateOrder
import com.davidchaves.supplierapi.domain.usecases.model.OrderModel
import com.davidchaves.supplierapi.main.annotations.Service

@Service
class DbCreateOrder(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
) : CreateOrder {

    override fun createOrder(orderModel: OrderModel): Order {
        val uuids = orderModel.items.map { it.productUuid }
        val products = productRepository.findByUuidIn(uuids)
        val order = mapToOrder(orderModel, products)
        orderRepository.save(order)
        return order
    }

    private fun mapToOrder(orderModel: OrderModel, products: List<Product>): Order {
        val items: List<OrderItem> = orderModel.items.map {
            val product = products.find { product -> product.uuid == it.productUuid } ?: throw ProductNotFoundException()
            OrderItem(product, it.quantity)
        }
        return Order(items = items)
    }
}