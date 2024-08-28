package com.samir.baims.presentation.mainNav.activity

import android.view.LayoutInflater
import com.samir.baims.R
import com.samir.baims.common.base.activity.BaseActivity
import com.samir.baims.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun inflateBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun getAnimationPopUpView(): Pair<Int, Int> {
        return Pair(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top)
    }

    override fun getContainer(): Int {
        return R.id.main_container
    }

    override fun initializeViews() {}

}