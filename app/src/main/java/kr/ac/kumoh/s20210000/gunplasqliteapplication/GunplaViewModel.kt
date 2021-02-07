package kr.ac.kumoh.s20210000.gunplasqliteapplication
// 본인의 package 사용할 것

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class GunplaViewModel(application: Application): AndroidViewModel(application) {
    private val mechanic: LiveData<List<Mechanic>>
    private val repository: GunplaRepository

    init {
        val dao = GunplaDatabase.getDatabase(application).gunplaDao()
        repository = GunplaRepository(dao)
        mechanic = repository.allMechanic.asLiveData()
    }

    fun getAllMechanic() : LiveData<List<Mechanic>> {
        return mechanic
    }

    fun getMechanic(i: Int) = mechanic.value?.get(i)
    fun getSize() = mechanic.value?.size ?: 0

    // coroutine 실행
    fun insert(mechanic: Mechanic) = viewModelScope.launch {
        repository.insert(mechanic)
    }
}