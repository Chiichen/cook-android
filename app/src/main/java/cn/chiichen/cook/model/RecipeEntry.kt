package cn.chiichen.cook.model

data class RecipeEntry(
    val name:String,//名字
    val stuff:String,//食材
    val bv:String,//bv号
    val difficulty:String,//难易度
    val tags:String,//标签
    val methods:String,//烹饪方式
    val tools:String//厨具
);
