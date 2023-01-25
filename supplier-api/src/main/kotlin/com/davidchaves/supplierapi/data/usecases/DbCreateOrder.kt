package com.davidchaves.supplierapi.data.usecases

import com.davidchaves.supplierapi.data.exception.ProductNotFoundException
import com.davidchaves.supplierapi.data.protocols.OrderRepository
import com.davidchaves.supplierapi.data.protocols.ProductRepository
import com.davidchaves.supplierapi.domain.model.Order
import com.davidchaves.supplierapi.domain.model.OrderItem
import com.davidchaves.supplierapi.domain.model.Product
import com.davidchaves.supplierapi.domain.usecases.CreateOrder
import com.davidchaves.supplierapi.domain.usecases.PurchaseModel
import com.davidchaves.supplierapi.main.annotations.UseCase

@UseCase
class DbCreateOrder(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
) : CreateOrder {

    override fun createOrder(purchaseModel: PurchaseModel): Order {
        val uuids = purchaseModel.items.map { it.productUuid }
        val products = productRepository.findByUuidIn(uuids)
        val order = mapToOrder(purchaseModel, products)
        orderRepository.save(order)
        return order
    }

    private fun mapToOrder(purchaseModel: PurchaseModel, products: List<Product>): Order {
        val items = purchaseModel.items.map {
            val product = products.find { product -> product.uuid == it.productUuid } ?: throw ProductNotFoundException()
            OrderItem(product, it.quantity)
        }
        return Order(purchaseId = purchaseModel.id, items = items)
    }
}