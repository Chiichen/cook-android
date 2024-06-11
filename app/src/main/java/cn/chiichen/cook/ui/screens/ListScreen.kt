package cn.chiichen.cook.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.chiichen.cook.R
import cn.chiichen.cook.database.DatabaseManager
import cn.chiichen.cook.model.entity.Recipe
import cn.chiichen.cook.repository.RecipeRepository
import cn.chiichen.cook.utils.stuffToIcon
import cn.chiichen.cook.utils.toolsToIcon

@Composable
fun ListScreen() {
    val context = LocalContext.current
    val db = DatabaseManager.getAppDatabase(context)
    val recipeDao = db.recipeDao()
    val recipeRepository = RecipeRepository(context.assets, recipeDao)
    LaunchedEffect(Unit) {
        Log.i("ListScreen", "init csv data in list screen")
        recipeRepository.initCsvData();
    }

    var recipes: MutableList<Recipe> = mutableListOf(
        Recipe(
            "电饭煲版广式腊肠煲饭", "腊肠、米", "BV1NE411Q7Jj", "简单", "广式", "煲", "电饭煲"
        )
    )

    var number by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier.padding(12.dp)
    ) {

        Text(
            text = "今天吃什么",
            fontSize = 40.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (number > 1) {
                        number--
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_remove),
                    contentDescription = "add",
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = "$number",
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.padding(5.dp))

            IconButton(
                onClick = {
                    number++
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    recipeRepository.getRandomRecipe(number).value?.let { it ->
                        Log.i("ListScreen", String.format("Random %d recipe", number))
                        recipes.clear()
                        recipes.addAll(it)
                    }
                }
            ) {
                Text(text = "随机一下", fontSize = 20.sp)
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = ""
                )
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(15.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(recipes) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
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
                    }) {
                        Text(
                            text = stuffToIcon(item.stuff) + " " + item.name,
                            fontSize = 15.sp
                        )
                        var tools: List<Int> = toolsToIcon(item.tools)
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
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ListPreview() {
    ListScreen()
}
