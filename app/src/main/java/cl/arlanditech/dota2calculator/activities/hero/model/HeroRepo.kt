package cl.arlanditech.dota2calculator.activities.hero.model

import android.util.Log
import cl.arlanditech.dota2calculator.activities.hero.HeroViewModel
import cl.arlanditech.dota2calculator.model.Hero
import cl.arlanditech.dota2calculator.model.data.Common
import cl.arlanditech.dota2calculator.model.database.daos.HeroesDAO
import cl.arlanditech.dota2calculator.app.RoomApplication
import cl.arlanditech.dota2calculator.model.database.DotaDatabase
import cl.febos.pos.model.database.entities.HeroEntity

class HeroRepo(private val viewModel: HeroViewModel) {
    val TAG = "HeroRepo"
    private val writer = RoomApplication.databaseWriteExecutor
    private val database: DotaDatabase = RoomApplication.dotaDatabase!!
    private val heroesDAO: HeroesDAO = database.getHeroesDAO()
    private lateinit var hero: Hero

    fun getHero() {
        hero =  Common.currentHero!!
        viewModel.hero.value = hero

        getIsFavorite()
    }

    private fun getIsFavorite() {
        writer.execute {
            val heroEntity = heroesDAO.getHero(hero.id)
            viewModel.hero.value?.isFavorite = heroEntity != null
        }
    }

    fun updateFavoriteHero() {
        writer.execute{
            val heroEntity: HeroEntity? = heroesDAO.getHero(hero.id)
            if (heroEntity == null) {
                val idHeroStat = heroesDAO.insertHeroStat(hero.heroStat!!.toEntity())
                hero.idHeroStat = idHeroStat
                heroesDAO.insertHero(hero.toEntity())
                viewModel.hero.postValue(hero.copy(isFavorite = true))
            } else {
                heroesDAO.deleteHero(heroEntity)
                val heroStatsEntity = heroesDAO.getHeroStats(heroEntity.idHeroStat)
                heroesDAO.deleteHeroStat(heroStatsEntity)
                viewModel.hero.postValue(hero.copy(isFavorite = false))
            }

        }
    }
}