package cn.chiichen.cook

object Global {

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