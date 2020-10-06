package com.example.androidgettingstarted

import android.content.Context
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.core.Amplify

class Backend private constructor() {

    companion object  {
        private const val TAG = "Backend"
        var shared : Backend = Backend()
            private set
    }

    fun initialize(applicationContext: Context) : Backend {
        try {
            Amplify.configure(applicationContext)
            Log.i(TAG, "Initialized Amplify")
        } catch (e: AmplifyException) {
            Log.e(TAG, "Could not initialize Amplify", e)
        }
        return this
    }
}