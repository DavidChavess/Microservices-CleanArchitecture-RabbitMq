package com.davidchaves.storeapi.mock;

import com.davidchaves.storeapi.domain.models.DeliveryAddressModel
import com.davidchaves.storeapi.domain.models.PurchaseItemModel
import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import java.math.BigDecimal

class SavePurchaseModelMock {
    companion object SavePurchaseModelMock {
        fun purchaseModelWithTwoProductsMock(): SavePurchaseModel =
            SavePurchaseModel(
                id = "any id",
                items = listOf(
                    PurchaseItemModel("uuid 1", BigDecimal.TEN),
                    PurchaseItemModel("uuid 2", BigDecimal.TEN)
                ),
                deliveryAddress = DeliveryAddressModel("Fulano", "Rua teste", "14.440.000"),
                status = "PENDING"
            )

        fun purchaseModelWithOneProductMock(): SavePurchaseModel =
            SavePurchaseModel(
                id = "any id",
                items = listOf(PurchaseItemModel("uuid 1", BigDecimal.TEN)),
                deliveryAddress = DeliveryAddressModel("Fulano", "Rua teste", "14.440.000"),
                status = "PENDING"
            )
    }
}
