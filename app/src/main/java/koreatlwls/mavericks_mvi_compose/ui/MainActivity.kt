package koreatlwls.mavericks_mvi_compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.airbnb.mvrx.MavericksView
import dagger.hilt.android.AndroidEntryPoint
import koreatlwls.mavericks_mvi_compose.ui.theme.MavericksMVIComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() , MavericksView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MavericksMVIComposeTheme {
                SearchScreen()
            }
        }

    }

    override fun invalidate() {

    }
}

