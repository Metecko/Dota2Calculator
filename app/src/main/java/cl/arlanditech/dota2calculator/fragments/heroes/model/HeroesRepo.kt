package cl.arlanditech.dota2calculator.fragments.heroes.model

import cl.arlanditech.dota2calculator.APIClient
import cl.arlanditech.dota2calculator.app.RoomApplication
import cl.arlanditech.dota2calculator.fragments.heroes.HeroesViewModel
import cl.arlanditech.dota2calculator.fragments.heroes.model.api.APIHeroes
import cl.arlanditech.dota2calculator.fragments.heroes.model.api.response.BaseResponse
import cl.arlanditech.dota2calculator.fragments.heroes.model.api.response.HeroResponse
import cl.arlanditech.dota2calculator.model.Hero
import cl.arlanditech.dota2calculator.model.Observer
import cl.arlanditech.dota2calculator.model.database.DotaDatabase
import cl.arlanditech.dota2calculator.model.database.daos.HeroesDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.full.memberProperties

class HeroesRepo(private val viewModel: HeroesViewModel) {
    val TAG = "DataHeroAccess"
    private val observers = mutableListOf<Observer>()
    private val writer = RoomApplication.databaseWriteExecutor
    private val database: DotaDatabase = RoomApplication.dotaDatabase!!
    private val heroesDAO: HeroesDAO = database.getHeroesDAO()
    fun register(observer: Observer) = observers.add(observer)
    fun unregister(observer: Observer) = observers.remove(observer)

    fun getHeroes() {
        val apiclient = APIClient.client.create(APIHeroes::class.java)
        val call = apiclient.getHeroes()

        call.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>?, t: Throwable?) {
                getLocalHeroes()
            }

            override fun onResponse(call: Call<BaseResponse>?, response: Response<BaseResponse>?) {
                if (response?.isSuccessful!!) {
                    val heroes: ArrayList<Hero> = ArrayList()
                    for (prop in BaseResponse::class.memberProperties) {
                        val heroResponse: HeroResponse  = prop.get(response.body()!!) as HeroResponse
                        val hero = Hero.getHeroFrom(heroResponse)
                        heroes.add(hero)
                    }
                    heroes.sortWith(compareBy {it.displayName})
                    viewModel.heroes.value = heroes
                } else {
                    getLocalHeroes()
                }
            }
        })
    }

    private fun getLocalHeroes() {
        writer.execute {
            val heroes = ArrayList<Hero>()
            val heroesEntities = heroesDAO.getAllHeroes()
            for (entity in heroesEntities) {
                val hero = entity.toHero()
                val heroStatEntity = heroesDAO.getHeroStats(hero.idHeroStat!!)
                hero.heroStat = heroStatEntity.toHeroStat()
                heroes.add(hero)
            }
            heroes.sortWith(compareBy {it.displayName})
            viewModel.heroes.postValue(heroes)
        }
    }
}