package cn.chiichen.cook.utils

import cn.chiichen.cook.Global.stuffMap
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

private const val dataPath : String = "data.json"

data class Item(
    val name: String,
    val emoji: String
)

fun readJson() {
    // 读取文件内容
    val jsonString = File(dataPath).readText()

    // 使用Gson解析JSON
    val itemType = object : TypeToken<List<Item>>() {}.type
    val itemList: List<Item> = Gson().fromJson(jsonString, itemType)

    // 将name和emoji作为键值对存储在Map中
    stuffMap = itemList.associate { it.name to it.emoji }

}
