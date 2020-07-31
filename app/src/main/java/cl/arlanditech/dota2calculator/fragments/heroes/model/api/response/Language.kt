package cl.arlanditech.dota2calculator.fragments.heroes.model.api.response

import com.google.gson.annotations.SerializedName

data class Language (
    @SerializedName("heroId") val heroId : Int,
    @SerializedName("gameVersionId") val gameVersionId : Int,
    @SerializedName("languageId") val languageId : Int,
    @SerializedName("displayName") val displayName : String,
    @SerializedName("bio") val bio : String,
    @SerializedName("hype") val hype : String
)