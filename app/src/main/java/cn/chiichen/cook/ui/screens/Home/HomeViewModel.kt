package cn.chiichen.cook.ui.screens.Home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.cook.model.entity.Recipe
import cn.chiichen.cook.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: RecipeRepository) : ViewModel() {

    /**
     * 非侧边栏
     */

    // 0 => 模糊匹配, 1 => 严格匹配
    var mode: MutableState<Int> = mutableIntStateOf(0)

    var recipes: MutableState<List<Recipe>> = mutableStateOf(emptyList())

    var showDialog: MutableState<Boolean> = mutableStateOf(false)

    // 首页点击食谱跳转的时间(确认跳转时存入记录)
    var clickRecipeTime: MutableState<String> = mutableStateOf("")

    // 首页点击的食谱(确认跳转时存入记录)
    var clickRecipeItem: MutableState<Recipe> = mutableStateOf(Recipe("", "", "", "", "", "", ""))

    // 存入点击跳转记录，用于历史记录
    fun putIntoStorage() {
        viewModelScope.launch(Dispatchers.IO) {
            //TODO (clickTime.value + clickItem.value) => repository
        }
    }

    /**
     * 侧边栏
     */

    // 选择的食材和厨具
    var choices: MutableState<Map<String, MutableSet<String>>> = mutableStateOf(
        mapOf(
            "肉类" to mutableSetOf(),
            "蔬菜" to mutableSetOf(),
            "主食" to mutableSetOf(),
            "厨具" to mutableSetOf()
        )
    )

    fun getMatchRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("HomeViewModel", "Getting Match Recipes")
            val stuffList: ArrayList<String> = arrayListOf("");
            val toolsList: ArrayList<String> = arrayListOf("");
            choices.value["肉类"]?.let { its ->
                for (it in its) {
                    stuffList.add(it)
                }
            }
            choices.value["蔬菜"]?.let { its ->
                for (it in its) {
                    stuffList.add(it)
                }
            }
            choices.value["主食"]?.let { its ->
                for (it in its) {
                    stuffList.add(it)
                }
            }
            choices.value["厨具"]?.let { its ->
                for (it in its) {
                    toolsList.add(it)
                }
            }
            if (toolsList.isEmpty() && stuffList.isEmpty()) {
                return@launch;
            }
            if (mode.value == 0) { //模糊匹配
                recipes.value = repository.getRecipe(stuffList, toolsList);
            } else if (mode.value == 1) { //严格匹配
                recipes.value = repository.getRecipeStrictly(stuffList, toolsList);
            } else {
                Log.e("HomeViewModel", "Invalid mode value")
            }
        }
    }
}