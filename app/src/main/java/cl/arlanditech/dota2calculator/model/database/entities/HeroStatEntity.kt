package cl.arlanditech.dota2calculator.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cl.arlanditech.dota2calculator.model.HeroStat

@Entity(tableName = "herostats")
data class HeroStatEntity(@ColumnInfo(name = "id")
                          @PrimaryKey(autoGenerate = true)
                          val id: Long = 0,
                          @ColumnInfo(name = "gameVersionId")
                          val gameVersionId : Int,
                          @ColumnInfo(name = "enabled")
                          val enabled : Boolean,
                          @ColumnInfo(name = "heroUnlockOrder")
                          val heroUnlockOrder : Int,
                          @ColumnInfo(name = "team")
                          val team : Boolean,
                          @ColumnInfo(name = "cmEnabled")
                          val cmEnabled : Boolean,
                          @ColumnInfo(name = "newPlayerEnabled")
                          val newPlayerEnabled : Boolean,
                          @ColumnInfo(name = "attackType")
                          val attackType : String,
                          @ColumnInfo(name = "startingArmor")
                          val startingArmor : Double,
                          @ColumnInfo(name = "startingMagicArmor")
                          val startingMagicArmor : Int,
                          @ColumnInfo(name = "startingDamageMin")
                          val startingDamageMin : Int,
                          @ColumnInfo(name = "startingDamageMax")
                          val startingDamageMax : Int,
                          @ColumnInfo(name = "attackRate")
                          val attackRate : Double,
                          @ColumnInfo(name = "attackAnimationPoint")
                          val attackAnimationPoint : Double,
                          @ColumnInfo(name = "attackAcquisitionRange")
                          val attackAcquisitionRange : Int,
                          @ColumnInfo(name = "attackRange")
                          val attackRange : Int,
                          @ColumnInfo(name = "primaryAttribute")
                          val primaryAttribute : String,
                          @ColumnInfo(name = "heroPrimaryAttribute")
                          val heroPrimaryAttribute : Int,
                          @ColumnInfo(name = "strengthBase")
                          val strengthBase : Int,
                          @ColumnInfo(name = "strengthGain")
                          val strengthGain : Double,
                          @ColumnInfo(name = "intelligenceBase")
                          val intelligenceBase : Int,
                          @ColumnInfo(name = "intelligenceGain")
                          val intelligenceGain : Double,
                          @ColumnInfo(name = "agilityBase")
                          val agilityBase : Int,
                          @ColumnInfo(name = "agilityGain")
                          val agilityGain : Double,
                          @ColumnInfo(name = "hpRegen")
                          val hpRegen : Int,
                          @ColumnInfo(name = "mpRegen")
                          val mpRegen : Int,
                          @ColumnInfo(name = "moveSpeed")
                          val moveSpeed : Int,
                          @ColumnInfo(name = "moveTurnRate")
                          val moveTurnRate : Double,
                          @ColumnInfo(name = "hpBarOffset")
                          val hpBarOffset : Int,
                          @ColumnInfo(name = "visionDaytimeRange")
                          val visionDaytimeRange : Int,
                          @ColumnInfo(name = "visionNighttimeRange")
                          val visionNighttimeRange : Int ) {
    fun toHeroStat(): HeroStat {
        val heroStat = HeroStat(
            gameVersionId = gameVersionId,
            enabled = enabled,
            heroUnlockOrder = heroUnlockOrder,
            team = team,
            cmEnabled = cmEnabled,
            newPlayerEnabled = newPlayerEnabled,
            attackType = attackType,
            startingArmor = startingArmor,
            startingMagicArmor = startingMagicArmor,
            startingDamageMin = startingDamageMin,
            startingDamageMax = startingDamageMax,
            attackRate = attackRate,
            attackAnimationPoint = attackAnimationPoint,
            attackAcquisitionRange = attackAcquisitionRange,
            attackRange = attackRange,
            primaryAttribute = primaryAttribute,
            heroPrimaryAttribute = heroPrimaryAttribute,
            strengthGain = strengthGain,
            strengthBase = strengthBase,
            agilityGain = agilityGain,
            agilityBase = agilityBase,
            intelligenceGain = intelligenceGain,
            intelligenceBase = intelligenceBase,
            hpRegen = hpRegen,
            mpRegen = mpRegen,
            moveSpeed = moveSpeed,
            moveTurnRate = moveTurnRate,
            hpBarOffset = hpBarOffset,
            visionNighttimeRange = visionNighttimeRange,
            visionDaytimeRange = visionDaytimeRange
        )

        return heroStat
    }
}