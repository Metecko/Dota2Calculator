package cl.arlanditech.dota2calculator.fragments.heroes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.arlanditech.dota2calculator.model.Hero

class HeroesViewModel : ViewModel() {

    private val _heroes = MutableLiveData<ArrayList<Hero>>().apply {
        value = ArrayList()
    }
    val heroes: MutableLiveData<ArrayList<Hero>> = _heroes
}