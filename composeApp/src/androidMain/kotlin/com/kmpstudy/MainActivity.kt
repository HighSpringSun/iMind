package com.kmpstudy

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kmpstudy.data.db.context
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bindSingleton

class MainActivity : ComponentActivity(), DIAware {
    override val di: DI
        get() = DI.lazy {
            bindSingleton<Context> { this@MainActivity.applicationContext }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        context = this.applicationContext
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AndroidApp()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}