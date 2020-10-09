package cl.arlanditech.dota2calculator.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.arlanditech.dota2calculator.model.database.daos.HeroesDAO
import cl.arlanditech.dota2calculator.model.database.entities.AbilityEntity
import cl.arlanditech.dota2calculator.model.database.entities.HeroStatEntity
import cl.febos.pos.model.database.entities.*

@Database(entities = [
    HeroEntity::class,
    HeroStatEntity::class,
    AbilityEntity::class
                ],
          version = 1)
abstract class DotaDatabase: RoomDatabase() {
    abstract fun getHeroesDAO(): HeroesDAO
}