package cl.arlanditech.dota2calculator.activities.hero

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.arlanditech.dota2calculator.model.Hero

class HeroViewModel : ViewModel() {

    private val _hero = MutableLiveData<Hero?>().apply {
        value = null
    }

    val hero: MutableLiveData<Hero?> = _hero

}