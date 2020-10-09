package cl.arlanditech.dota2calculator.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cl.febos.pos.model.database.entities.HeroEntity

@Entity(
    tableName = "abilities",
    foreignKeys = [
        ForeignKey(
            entity = HeroEntity::class,
            parentColumns = ["id"],
            childColumns = ["idHero"],
            onDelete = ForeignKey.CASCADE)]
)
data class AbilityEntity(@ColumnInfo(name = "id")
                         @PrimaryKey
                         val id: Long,
                         @ColumnInfo(name = "idHero", index = true)
                         val idHero: Long,
                         @ColumnInfo(name = "isTalent")
                         val isTalent: Boolean,
                         @ColumnInfo(name = "displayName")
                         val displayName: String,
                         @ColumnInfo(name = "description")
                         val description: String,
                         @ColumnInfo(name = "idAbilityStat")
                         val abilityStat: Long)