package com.samir.baims.data.dataSource.base

import android.content.SharedPreferences
import com.google.gson.Gson
import com.samir.baims.common.encryptionAndDecryptionByKeyStore.EncryptionDecryptionManager

/**
 *
 * /*
 * | Method           | Description                                                                                          | Operation Type    | Return Value                | Use Case                                                                 |
 * |------------------|------------------------------------------------------------------------------------------------------|-------------------|-----------------------------|--------------------------------------------------------------------------|
 * | editor.commit()  | Saves changes to SharedPreferences synchronously.                                                    | Synchronous       | true if successful, false otherwise | When you need to ensure changes are saved before proceeding, and you need to check the success of the operation. |
 * | editor.apply()   | Saves changes to SharedPreferences asynchronously in the background.                                 | Asynchronous      | None                        | Preferred for most cases where immediate confirmation is not needed. More efficient as it does not block the main thread. |
 * */
 *
 * // Save changes to SharedPreferences
 *
 * // Use editor.commit() to save changes synchronously
 * // - Synchronous Operation: Blocks the calling thread until the changes are saved.
 * // - Return Value: Returns true if the save was successful, false otherwise.
 * // - Use Case: When you need to ensure that the changes are saved before proceeding,
 * //   and you need to check the success of the operation.
 *
 * val success = editor.commit()  // Synchronously save changes
 *
 * if (success) {
 *     // Changes were successfully committed
 * } else {
 *     // Commit failed
 * }
 *
 * // Use editor.apply() to save changes asynchronously
 * // - Asynchronous Operation: Saves the changes in the background without blocking the calling thread.
 * // - No Return Value: Does not return a success/failure result.
 * // - Use Case: Preferred in most cases where you do not need immediate confirmation
 * //   of the save operation. It is more efficient as it does not block the main thread.
 *
 * editor.apply()  // Asynchronously save changes
 *
 */
class PreferencesManager(private val sharedPreferences: SharedPreferences) {

    private val editor: SharedPreferences.Editor = this.sharedPreferences.edit()

    //region Saving (String,Int,Boolean,Long)
    fun saveString(key: String , value: String){
        editor.putString(key , value).apply()
    }
    fun saveInt(key: String , value: Int){
        editor.putInt(key , value).apply()
    }
    fun saveBoolean(key: String , value: Boolean){
        editor.putBoolean(key , value).apply()
    }
    fun saveLong(key: String , value: Long){
        editor.putLong(key , value).apply()
    }
    //endregion

    //region Returning (String,Int,Boolean)
    fun returnString(key: String): String{
        return sharedPreferences.getString(key , "").toString()
    }
    fun returnInt(key: String): Int{
        return sharedPreferences.getInt(key , 0)
    }
    fun returnBoolean(key: String): Boolean{
        return sharedPreferences.getBoolean(key , false)
    }
    fun returnLong(key: String): Long{
        return sharedPreferences.getLong(key , 0L)
    }
    //endregion

    //region Saving And Retrieving Objects
    fun <Model> getObject(key: String, modelClass: Class<Model>): Model  {
        val jsonInStringEncrypted = this.sharedPreferences.getString(key, null) ?: throw IllegalArgumentException("No data found for the given key: $key")
        val gson = Gson()
        return gson.fromJson(EncryptionDecryptionManager().decryptData(data = jsonInStringEncrypted.toString(),keyModelResponse= EncryptionDecryptionManager().createKey() ), modelClass)
    }
    fun saveObject(key: String, model: Any) : Boolean {
        editor.putString(key,EncryptionDecryptionManager().encryptData(data = Gson().toJson(model),keyModelResponse= EncryptionDecryptionManager().createKey() ))
        return editor.commit()
    }
    //endregion

    //region Clear Shared Preference Reference
    fun clearSharedPreferences() {
        this.sharedPreferences.edit().clear().apply()
    }
    //endregion

}

