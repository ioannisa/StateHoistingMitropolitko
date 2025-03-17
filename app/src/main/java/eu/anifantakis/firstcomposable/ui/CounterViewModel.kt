package eu.anifantakis.firstcomposable.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel(
    someInteger: Int
) : ViewModel() {
    // Compose-friendly state holder
    private val _count = mutableIntStateOf(0)
    val count: State<Int> = _count

    fun increment() {
        _count.value++
    }

    fun decrement() {
        _count.value--
    }
}