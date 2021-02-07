package kr.ac.kumoh.s20210000.gunplasqliteapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GunplaViewModel(private val repository: GunplaRepository) : ViewModel() {
    val allMechanic: LiveData<List<Mechanic>> = repository.allMechanic.asLiveData()

    // coroutine 실행
    fun insert(word: Mechanic) = viewModelScope.launch {
        repository.insert(word)
    }
}