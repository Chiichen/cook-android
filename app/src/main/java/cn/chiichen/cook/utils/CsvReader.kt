package cn.chiichen.cook.utils

import android.content.res.AssetManager
import android.content.res.AssetManager.AssetInputStream
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.bean.HeaderColumnNameMappingStrategy
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


object CsvReader {

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(IOException::class)
    inline fun <reified T> readCsvFromAsset(assetReader: AssetManager,fileName: String): ArrayList<T> {
        var ret: ArrayList<T>
        assetReader.open(fileName).reader(StandardCharsets.UTF_8).use { br ->
            val strategy
                    : HeaderColumnNameMappingStrategy<T?> =
                HeaderColumnNameMappingStrategy<T?>()
            strategy.setType(T::class.java)

            val csvToBean: CsvToBean<*> = CsvToBeanBuilder<Any?>(br)
                .withType(T::class.java)
                .withMappingStrategy(strategy)
                .withIgnoreLeadingWhiteSpace(true)
                .build()

            ret = csvToBean.parse() as ArrayList<T>
            ret.forEach(System.out::println)
        }
        return ret;
    }
}

