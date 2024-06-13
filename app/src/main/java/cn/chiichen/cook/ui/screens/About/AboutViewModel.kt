package cn.chiichen.cook.ui.screens.About

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.cook.model.entity.Recipe
import cn.chiichen.cook.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.TreeMap

class AboutViewModel(private val repository: RecipeRepository) : ViewModel() {

    var records: MutableState<Map<String, MutableList<Recipe>>>
        = mutableStateOf(TreeMap<String, MutableList<Recipe>>(reverseOrder()))

    fun getRecords() {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO
        }
    }

}