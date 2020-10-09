package cl.arlanditech.dota2calculator.fragments.heroes.model.api.response

import com.google.gson.annotations.SerializedName

data class HeroResponse (
    @SerializedName("id") val id : Long,
    @SerializedName("name") val name : String,
    @SerializedName("displayName") val displayName : String,
    @SerializedName("shortName") val shortName : String,
    @SerializedName("abilities") val abilities : List<Abilities>,
    @SerializedName("talents") val talents : List<Talents>,
    @SerializedName("stat") val heroStatResponse : HeroStatResponse,
    @SerializedName("language") val language : Language,
    @SerializedName("aliases") val aliases : List<String>
) {
    fun getAbilitiesMap(): HashMap<Int, Int> {
        val abilitiesMap: HashMap<Int, Int> = HashMap()
        for (ability in abilities) {
            abilitiesMap[ability.slot] = ability.abilityId
        }
        return abilitiesMap
    }

    fun getTalentsMap(): HashMap<Int, Int> {
        val talentsMap: HashMap<Int, Int> = HashMap()
        for (talent in talents) {
            talentsMap[talent.slot] = talent.abilityId
        }
        return talentsMap
    }
}