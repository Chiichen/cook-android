package cn.chiichen.cook.model.entity


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.opencsv.bean.CsvBindByName


@Entity(indices = [Index(value = ["tools", "stuff"], unique = false)])
data class Recipe(
    @CsvBindByName @PrimaryKey val name: String,
    @CsvBindByName val stuff: String,
    @CsvBindByName val bv: String,
    @CsvBindByName val difficulty: String,
    @CsvBindByName val tags: String,
    @CsvBindByName val methods: String,
    @CsvBindByName val tools: String
) {
    constructor() : this("", "", "", "", "", "", "")
};
