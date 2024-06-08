package cn.chiichen.cook

object Global {

    var stuffMap: Map<String, String> = mutableMapOf(
        "土豆" to "🥔",        "胡萝卜" to "🥕",        "花菜" to "🥦",
        "白萝卜" to "🥣",        "西葫芦" to "🥒",        "番茄" to "🍅",
        "芹菜" to "🥬",        "黄瓜" to "🥒",        "洋葱" to "🧅",
        "莴笋" to "🎍",        "菌菇" to "🍄",        "茄子" to "🍆",
        "豆腐" to "🍲",        "包菜" to "🥗",        "白菜" to "🥬",
        "午餐肉" to "🥓",        "香肠" to "🌭",        "腊肠" to "🌭",
        "鸡肉" to "🐤",        "猪肉" to "🐷",        "鸡蛋" to "🥚",
        "虾" to "🦐",        "牛肉" to "🐮",        "骨头" to "🦴",
        "鱼（Todo）" to "🐟",        "面食" to "🍝",        "面包" to "🍞",
        "米" to "🍚",        "方便面" to "🍜"
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
        "肉类" to listOf("牛肉", "鸡肉", "猪肉", "鸭肉", "羊肉"),
        "蔬菜" to listOf("青菜", "白菜", "胡萝卜", "番茄", "土豆"),
        "主食" to listOf("米饭", "面条"),
        "厨具" to listOf("锅")
    )


    val Choice: MutableMap<String, MutableSet<String>> = mutableMapOf(
        "肉类" to mutableSetOf(),
        "蔬菜" to mutableSetOf(),
        "主食" to mutableSetOf(),
        "厨具" to mutableSetOf()
    )

}