package com.arttttt.koinsample.ui.b

import android.os.Bundle
import android.view.View
import com.arttttt.koinsample.R
import com.arttttt.koinsample.objects.ComponentB
import com.arttttt.koinsample.ui.KoinFragment
import kotlinx.android.synthetic.main.fragment_a.*
import timber.log.Timber

class FragmentB: KoinFragment(R.layout.fragment_b) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .popBackStack()
        }

        val componentB = scope?.get<ComponentB>()
        Timber.e(componentB?.toString())
    }
}