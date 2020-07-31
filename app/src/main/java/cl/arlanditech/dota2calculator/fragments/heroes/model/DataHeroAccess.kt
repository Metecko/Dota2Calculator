package cl.arlanditech.dota2calculator.fragments.heroes.model

import android.content.Context
import cl.arlanditech.dota2calculator.APIClient
import cl.arlanditech.dota2calculator.fragments.heroes.model.api.APIHeroes
import cl.arlanditech.dota2calculator.fragments.heroes.model.api.response.BaseResponse
import cl.arlanditech.dota2calculator.fragments.heroes.model.api.response.HeroResponse
import cl.arlanditech.dota2calculator.fragments.heroes.model.observer.HeroesObserver
import cl.arlanditech.dota2calculator.model.Hero
import cl.arlanditech.dota2calculator.model.Observer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KFunction2
import kotlin.reflect.full.memberProperties

class DataHeroAccess(private val context: Context) {
    val TAG = "DataHeroAccess"
    private val observers = mutableListOf<Observer>()
    fun register(observer: Observer) = observers.add(observer)
    fun unregister(observer: Observer) = observers.remove(observer)

    fun getHeroes() {
        val apiclient = APIClient.client.create(APIHeroes::class.java)
        val call = apiclient.getHeroes()

        call.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<BaseResponse>?, response: Response<BaseResponse>?) {
                if (response?.isSuccessful!!) {
                    val heroes: ArrayList<Hero> = ArrayList()
                    for (prop in BaseResponse::class.memberProperties) {
                        val heroResponse: HeroResponse  = prop.get(response.body()!!) as HeroResponse
                        val hero = Hero.getHeroFromHeroResponse(heroResponse, context)
                        heroes.add(hero)
                    }

                    notifyHeroes(HeroesObserver::addHeroes, heroes)
                } else {

                }
            }
        })
    }

    private fun notifyHeroes(action: KFunction2<HeroesObserver, ArrayList<Hero>, Unit>, valor : ArrayList<Hero>) {
        observers.filterIsInstance<HeroesObserver>().onEach { action(it, valor) }
    }
}