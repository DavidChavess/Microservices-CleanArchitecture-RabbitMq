package com.davidchaves.storeapi.domain.usecases

import com.davidchaves.storeapi.domain.models.SavePaymentModel

interface SavePayment {
    fun save(savePurchaseModel: SavePaymentModel)
}
