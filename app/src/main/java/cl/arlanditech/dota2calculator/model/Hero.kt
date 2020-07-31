package cl.arlanditech.dota2calculator.model

import android.content.Context
import cl.arlanditech.dota2calculator.fragments.heroes.model.api.response.HeroResponse
import java.util.*
import kotlin.collections.HashMap

data class Hero(
    val id: Int,
    val displayName: String,
    val iconId: Int,
    val heroStat: HeroStat,
    val abilitiesId: HashMap<Int, Int>,
    val talentsId: HashMap<Int, Int>
) {

    companion object {
        fun getHeroFromHeroResponse(heroResponse: HeroResponse, context: Context): Hero {
            val pngHeroName: String = nameToPNGName(heroResponse.displayName)
            val iconId: Int =
                context.resources.getIdentifier(pngHeroName, "drawable", context.packageName)

            val hero = Hero(
                id = heroResponse.id,
                displayName = heroResponse.displayName,
                iconId = iconId,
                heroStat = heroResponse.heroStatResponse.toHeroStat(),
                abilitiesId = heroResponse.getAbilitiesMap(),
                talentsId = heroResponse.getTalentsMap())
            return hero
        }

        private fun nameToPNGName(displayName: String) : String {
            val heroNamePNG = displayName.toLowerCase(Locale.ROOT).
            replace(" ", "_").
            replace("-", "_").
            replace("'", "")

            return heroNamePNG
        }
    }

}