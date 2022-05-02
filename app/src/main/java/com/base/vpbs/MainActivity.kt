package com.base.vpbs


import android.os.Bundle
import com.base.vpbs.base.BaseActivity
import com.base.vpbs.base.EmptyViewModel
import com.base.vpbs.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, EmptyViewModel>() {
    override fun onBundle(bundle: Bundle) {

    }

    override fun init(savedInstanceState: Bundle?) {

        binding?.btnMenu?.setOnClickListener {
            ContentSheetFragment().show(supportFragmentManager, "content")
        }




    }
}