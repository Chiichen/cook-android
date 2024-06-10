package cn.chiichen.cook

import android.os.Build
import androidx.annotation.RequiresApi
import cn.chiichen.cook.model.RecipeEntry
import java.time.LocalDate
import java.util.TreeMap

object Global {

    var stuffMap: Map<String, String> = mutableMapOf(
        "土豆" to "🥔", "胡萝卜" to "🥕", "花菜" to "🥦",
        "白萝卜" to "🥣", "西葫芦" to "🥒", "番茄" to "🍅",
        "芹菜" to "🥬", "黄瓜" to "🥒", "洋葱" to "🧅",
        "莴笋" to "🎍", "菌菇" to "🍄", "茄子" to "🍆",
        "豆腐" to "🍲", "包菜" to "🥗", "白菜" to "🥬",
        "午餐肉" to "🥓", "香肠" to "🌭", "腊肠" to "🌭",
        "鸡肉" to "🐤", "猪肉" to "🐷", "鸡蛋" to "🥚",
        "虾" to "🦐", "牛肉" to "🐮", "骨头" to "🦴",
        "鱼（Todo）" to "🐟", "面食" to "🍝", "面包" to "🍞",
        "米" to "🍚", "方便面" to "🍜"
    )

    var toolsMap: Map<String, Int> = mutableMapOf(
        "烤箱" to R.drawable.ic_oven,
        "空气炸锅" to R.drawable.ic_airfryer,
        "微波炉" to R.drawable.ic_microwave,
        "电饭煲" to R.drawable.ic_cooker,
        "锅" to R.drawable.ic_pot,
    )
    val Modes: List<String> = listOf("一般模式", "严格模式")

    val EntryTypes: List<String> = listOf("肉类", "蔬菜", "主食", "厨具")

    val Data: Map<String, List<String>> = mapOf(
        "肉类" to listOf("牛肉", "鸡肉", "猪肉"),
        "蔬菜" to listOf("白菜", "胡萝卜", "番茄", "土豆"),
        "主食" to listOf("米", "面食", "面包"),
        "厨具" to listOf("锅", "烤箱", "微波炉")
    )


    val Choice: MutableMap<String, MutableSet<String>> = mutableMapOf(
        "肉类" to mutableSetOf(),
        "蔬菜" to mutableSetOf(),
        "主食" to mutableSetOf(),
        "厨具" to mutableSetOf()
    )


    // 暂时用作测试


    var Recipes: MutableList<RecipeEntry> = mutableListOf(
        RecipeEntry(
            "电饭煲版罗宋汤",
            "牛肉、番茄、洋葱、芹菜、胡萝卜、土豆、包菜、香肠",
            "BV16Q4y1m7nU",
            "简单",
            "杂烩",
            "煲",
            "电饭煲",
        ),
        RecipeEntry(
            "空气炸锅炸万物",
            "土豆、胡萝卜、花菜、西葫芦、芹菜、洋葱、莴笋、菌菇、茄子、包菜、午餐肉、香肠、鸡肉、猪肉、虾、牛肉、鸡肉、番茄、豆腐、面包",
            "BV1wL411F7Cd",
            "简单",
            "杂烩",
            "炸",
            "空气炸锅",
        ),
        RecipeEntry(
            "骨头汤火锅锅底做法（全鸡版）",
            "骨头、土豆、胡萝卜、花菜、白萝卜、西葫芦、芹菜、菌菇、豆腐、包菜、白菜、午餐肉、鸡肉、猪肉、虾、牛肉、面食、方便面",
            "BV1bi4y187ro",
            "困难",
            "杂烩",
            "",
            "锅",
        ),
        RecipeEntry(
            "清汤锅万能高汤做法（鸡蛋+猪肉）",
            "猪肉、鸡蛋、土豆、胡萝卜、花菜、白萝卜、西葫芦、芹菜、菌菇、豆腐、包菜、白菜、午餐肉、鸡肉、虾、牛肉、面食、方便面",
            "BV1zD4y197Us",
            "困难",
            "杂烩",
            "",
            "锅",
        )

    )

    @RequiresApi(Build.VERSION_CODES.O)
    var Records: MutableMap<LocalDate, MutableList<RecipeEntry>> = TreeMap<LocalDate, MutableList<RecipeEntry>>(reverseOrder()).apply {
        put(LocalDate.of(2022, 11, 30), Recipes)
        put(LocalDate.of(2022, 12, 2), Recipes)
        put(LocalDate.of(2022, 12, 1), Recipes)
    }

}