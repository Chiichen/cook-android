package cn.chiichen.cook.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.chiichen.cook.R

@Composable
fun AboutScreen(navController: NavController) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Text(text = "我的",
            fontSize = 40.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(
                onClick = {
                    navController.navigate("favor")
                },
                modifier = Modifier.width(100.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Favorite,
                        contentDescription = "favor",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(text = "我的收藏")
                }

            }

            Spacer(modifier = Modifier.size(60.dp))

            Button(
                onClick = {
                    navController.navigate("history")
                },
                modifier = Modifier.width(100.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_history),
                        contentDescription = "history",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(text = "历史记录")
                }
            }
        }
        Spacer(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun FavorPage(navController: NavController){
    Row (
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(
            onClick = {
                navController.navigate("about")
            }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(text = "我的收藏",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.weight(1f))
    }

    /*TODO*/
}

@Composable
fun HistoryPage(navController: NavHostController) {
    Row (
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(
            onClick = {
                navController.navigate("about")
            }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(text = "历史记录",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.weight(1f))
    }
    /*TODO*/
}

@Composable
@Preview(showBackground = true)
fun AboutPreview(){

    val navController = rememberNavController()
//    AboutScreen(navController = navController)
    FavorPage(navController)
}
