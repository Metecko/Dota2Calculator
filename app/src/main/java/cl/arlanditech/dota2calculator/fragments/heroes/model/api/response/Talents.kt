package cl.arlanditech.dota2calculator.fragments.heroes.model.api.response

import com.google.gson.annotations.SerializedName

data class Talents (
    @SerializedName("slot") val slot : Int,
    @SerializedName("gameVersionId") val gameVersionId : Int,
    @SerializedName("abilityId") val abilityId : Int
)