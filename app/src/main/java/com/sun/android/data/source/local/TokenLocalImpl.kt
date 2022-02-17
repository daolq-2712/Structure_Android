package com.sun.android.data.source.local

import com.sun.android.data.source.TokenDataSource
import com.sun.android.data.source.local.api.SharedPrefApi
import com.sun.android.data.source.local.api.sharedpref.SharedPrefKey

class TokenLocalImpl(private val sharedPrefApi: SharedPrefApi) :
    TokenDataSource.Local {

    override fun getToken() =
        sharedPrefApi.get(SharedPrefKey.KEY_TOKEN, String::class.java)

    override fun saveToken(token: String) =
        sharedPrefApi.put(SharedPrefKey.KEY_TOKEN, token)

    override fun clearToken() = sharedPrefApi.removeKey(SharedPrefKey.KEY_TOKEN)
}
