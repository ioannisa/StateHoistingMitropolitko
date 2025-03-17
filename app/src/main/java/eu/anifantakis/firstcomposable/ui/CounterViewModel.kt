package eu.anifantakis.firstcomposable.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel() : ViewModel() {
    // Compose-friendly state holder
    private val _count = mutableIntStateOf(0)
    val count: State<Int> = _count

    fun increment() {
        if (count.value < 10) {
            _count.value++
        }
    }

    fun decrement() {
        if (count.value > 0) {
            _count.value--
        }
    }
}