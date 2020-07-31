package cl.arlanditech.dota2calculator.fragments.heroes.model.observer

import cl.arlanditech.dota2calculator.model.Hero
import cl.arlanditech.dota2calculator.model.Observer

interface HeroesObserver: Observer {

    fun addHeroes(heroes: ArrayList<Hero>)
}