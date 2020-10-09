package cl.arlanditech.dota2calculator.model.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cl.arlanditech.dota2calculator.model.database.entities.HeroStatEntity
import cl.febos.pos.model.database.entities.HeroEntity

@Dao
interface HeroesDAO {
    @Insert
    fun insertHero(heroEntity: HeroEntity): Long
    @Query("SELECT * FROM heroes")
    fun getAllHeroes(): List<HeroEntity>
    @Query("SELECT * FROM heroes WHERE heroes.id = :idHero")
    fun getHero(idHero: Long): HeroEntity?
    @Query("SELECT * FROM herostats WHERE herostats.id = :idHeroStat")
    fun getHeroStats(idHeroStat: Long): HeroStatEntity
    @Insert
    fun insertHeroStat(heroStatEntity: HeroStatEntity): Long
    @Delete
    fun deleteHero(heroEntity: HeroEntity)
    @Delete
    fun deleteHeroStat(heroStatEntity: HeroStatEntity)
}