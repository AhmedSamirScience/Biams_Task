package com.samir.baims.presentation.mainNav.fragment.main

import androidx.fragment.app.viewModels
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samir.baims.R
import com.samir.baims.common.base.fragment.BaseFragment
import com.samir.baims.common.constants.Constants
import com.samir.baims.common.stateHandling.uI.LiveDataResource
import com.samir.baims.common.utils.widget.CustomAlertDialog
import com.samir.baims.databinding.FragmentMainBinding
import com.samir.baims.domain.model.remote.main.Cities
import com.samir.baims.presentation.mainNav.fragment.main.adapter.CitesAdapter
import com.samir.baims.presentation.mainNav.fragment.main.clickEvent.CitiesClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(), CitiesClickListener {

    private var builderAlert: AlertDialog? = null

    //region initialization
    override fun getContentView(): Int {
        return R.layout.fragment_main
    }
    override fun getAllViews(): List<View> {
        return listOf<View>( baseViewBinding.edtSearch )
    }
    override fun getAllViewsForVisibility(): List<View> {
        return listOf<View>( baseViewBinding.tvRefresh )
    }
    override fun initializeViewModel() {
        val viewModel: MainViewModel by viewModels()
        baseViewModel = viewModel
    }
    override fun subscribeLiveData() {
        getCitiesObserver()
    }
    override fun initializeViews() {
         //region Set up text watcher for filtering
        baseViewBinding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //i want to close the keyboard after the user finish typing

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                (baseViewBinding.recyclerView.adapter as? CitesAdapter)?.filter(s.toString())
            }
        })
        //endregion

        //region SwipeRefreshLayout
        baseViewBinding.swipeRefreshLayout.setColorSchemeResources(
            R.color.black,
            R.color.gray_light_opacity_20,
            R.color.gray
        )
        baseViewBinding.swipeRefreshLayout.setOnRefreshListener {
            closeRefreshing()
            baseViewModel?.fetchCities()
        }
        //endregion

        baseViewModel?.fetchCities()

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
                            result.data?.let { initCitiesAdapterRecycler(it.cities, baseViewBinding.recyclerView) }
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
                            initCitiesAdapterRecycler(emptyList(), baseViewBinding.recyclerView)
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
    private fun initCitiesAdapterRecycler(myList: List<Cities.City>, recyclerView: RecyclerView){
        if(myList.isNotEmpty()){
            recyclerView.layoutManager = GridLayoutManager(context, 1)
            val todayVisitAdapter = CitesAdapter().apply {
                submitMyList(myList, this@MainFragment)
            }
            baseViewBinding.recyclerView.visibility = View.VISIBLE
            recyclerView.adapter = todayVisitAdapter
            recyclerView.startLayoutAnimation()
        }else{
            baseViewBinding.recyclerView.visibility = View.GONE

        }
    }
    override fun onItemClicked(itemSelected: Cities.City) {
        findNavController().navigate(R.id.action_mainFragment_to_weatherListFragment)
    }
    //endregion

    //region life cycle of the application
    override fun start() {}
    override fun stopAndReset() {}
    //endregion

}