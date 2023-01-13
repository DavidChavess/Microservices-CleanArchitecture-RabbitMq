package com.davidchaves.storeapi.mock;

import com.davidchaves.storeapi.domain.models.DeliveryAddressModel
import com.davidchaves.storeapi.domain.models.PurchaseItemModel
import com.davidchaves.storeapi.domain.models.SavePurchaseModel
import java.math.BigDecimal

class SavePurchaseModelMock {
    companion object SavePurchaseModelMock {
        fun purchaseModelWithTwoProductsMock(): SavePurchaseModel {
            return SavePurchaseModel(
                listOf(
                    PurchaseItemModel("uuid 1", BigDecimal.TEN),
                    PurchaseItemModel("uuid 2", BigDecimal.TEN)
                ),
                DeliveryAddressModel("Fulano", "Rua teste", "14.440.000"),
                "PENDING"
            )
        }

        fun purchaseModelWithOneProductMock(): SavePurchaseModel {
            return SavePurchaseModel(
                listOf(
                    PurchaseItemModel("uuid 1", BigDecimal.TEN),
                ), DeliveryAddressModel("Fulano", "Rua teste", "14.440.000"),
                "PENDING"
            )
        }
    }
}
