# Unofficial Kotlin Wrapper for the API-SPORTS NBA API

Official API-NBA documentation: https://api-sports.io/documentation/nba/v2

Usage:

Make an account on [rapidapi](https://rapidapi.com/api-sports/api/api-nba/pricing) in order to get an api key.

Instantiate ApiNbaClient with your api key and choice of host:
- RAPID_API -> "https://api-nba-v1.p.rapidapi.com/"
- API_SPORTS -> "https://v2.nba.api-sports.io/"

Under the curtain, ApiNbaClient utilizes these endpoints:
- STATUS -> "status/"
- LEAGUES -> "leagues/"
- SEASONS -> "seasons/"
- TEAMS -> "teams/"
- PLAYERS -> "players/"
- GAMES -> "games/"
- STANDINGS -> "standings/"
- GAME_STATISTICS -> "games/statistics/"
- TEAM_STATISTICS -> "teams/statistics/"
- PLAYER_STATISTICS -> "players/statistics/"

ApiNbaClient exposes these methods to interact with the API:
- `getAccountStatus()` - Provides information about your account status. Does not count against your daily request limit.


- `getSeasons()` - An Integer list of all seasons available in the API.
- `getLeagues()` - A String list of all leagues represented in the API.


- `getGameStatistics(Int)` - Stats for both teams in a specific game.
- `getTeamStatistics(Int, Int)` - Season stat totals for a certain team.
- `getPlayersStatisticsByTeamAndSeason(Int, Int)` - Stat totals for all of a team's players by season.
- `getPlayerStatisticsByPlayerAndSeason(Int, Int)` - Stat totals for a single player by season.
- `getPlayersStatisticsByGame(Int)` - Stat totals for all players in a single game.


- `getGameById(Int)` - Info for a single game by game id.
- `getGamesBetweenTwoTeams(Int, Int)` - All games pertaining to a head-to-head matchup.
- `getGamesByDate(String)` - All games played on a certain date.
- `getGamesBySeason(Int)` - All games played in a certain season (This is likely a very large list).
- `getGamesByTeamAndSeason(Int, Int)` - All of a team's games for a season.
- `getLiveGames()` - All games currently being played.


- `getAllTeams()` - All teams in the API.
- `getTeamById(Int)` - Info for a single team by team id.
- `getTeamsByConference(String)` - All teams matching a conference.
- `getTeamsByDivision(String)` - All teams matching a division.
- `getTeamByCode(String)` - Info for a single team by team code.


- `getPlayersByTeamAndSeason(Int, Int)` - All players on a team for one season.
- `getPlayerById(Int)` - Info for a single player by player id.
- `getPlayersByCountry(String)` - All players matching a country.


- `getStandingsByConferenceAndSeason(String, Int)` - Standings for a conference by season.
- `getStandingsByDivisionAndSeason(String, Int)` - Standings for a division by season.
- `getStandingsByTeamAndSeason(Int, Int)` - Standings for a team by season.


- `searchTeams(String)` - Teams that match a search term.
- `searchPlayers(String)` - Players that match a search term.