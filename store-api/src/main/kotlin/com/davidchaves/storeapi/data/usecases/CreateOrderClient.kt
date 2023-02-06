package com.davidchaves.storeapi.data.usecases

import com.davidchaves.storeapi.data.protocols.SupplierClient
import com.davidchaves.storeapi.domain.models.Order
import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import com.davidchaves.storeapi.domain.usecases.CreateOrder
import com.davidchaves.storeapi.main.annotations.Usecase

@Usecase
class CreateOrderClient(private val supplierClient: SupplierClient) : CreateOrder {

    override fun create(purchase: SavePurchaseModel): Order =
        supplierClient.createOrder(purchase)
}