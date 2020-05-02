package com.arttttt.koinsample.ui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.arttttt.koinsample.utils.castTo
import org.koin.android.ext.android.getKoin
import org.koin.core.scope.Scope
import org.koin.ext.getScopeId
import org.koin.ext.getScopeName

abstract class KoinFragment(layoutRes: Int): Fragment(layoutRes) {

    companion object {
        private const val SCOPE_ID = "scope_name"
    }

    private var instanceStateSaved: Boolean = false

    internal lateinit var scope: Scope

    protected open var scopeId: String = getScopeId()

    fun getParentScope(): Scope? {
        return parentFragment
            ?.childFragmentManager
            ?.fragments
            ?.lastOrNull()
            ?.castTo<KoinFragment>()
            ?.scope
            ?: parentFragment
                .castTo<KoinFragment>()
                ?.scope
            ?: activity
                ?.supportFragmentManager
                ?.fragments
                ?.lastOrNull()
                ?.castTo<KoinFragment>()
                ?.scope
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        scopeId = savedInstanceState?.getString(SCOPE_ID) ?: scopeId
        scope = getKoin().getOrCreateScope(scopeId, getScopeName())
        getParentScope()?.let { parentScope -> scope.linkTo(parentScope)}

        super.onCreate(savedInstanceState)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SCOPE_ID, scopeId)
        instanceStateSaved = true
    }

    @CallSuper
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        instanceStateSaved = false
    }

    private fun needCloseScope(): Boolean {
        return when {
            activity?.isChangingConfigurations == true -> false
            activity?.isFinishing == true -> true
            else -> isRealRemoving()
        }
    }

    internal fun isRealRemoving(): Boolean {
        return (isRemoving && !instanceStateSaved) || (parentFragment.castTo<KoinFragment>()?.isRealRemoving() ?: false)
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()

        if (needCloseScope()) {
            scope.close()
        }
    }
}