package com.davidchaves.supplierapi.domain.usecases

import com.davidchaves.supplierapi.domain.model.Order
import com.davidchaves.supplierapi.domain.usecases.model.OrderModel

interface CreateOrder {
    fun createOrder(orderModel: OrderModel): Order
}
