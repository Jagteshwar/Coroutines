package com.jagteshwar.coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jagteshwar.coroutines.ui.theme.CoroutinesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val TAG: String = "KOTLINFUN"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutinesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            Log.d(TAG, Thread.currentThread().name)
            count+=1
        }
        ) {
            Text(text = "Update Counter")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            Log.d(TAG, Thread.currentThread().name)
            doAction()
        }
        ) {
            Text(text = "Execute Task")
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun doAction(){
//    thread(start = true){
//        executeLongRunningTask()
//    }
    CoroutineScope(Dispatchers.IO).launch {
        Log.d(TAG, "IO-> ${Thread.currentThread().name}")
        executeLongRunningTask()
    }

//    GlobalScope.launch(Dispatchers.Main) {
//        Log.d(TAG, "Main-> ${Thread.currentThread().name}")
//    }
//
//    MainScope().launch(Dispatchers.Default){
//        Log.d(TAG, "Default-> ${Thread.currentThread().name}")
//    }
}

private fun executeLongRunningTask(){
    for(i in 1..1000000000L){

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoroutinesTheme {
        Greeting()
    }
}