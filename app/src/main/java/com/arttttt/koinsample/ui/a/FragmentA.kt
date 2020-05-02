package com.arttttt.koinsample.ui.a

import android.os.Bundle
import android.view.View
import com.arttttt.koinsample.R
import com.arttttt.koinsample.objects.ComponentB
import com.arttttt.koinsample.ui.KoinFragment
import com.arttttt.koinsample.ui.b.FragmentB
import kotlinx.android.synthetic.main.fragment_a.*
import org.koin.android.ext.android.getKoin
import timber.log.Timber

class FragmentA: KoinFragment(R.layout.fragment_a) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, FragmentB())
                .addToBackStack(null)
                .commit()
        }

        val componentB = scope?.get<ComponentB>()
        Timber.e("scoped = ${componentB?.toString()}")
    }
}