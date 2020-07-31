package cl.arlanditech.dota2calculator.model

data class Ability(val id: Int,
                   val displayName: String,
                   val description: String,
                   val attributes: List<String>,
                   val abilityStat: AbilityStat) {
}