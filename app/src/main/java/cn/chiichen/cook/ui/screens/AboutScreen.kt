package cn.chiichen.cook.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import cn.chiichen.cook.Global
import cn.chiichen.cook.R
import cn.chiichen.cook.model.RecipeEntry
import cn.chiichen.cook.utils.stuffToIcon
import cn.chiichen.cook.utils.toolsToIcon
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
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

    val records = Global.Records
    val context = LocalContext.current

    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                onClick = {
                    navController.navigate("about")
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }

            Spacer(modifier = Modifier.weight(0.8f))

            Text(text = "历史记录",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        LazyColumn(
            contentPadding = PaddingValues(15.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(records.entries.toList()) { entry ->
                Text(
                    text = "${getTime(entry.key.time)}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )
                Box(
                    modifier = Modifier
                        .drawWithContent {
                            drawRect(color = Color.LightGray, size = Size(3.dp.toPx(), size.height))
                            drawContent() },
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) { for (item in entry.value) RecipeEntry(context = context, item = item) }
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }

        }
    }

}

fun getTime(input: Date): String? {
    val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT' yyyy", Locale.US)
    inputFormat.timeZone = TimeZone.getTimeZone("GMT")
    val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
    val date = inputFormat.parse(input.toString())
    return date?.let { outputFormat.format(it) }
}



fun skip(context: Context, item: RecipeEntry) {
    val bilibiliUri = Uri.parse("bilibili://video/${item.bv}")
    val webUri = Uri.parse("https://www.bilibili.com/video/${item.bv}")

    val intent = Intent(Intent.ACTION_VIEW, bilibiliUri)

    if (intent.resolveActivity(context.packageManager) != null) {
        // 应用已安装，启动它
        context.startActivity(intent)
    } else {
        // 应用未安装，跳转到浏览器中的对应链接
        val webIntent = Intent(Intent.ACTION_VIEW, webUri)
        context.startActivity(webIntent)
    }
}

@Composable
fun RecipeEntry(
    context: Context,
    item: RecipeEntry
) {
    Button(
        onClick = { skip(context = context, item = item) }
    ) {
        Text(
            text = stuffToIcon(item.stuff) + " " + item.name,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )

        val tools: List<Int> = toolsToIcon(item.tools)
        Row(
            modifier = Modifier.weight(tools.size.toFloat() / 10)
        ) {

            for (tool in tools) {
                Icon(
                    painter = painterResource(id = tool),
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        }

    }

}

/*@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun AboutPreview(){

    val navController = rememberNavController()
//    AboutScreen(navController = navController)
*//*    FavorPage(navController)*//*
    HistoryPage(navController = navController)
}*/
