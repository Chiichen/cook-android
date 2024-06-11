package cn.chiichen.cook.model.entity


import com.opencsv.bean.CsvBindByName



data class Recipe(@CsvBindByName val name:String, @CsvBindByName val stuff:String, @CsvBindByName val bv:String, @CsvBindByName val difficulty:String, @CsvBindByName val tags:String, @CsvBindByName val methods:String, @CsvBindByName val tools:String);
