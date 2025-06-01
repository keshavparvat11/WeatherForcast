package com.example.wetherforecast.widgets

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.wetherforecast.navigation.WeatherScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernTopBar(
    title: String ="",
    isMainScreen: Boolean = false,
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    icon: ImageVector? = null,
    onNavigationClick: () -> Unit ={},
    onActionClick: () -> Unit = {}

) {
    val manuState = remember {
        mutableStateOf(false)
    }
    val expended by remember { mutableStateOf(true) }
    val backgroundColor = MaterialTheme.colorScheme.primary
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(backgroundColor.copy(alpha = 0.9f), backgroundColor.copy(alpha = 0.6f))
    )
    if (manuState.value){
        showDropDownManu(manuState = manuState,navController = navController)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradientBrush)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold,
                        fontSize = 30.sp),
                    color = Color.Black
                )
            },
            navigationIcon = { if (icon != null){
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            },
            actions = {
                if (isMainScreen) {
                    IconButton(onClick = onActionClick) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    IconButton(onClick = {manuState.value = true}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                else Box {  }

            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent, // Uses gradient background
                scrolledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
            ),
            scrollBehavior = scrollBehavior
        )
    }
}
@Composable
fun showDropDownManu(
    manuState: MutableState<Boolean>,
    navController: NavController
) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf("Favorites", "About" , "Setting")
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 40.dp, right = 30.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(140.dp)
                .background(color = Color.White),
           )
               {
          items.forEach { item  ->
               DropdownMenuItem(
                   onClick = {
                       expanded = false
                       manuState.value = false
                   },
                   text = {
                      Row {
                           Icon(imageVector = when(item){
                               "Favorites" -> Icons.Default.FavoriteBorder
                               "About" -> Icons.Default.Info
                               else -> Icons.Default.Settings },
                               contentDescription = null,
                               tint = Color.Gray
                           )
                           Text(text = item,
                               color = Color.Gray)
                      }
                   },
                   modifier = Modifier.clickable(onClick = {
                       Log.d("Tagee", "showDropDownMan")
                       when(item){
                           "Favorites" -> navController.navigate(WeatherScreen.FavoriteScreen.name)
                           "About" -> navController.navigate(WeatherScreen.AboutScreen.name)
                           else -> navController.navigate(WeatherScreen.SettingScreen.name) }
                   })

               )


           }


        }
    }
}


//@Composable
//fun weatherTopBar(title : String = "",
//                  icon: ImageVector? = null,
//                  isMainScreen : Boolean = true,
//                  elevation: Dp = 4.dp,
//                 // navController: NavController,
//                  onAddActionClicked:() -> Unit ={},
//                  onButtonClicked:() -> Unit ={}
//                  ){
//
//    TopAppBar(
//        modifier = Modifier.height(65.dp),
//        title = {
//            Text(
//                text = title,
//                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold,
//                    fontSize = 30.sp)
//            )
//        },
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primary
//        ),
//        navigationIcon = {
//            if (!isMainScreen){
//            IconButton(onClick = { /* Handle navigation */ }) {
//                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
//            }}
//            else Spacer(modifier = Modifier.width(50.dp))
//        },
//       actions = {
//            IconButton(onClick = { /* Handle action */ }) {
//               Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Black)
//            }
//           IconButton(onClick = { /* Handle action */ }) {
//               Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)
//           }
//
//       }
//
//    )
//}
