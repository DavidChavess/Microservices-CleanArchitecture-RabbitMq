package com.davidchaves.storeapi.infra

import com.davidchaves.storeapi.data.protocols.SupplierClient
import com.davidchaves.storeapi.domain.models.Order
import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient("supplier", url = "http://localhost:8082")
interface SupplierFeignClient : SupplierClient {
    @PostMapping("/order")
    override fun createOrder(@RequestBody purchase: SavePurchaseModel): Order
}