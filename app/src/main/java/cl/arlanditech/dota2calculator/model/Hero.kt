package cl.arlanditech.dota2calculator.model

import android.content.Context
import cl.arlanditech.dota2calculator.fragments.heroes.model.api.response.HeroResponse
import cl.febos.pos.model.database.entities.HeroEntity
import java.util.*
import kotlin.collections.HashMap

data class Hero(
    val id: Long,
    val displayName: String,
    val shortname: String,
    var idHeroStat: Long? = null,
    var heroStat: HeroStat? = null,
    val abilitiesId: HashMap<Int, Int>? = null,
    val talentsId: HashMap<Int, Int>? = null,
    var isFavorite: Boolean = false
) {

    fun getURLPng() : String {
        val url = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/heroes/${shortname}_full.png"

        return url
    }

    fun toEntity(): HeroEntity {
        val entity = HeroEntity(
            id = id,
            displayName = displayName,
            shortName = shortname,
            idHeroStat = idHeroStat!!
        )
        return entity
    }

    companion object {
        fun getHeroFrom(heroResponse: HeroResponse): Hero {
            val hero = Hero(
                id = heroResponse.id,
                displayName = heroResponse.displayName,
                shortname = heroResponse.shortName,
                heroStat = heroResponse.heroStatResponse.toHeroStat(),
                abilitiesId = heroResponse.getAbilitiesMap(),
                talentsId = heroResponse.getTalentsMap())
            return hero
        }
    }

}