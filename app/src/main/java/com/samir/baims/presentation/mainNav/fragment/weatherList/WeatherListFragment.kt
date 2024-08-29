package com.samir.baims.presentation.mainNav.fragment.weatherList

import android.util.Log
import androidx.fragment.app.viewModels
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samir.baims.R
import com.samir.baims.common.base.fragment.BaseFragment
import com.samir.baims.common.stateHandling.uI.LiveDataResource
import com.samir.baims.common.utils.widget.CustomAlertDialog
import com.samir.baims.databinding.FragmentWeatherListBinding
import com.samir.baims.domain.model.remote.main.Cities
import com.samir.baims.domain.model.remote.weatherList.WeatherList
import com.samir.baims.presentation.mainNav.fragment.weatherList.adapter.WeatherAdapter
import com.samir.baims.presentation.mainNav.fragment.weatherList.clickEvent.WeatherClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherListFragment : BaseFragment<WeatherListViewModel, FragmentWeatherListBinding>(), WeatherClickListener {

    private var builderAlert: AlertDialog? = null

    //region initialization
    override fun getContentView(): Int {
        return R.layout.fragment_weather_list
    }
    override fun getAllViews(): List<View> {
        return listOf<View>(  )
    }
    override fun getAllViewsForVisibility(): List<View> {
        return listOf<View>( baseViewBinding.tvRefresh )
    }
    override fun initializeViewModel() {
        val viewModel: WeatherListViewModel by viewModels()
        baseViewModel = viewModel
    }
    override fun subscribeLiveData() {
        getCitiesObserver()
    }
    override fun initializeViews() {


        //region SwipeRefreshLayout
        baseViewBinding.swipeRefreshLayout.setColorSchemeResources(
            R.color.black,
            R.color.gray_light_opacity_20,
            R.color.gray
        )
        baseViewBinding.swipeRefreshLayout.setOnRefreshListener {
            closeRefreshing()

        }
        //endregion








    }
    private fun closeRefreshing(){
        baseViewBinding.swipeRefreshLayout.isRefreshing = false
    }
    //endregion
    override fun onClick(v: View?) {}

    //region Observers
    private fun getCitiesObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                baseViewModel?.getCitiesStateFlow?.collect { result ->
                    when (result) {
                        is LiveDataResource.Success -> {
                            enableAllViews()
                            goneAllViews()
                            builderAlert?.dismiss()
                            result.data?.let {initWeatherAdapterRecycler(result.data.weatherItem, baseViewBinding.recyclerView)
                            }
                            Log.e("AppDone", "Fragment  Success: " )
                        }
                        is LiveDataResource.ErrorResponse -> {
                            disableAllViews()
                            visibleAllViews()
                            CustomAlertDialog.showDialogErrorWithActionButton(requireContext(), result.message.toString()){ }
                            builderAlert?.dismiss()
                            Log.e("AppDone", "Fragment  ErrorResponse: " )
                        }
                        is LiveDataResource.Error -> {
                            disableAllViews()
                            visibleAllViews()
                            builderAlert?.dismiss()
                            CustomAlertDialog.showDialogErrorWithActionButton(requireContext(), result.message.toString()){ }
                            Log.e("AppDone", "Fragment  Error: " )
                        }
                        is LiveDataResource.Loading -> {
                            initWeatherAdapterRecycler(emptyList(), baseViewBinding.recyclerView)
                            disableAllViews()
                            goneAllViews()
                            Log.e("AppDone", "Fragment  Loading: " )
                            builderAlert = CustomAlertDialog.initAndShowAlertDialog(messageAlert= getString(R.string.app_Loading), ctx= requireContext())

                        }
                    }
                }
            }
        }
    }
    //endregion

    //region recycler View
    private fun initWeatherAdapterRecycler(myList: List<WeatherList.WeatherSingleItem>, recyclerView: RecyclerView){
        if(myList.isNotEmpty()){
            recyclerView.layoutManager = GridLayoutManager(context, 1)
            val todayVisitAdapter = WeatherAdapter().apply {
                submitMyList(myList, this@WeatherListFragment)
            }
            baseViewBinding.recyclerView.visibility = View.VISIBLE
            recyclerView.adapter = todayVisitAdapter
            recyclerView.startLayoutAnimation()
        }else{
            baseViewBinding.recyclerView.visibility = View.GONE

        }
    }
    override fun onItemClicked(itemSelected: WeatherList.WeatherSingleItem) {

    }
    //endregion

    //region life cycle of the application
    override fun start() {}
    override fun stopAndReset() {}
    //endregion

}