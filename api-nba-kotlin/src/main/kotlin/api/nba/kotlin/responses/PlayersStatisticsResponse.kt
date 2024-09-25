package api.nba.kotlin.responses

import api.nba.kotlin.models.Team

/**
 * Represents the response object for player statistics.
 *
 * @param player The player object containing player information.
 * @param team The team object containing team information.
 * @param game The game object containing game information.
 * @param points The total number of points scored by the player.
 * @param pos The position of the player.
 * @param min The number of minutes played by the player.
 * @param fgm The total number of field goals made by the player.
 * @param fga The total number of field goals attempted by the player.
 * @param fgp The field goal percentage of the player.
 * @param ftm The total number of free throws made by the player.
 * @param fta The total number of free throws attempted by the player.
 * @param ftp The free throw percentage of the player.
 * @param tpm The total number of three-point field goals made by the player.
 * @param tpa The total number of three-point field goals attempted by the player.
 * @param tpp The three-point field goal percentage of the player.
 * @param offReb The number of offensive rebounds by the player.
 * @param defReb The number of defensive rebounds by the player.
 * @param totReb The total number of rebounds by the player.
 * @param assists The total number of assists by the player.
 * @param pFouls The total number of personal fouls by the player.
 * @param steals The total number of steals by the player.
 * @param turnovers The total number of turnovers by the player.
 * @param blocks The total number of blocks by the player.
 * @param plusMinus The plus/minus of the player.
 */
data class PlayersStatisticsResponse(
    val player: Player,
    val team: Team,
    val game: Game,
    val points: Int,
    val pos: String,
    val min: String,
    val fgm: Int,
    val fga: Int,
    val fgp: String,
    val ftm: Int,
    val fta: Int,
    val ftp: String,
    val tpm: Int,
    val tpa: Int,
    val tpp: String,
    val offReb: Int,
    val defReb: Int,
    val totReb: Int,
    val assists: Int,
    val pFouls: Int,
    val steals: Int,
    val turnovers: Int,
    val blocks: Int,
    val plusMinus: String,
    val comment: String? = null,
) {
    /**
     * Represents a player in an NBA team.
     *
     * @property id The unique identifier of the player.
     * @property firstname The firstname of the player.
     * @property lastname The lastname of the player.
     */
    data class Player(
        val id: Int,
        val firstname: String,
        val lastname: String,
    )

    /**
     * Represents a game in the NBA.
     *
     * @property id The unique identifier of the game.
     */
    data class Game(
        val id: Int,
    )
}
