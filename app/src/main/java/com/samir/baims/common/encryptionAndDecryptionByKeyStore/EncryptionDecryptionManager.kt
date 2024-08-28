package com.samir.baims.common.encryptionAndDecryptionByKeyStore

import android.security.keystore.KeyProperties
import com.samir.baims.common.constants.Constants
import com.samir.baims.common.encryptionAndDecryptionByKeyStore.keyStoreIntegration.KeyStoreHelper
import com.samir.baims.common.encryptionAndDecryptionByKeyStore.keyStoreIntegration.models.KeyModelRequest
import com.samir.baims.common.encryptionAndDecryptionByKeyStore.keyStoreIntegration.models.KeyModelResponse

/**
 * * This class was constructed following the principles of Procedural Module Cohesion.
 */
class EncryptionDecryptionManager {

     fun createKey (): KeyModelResponse
    {
        return  KeyStoreHelper(
            KeyModelRequest(algorithm= KeyProperties.KEY_ALGORITHM_AES ,blockMode= KeyProperties.BLOCK_MODE_CBC,
            padding=KeyProperties.ENCRYPTION_PADDING_PKCS7 ,keyAliasName= Constants.SECRET_KEY_2024, provider="AndroidKeyStore")
        ).getKey()
    }

    fun encryptData (data :String, keyModelResponse: KeyModelResponse) : String
    {
        return EncryptionModule().apply(data,keyModelResponse)
    }

    fun decryptData (data :String, keyModelResponse: KeyModelResponse) : String
    {
        return DecryptionModule().apply(data,keyModelResponse)
    }

}