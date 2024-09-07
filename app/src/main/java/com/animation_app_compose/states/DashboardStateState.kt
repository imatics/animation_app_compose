package com.animation_app_compose.states

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.animation_app_compose.MenuItem
import com.animation_app_compose.Routes
import kotlin.reflect.KProperty

class DashboardState(
    private var showTopBar: MutableState<Boolean>,
    private var showSearch: MutableState<Boolean>,
    private var showBack: MutableState<Boolean>,
    private var showTab: MutableState<Boolean>,
    private var showBottomBar: MutableState<Boolean>,
    private var showTitle: MutableState<Boolean>,
    private var title: String,
    private var selectedTabIndex: MutableState<Int>,
    private var selectedShipmentTabIndex: MutableState<Int>,
    private var currentRoute: MutableState<Routes>,

) {
    operator fun getValue(nothing: Nothing?, property: KProperty<*>): DashboardState {
        return this
    }

    val shouldShowSearch
        get() = showSearch.value

    val shouldShowTopBar
        get() = showTopBar.value

    val shouldShowBack
        get() = showBack.value


       val shouldShowTab
        get() = showTab.value


       val shouldShowBottomBar
        get() = showBottomBar.value


       val shouldShowTitle
        get() = showTitle


       val titleValue
        get() = title

        val routeValue
        get() = currentRoute.value


       val tabSelected
           get() = selectedTabIndex.value

    val shipmentSelected
        get() = selectedShipmentTabIndex.value


    fun showTitle(title: String) {
        this.title = title
        this.showTitle.value = true
    }


    fun showRoute(routes: Routes){
        if(routes == Routes.Start){
            selectTab(0)
        }
        this.currentRoute.value = routes
    }

    fun showSearch(show: Boolean) {
        if(show){
        this.showSearch.value = true
        this.showBack.value = true
        this.showTopBar.value = false
        this.showBottomBar.value = false
        }else{
            selectTab(0)
        }
    }

    fun showBack(show: Boolean) {
        this.showBack.value = show
    }

    fun showTab(show: Boolean) {
        this.showTab.value = show
    }

    fun showBottomBar(show: Boolean) {
        this.showBottomBar.value = show
    }

    fun selectTab(index: Int, menuItem: MenuItem? = null) {
        this.showSearch.value = false
        this.title = menuItem?.label?:""
        this.selectedTabIndex.value = index;
        this.showBottomBar.value = index == 0
        this.showBack.value = index != 0
        this.showTopBar.value = index == 0
        this.showTab.value = index == 2
        this.showTitle.value = index != 0
    }

    fun selectShipmentTab(index: Int) {
        this.selectedShipmentTabIndex.value = index;
    }


}

@Composable
fun rememberDashboardState(
    showTopBar:  MutableState<Boolean> = remember{ mutableStateOf(true)},
    showSearch: MutableState<Boolean> = remember{ mutableStateOf(false)},
    showTab: MutableState<Boolean> = remember{ mutableStateOf(false)},
    showBack: MutableState<Boolean> = remember{ mutableStateOf(false)},
    showBottomBar: MutableState<Boolean> = remember{ mutableStateOf(true)},
    showTitle: MutableState<Boolean> = remember{ mutableStateOf(false)},
    title: String = remember{ ""},
    selectedTabIndex: MutableState<Int> = remember{ mutableIntStateOf(0) },
    selectedShipmentTabIndex: MutableState<Int> = remember{ mutableIntStateOf(0) },
    routes: MutableState<Routes> = remember{ mutableStateOf(Routes.Start) },
) = remember() {
    DashboardState(showTopBar ,showSearch, showBack ,showTab, showBottomBar, showTitle, title, selectedTabIndex, selectedShipmentTabIndex, routes)
}