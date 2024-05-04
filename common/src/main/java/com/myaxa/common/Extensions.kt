package com.myaxa.common

import android.content.res.Resources
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun <T> unsafeLazy (initializer: () -> T) : Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)

fun <T : Any?> Flow<T>.collectOnLifecycle(lifecycleOwner: LifecycleOwner, action: suspend (T) -> Unit) {
    lifecycleOwner.lifecycleScope.launch {
        collectLatest(action)
    }
}

@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow<CharSequence?> {

        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }

        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}
