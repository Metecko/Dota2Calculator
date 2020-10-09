package cl.febos.pos.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cl.arlanditech.dota2calculator.model.Hero
import cl.arlanditech.dota2calculator.model.HeroStat
import cl.arlanditech.dota2calculator.model.database.entities.HeroStatEntity
import org.jetbrains.annotations.NotNull

@Entity(tableName = "heroes",
    foreignKeys = [
        ForeignKey(
            entity = HeroStatEntity::class,
            parentColumns = ["id"],
            childColumns = ["idHeroStat"],
            onDelete = ForeignKey.CASCADE)]
)
data class HeroEntity(@ColumnInfo(name = "id")
                      @PrimaryKey
                      val id: Long,
                      @ColumnInfo(name = "displayName")
                      @NotNull
                      val displayName: String,
                      @ColumnInfo(name = "shortName")
                      @NotNull
                      val shortName: String,
                      @ColumnInfo(name = "idHeroStat", index = true)
                      @NotNull
                      val idHeroStat: Long)
{
    fun toHero(): Hero {
        val hero = Hero(
            id = id,
            displayName = displayName,
            shortname = shortName,
            idHeroStat = idHeroStat
        )
        return hero
    }
}