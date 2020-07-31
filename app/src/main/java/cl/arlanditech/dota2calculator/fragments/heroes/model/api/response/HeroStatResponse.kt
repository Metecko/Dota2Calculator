package cl.arlanditech.dota2calculator.fragments.heroes.model.api.response

import cl.arlanditech.dota2calculator.model.HeroStat
import com.google.gson.annotations.SerializedName

data class HeroStatResponse (
    @SerializedName("gameVersionId") val gameVersionId : Int,
    @SerializedName("enabled") val enabled : Boolean,
    @SerializedName("heroUnlockOrder") val heroUnlockOrder : Int,
    @SerializedName("team") val team : Boolean,
    @SerializedName("cmEnabled") val cmEnabled : Boolean,
    @SerializedName("newPlayerEnabled") val newPlayerEnabled : Boolean,
    @SerializedName("attackType") val attackType : String,
    @SerializedName("startingArmor") val startingArmor : Double,
    @SerializedName("startingMagicArmor") val startingMagicArmor : Int,
    @SerializedName("startingDamageMin") val startingDamageMin : Int,
    @SerializedName("startingDamageMax") val startingDamageMax : Int,
    @SerializedName("attackRate") val attackRate : Double,
    @SerializedName("attackAnimationPoint") val attackAnimationPoint : Double,
    @SerializedName("attackAcquisitionRange") val attackAcquisitionRange : Int,
    @SerializedName("attackRange") val attackRange : Int,
    @SerializedName("primaryAttribute") val primaryAttribute : String,
    @SerializedName("heroPrimaryAttribute") val heroPrimaryAttribute : Int,
    @SerializedName("strengthBase") val strengthBase : Int,
    @SerializedName("strengthGain") val strengthGain : Double,
    @SerializedName("intelligenceBase") val intelligenceBase : Int,
    @SerializedName("intelligenceGain") val intelligenceGain : Double,
    @SerializedName("agilityBase") val agilityBase : Int,
    @SerializedName("agilityGain") val agilityGain : Double,
    @SerializedName("hpRegen") val hpRegen : Double,
    @SerializedName("mpRegen") val mpRegen : Double,
    @SerializedName("moveSpeed") val moveSpeed : Int,
    @SerializedName("moveTurnRate") val moveTurnRate : Double,
    @SerializedName("hpBarOffset") val hpBarOffset : Int,
    @SerializedName("visionDaytimeRange") val visionDaytimeRange : Int,
    @SerializedName("visionNighttimeRange") val visionNighttimeRange : Int,
    @SerializedName("complexity") val complexity : Int
) {
    fun toHeroStat(): HeroStat {
        val heroStat = HeroStat(
            gameVersionId = gameVersionId,
            enabled = enabled,
            heroUnlockOrder = heroUnlockOrder,
            team = team,
            agilityBase = agilityBase,
            agilityGain = agilityGain,
            attackAcquisitionRange = attackAcquisitionRange,
            attackAnimationPoint = attackAnimationPoint,
            attackRange = attackRange,
            attackRate = attackRate,
            attackType = attackType,
            cmEnabled = cmEnabled,
            heroPrimaryAttribute = heroPrimaryAttribute,
            hpBarOffset = hpBarOffset,
            hpRegen = hpBarOffset,
            intelligenceBase = intelligenceBase,
            intelligenceGain = intelligenceGain,
            moveSpeed = moveSpeed,
            moveTurnRate = moveTurnRate,
            mpRegen = moveSpeed,
            newPlayerEnabled = newPlayerEnabled,
            primaryAttribute = primaryAttribute,
            startingArmor = startingArmor,
            startingDamageMax = startingDamageMax,
            startingDamageMin = startingDamageMin,
            startingMagicArmor = startingMagicArmor,
            strengthBase = strengthBase,
            strengthGain = strengthGain,
            visionDaytimeRange = visionDaytimeRange,
            visionNighttimeRange = visionNighttimeRange
        )

        return heroStat
    }
}