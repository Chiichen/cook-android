package cn.chiichen.cook.ui.screens

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.chiichen.cook.Global
import cn.chiichen.cook.R
import cn.chiichen.cook.model.entity.Recipe
import cn.chiichen.cook.utils.stuffToIcon
import cn.chiichen.cook.utils.toolsToIcon
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun HomeScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var index by remember { mutableIntStateOf(0) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = { IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) { Icon(Icons.Filled.Menu, null) } },
                title = {
                    Row(
                        modifier = Modifier.fillMaxSize().padding(10.dp, 0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                    ) { index = ModeSwitch(types = Global.Modes) }
                },
                backgroundColor = Color.White
            )
        },
        drawerContent = {
            AppDrawerContent(Global.EntryTypes)
        }
    ) { paddingValues -> Box(Modifier.padding(paddingValues)) {
        if (scaffoldState.drawerState.isClosed) AppContent(index = index)
    } }
}

@Composable
fun AppContent(index: Int) {
    val context = LocalContext.current
    val recipes = Global.Recipes
    var showDialog by remember { mutableStateOf(false) }
    var clickItem by remember { mutableStateOf(Recipe("", "", "", "", "", "", "")) }
    LazyColumn(
        contentPadding = PaddingValues(15.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(recipes) { item ->
            Row(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(onClick = {
                    showDialog = true
                    clickItem = item
                }) {
                    Text(text = stuffToIcon(item.stuff) + " " + item.name, fontSize = 14.sp, modifier = Modifier.weight(1f))
                    val tools: List<Int> = toolsToIcon(item.tools)
                    Row(modifier = Modifier.weight(tools.size.toFloat() / 10)) {
                        for (tool in tools) { Icon(painter = painterResource(id = tool), contentDescription = "", tint = Color.Black) }
                    }

                }
            }
        }

    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "确认跳转") },
            text = {
                Text(text = buildAnnotatedString {
                    append("你确定要跳转到 ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) { append(clickItem.name) }
                    append(" 吗?")
                })
            },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    val bilibiliUri = Uri.parse("bilibili://video/${clickItem.bv}")
                    val webUri = Uri.parse("https://www.bilibili.com/video/${clickItem.bv}")
                    val intent = Intent(Intent.ACTION_VIEW, bilibiliUri)
                    if (intent.resolveActivity(context.packageManager) != null) {
                        // 应用已安装，启动它
                        context.startActivity(intent)
                        addRecord(clickItem)
                    } else {
                        // 应用未安装，跳转到浏览器中的对应链接
                        val webIntent = Intent(Intent.ACTION_VIEW, webUri)
                        context.startActivity(webIntent)
                        addRecord(clickItem)
                    }
                }) { Text("确认") }
            },
            dismissButton = { Button(onClick = { showDialog = false }) { Text("取消") } }
        )
    }
}

fun addRecord(item: Recipe) {
    val time = getTime(Date())
    if (Global.Records.containsKey(time)) Global.Records[time]?.add(item)
    else time?.let { Global.Records.put(it, mutableListOf(item)) }
}

@Composable
fun AppDrawerContent(types: List<String>) {
    var choose by remember { mutableStateOf("") }
    var buttonWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Column {
        types.forEach { type ->
            Column {
                Button(
                    onClick = { choose = if (choose == type) "" else type },
                    modifier = Modifier.fillMaxWidth().padding(10.dp, 5.dp).onSizeChanged { buttonWidth = with(density) { it.width.toDp() } },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
                ) { MenuEntry(type = type, choose = choose) }
                Box(modifier = Modifier.offset(10.dp)) {
                    DropdownMenu(expanded = choose == type, onDismissRequest = { choose = "" }) {
                        Global.Data[type]?.forEach {
                            DropdownMenuItem(modifier = Modifier.width(buttonWidth), onClick = {}) { ElementEntry(text = it, type = type) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ModeSwitch(
    types: List<String>
): Int {
    var index by remember { mutableIntStateOf(0) }
    var text by remember { mutableStateOf(types[index]) }
    var backgroundColor by remember { mutableStateOf(Color.LightGray) }
    val lightGreen = colorResource(id = R.color.lightGreen)
    Button(
        onClick = {
            index = (index + 1) % types.size
            text = types[index]
            backgroundColor = if (backgroundColor == lightGreen) Color.LightGray else lightGreen
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) { Text(text = text) }
    return index
}

@Composable
fun MenuEntry(
    type: String,
    choose: String
) {
    Row {
        Text(
            text = type,
            modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
        )
        Icon(
            imageVector =
            if (choose == type) Icons.Filled.KeyboardArrowDown
            else Icons.Filled.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .weight(0.1f)
        )
    }
}

@Composable
fun ElementEntry(text: String, type: String) {
    val lightGreen = colorResource(id = R.color.lightGreen)
    var backgroundColor by remember {
        mutableStateOf(
            if (Global.Choice[type]?.contains(text) == true) lightGreen
            else Color.White
        )
    }
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor, contentColor = Color.Black),
        onClick = {
            backgroundColor = if (backgroundColor == lightGreen) Color.White else lightGreen
            update(type, text)
        }
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            if (type != "厨具") Text(text = stuffToIcon(stuff = text))
            else {
                val tools = toolsToIcon(tools = text)
                for (tool in tools) { Icon(painter = painterResource(id = tool), contentDescription = "", tint = Color.Black) }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text)
        }
    }
}




@Composable
@Preview(showBackground = true)
fun Demo() {
    HomeScreen()
}

fun update(
    type: String,
    element: String
) {
    if (Global.Choice[type]?.contains(element) == true) remove(type, element)
    else add(type, element)
}

fun add(
    type: String,
    element: String
) {
    Global.Choice[type]?.add(element)
}

fun remove(
    type: String,
    element: String
) {
    Global.Choice[type]?.remove(element)
}

