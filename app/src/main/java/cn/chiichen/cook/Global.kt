package cn.chiichen.cook


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
        "一口大锅" to R.drawable.ic_pot,
    )
    val Modes: List<String> = listOf("一般模式", "严格模式")

    val EntryTypes: List<String> = listOf("肉类", "蔬菜", "主食", "厨具")

    val Data: Map<String, List<String>> = mapOf(
        "肉类" to listOf("午餐肉", "香肠", "腊肠", "鸡肉", "猪肉", "鸡蛋", "虾", "牛肉", "骨头", "鱼（Todo）"),
        "蔬菜" to listOf("土豆", "胡萝卜", "花菜", "白萝卜", "西葫芦", "番茄", "芹菜", "黄瓜", "洋葱", "莴笋", "菌菇", "茄子", "豆腐", "包菜", "白菜"),
        "主食" to listOf("面食", "面包", "米", "方便面"),
        "厨具" to listOf("一口大锅", "烤箱", "微波炉", "空气炸锅", "电饭煲")
    )

}