package cn.chiichen.cook.utils

import cn.chiichen.cook.Global.stuffMap
import cn.chiichen.cook.Global.toolsMap


fun stuffToIcon(stuff:String): String {
    var stuffIcons : String = ""
    val stuffs = stuff.split("、")
    for (s in stuffs) {
        stuffIcons += stuffMap.getValue(s)
        if (stuffIcons.length > 2) {
            stuffIcons += "..."
            break
        }
    }
    return stuffIcons
}

fun toolsToIcon(tools: String):List<Int>{
    var toolsIcons : MutableList<Int> = mutableListOf()
    val toolsArr = tools.split("、")
    for (tool in toolsArr) {
        toolsIcons.add(toolsMap.getValue(tool))
    }
    return toolsIcons
}