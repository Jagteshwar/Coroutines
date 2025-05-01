package com.jagteshwar.coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jagteshwar.coroutines.ui.theme.CoroutinesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val TAG: String = "KOTLIN"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutinesTheme {

            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            printFollowers()
        }


    }

    private suspend fun printFollowers() {
        var fbFollowers = 0
       val job =  CoroutineScope(Dispatchers.IO).launch {
            fbFollowers = getFbFollowers()
        }
        job.join()
        Log.d(TAG, "fb followers: $fbFollowers")

        val insta = CoroutineScope(Dispatchers.IO).async {
            getInstaFollowers()
        }
        Log.d(TAG, "insta followers: ${insta.await()}")

    }

    private suspend fun getFbFollowers(): Int {
        delay(1000)
        return 54
    }
    private suspend fun getInstaFollowers(): Int {
        delay(1000)
        return 99
    }
}

