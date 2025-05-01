package com.jagteshwar.coroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jagteshwar.coroutines.ui.theme.CoroutinesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val TAG: String = "KOTLIN"
lateinit var viewModel: MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutinesTheme {

            }
        }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycleScope.launch {
            delay(2000)
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
        CoroutineScope(Dispatchers.Main).launch {
            executeTask()
        }



    }

    private suspend fun executeTask() {
        Log.d(TAG, "Before")

    }

    private suspend fun execute() {
        val parentJob = CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG, "Parent-> $coroutineContext")
            Log.d(TAG, "Parent Started")

            val childJob = launch(Dispatchers.IO) {
                Log.d(TAG, "Child-> $coroutineContext")
                Log.d(TAG, "Child Started")
                delay(5000)
                Log.d(TAG, "Child ended")
            }
            delay(3000)
            Log.d(TAG, "Child job cancelled")
            childJob.cancel()
            Log.d(TAG, "Parent Ended")
        }
        delay(1000)
        parentJob.cancel()
        parentJob.join()
        Log.d(TAG, "Parent Completed")
    }

    private suspend fun printFollowers() {
        CoroutineScope(Dispatchers.IO).launch {
            val fb = async { getFbFollowers() }
            val insta = async { getInstaFollowers() }

            Log.d(TAG, "fb-> ${fb.await()} || insta-> ${insta.await()}")
        }
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

