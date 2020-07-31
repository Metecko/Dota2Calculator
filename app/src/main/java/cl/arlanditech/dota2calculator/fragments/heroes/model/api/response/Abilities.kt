package cl.arlanditech.dota2calculator.fragments.heroes.model.api.response

import com.google.gson.annotations.SerializedName

data class Abilities (
    @SerializedName("slot") val slot : Int,
    @SerializedName("abilityId") val abilityId : Int
)