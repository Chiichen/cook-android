package cn.chiichen.cook.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.chiichen.cook.R

@Composable
fun ListScreen() {

    var recipes: List<String> = mutableListOf()

    recipes = List(20) { "Item #$it" }

    var number by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier.padding(12.dp)
    ) {

        Text(text = "今天吃什么",
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
                    if (number > 1){
                        number--
                    }
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_remove),
                    contentDescription = "add",
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.padding(5.dp))

            Text(text = "$number",
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.padding(5.dp))

            IconButton(
                onClick = {
                    number++
                }
            ) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = "add",
                    modifier = Modifier.size(24.dp) )
            }
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                    onClick = {
                        initList(number,recipes)
                    }
                ) {
                Text(text = "随机一下")
                Icon(imageVector = Icons.Filled.Refresh,
                    contentDescription = "")
            }
        }

        LazyColumn (
            contentPadding = PaddingValues(15.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(recipes) {item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

fun initList(number: Int, recipes: List<String>) {
    /*TODO: 随机抽number个item并初始化recipes list*/
}

@Composable
@Preview(showBackground = true)
fun ListPreview(){
    ListScreen()
}
