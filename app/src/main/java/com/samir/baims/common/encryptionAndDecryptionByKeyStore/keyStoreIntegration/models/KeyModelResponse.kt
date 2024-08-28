package com.samir.baims.common.encryptionAndDecryptionByKeyStore.keyStoreIntegration.models

import javax.crypto.SecretKey

data class KeyModelResponse(
    val transformation: String,
    val secretKey :SecretKey
)
