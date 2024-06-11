package cn.chiichen.cook.ui.screens.List

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.cook.model.entity.Recipe
import cn.chiichen.cook.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val repository: RecipeRepository) : ViewModel() {
    var count: MutableState<Int> = mutableIntStateOf(1)

    // MutableState to hold the current list of images to display
    var recipes: MutableState<List<Recipe>> = mutableStateOf(emptyList())


    fun getRandomRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRandomRecipe(count.value).let { list ->
                recipes.value = list
            }
        }
    }
}