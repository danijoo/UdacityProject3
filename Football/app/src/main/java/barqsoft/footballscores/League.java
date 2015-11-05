package barqsoft.footballscores;

import android.content.Context;
import android.support.annotation.Nullable;

public enum League {

    BUNDESLIGA1(394, R.string.league_name_bundesliga1),
    BUNDESLIGA2(395, R.string.league_name_bundesliga2),
    LIGUE1(396, R.string.league_name_league1),
    LIGUE2(397, R.string.league_name_league2),
    PREMIER_LEAGUE(398, R.string.league_name_premierleague),
    PRIMERA_DIVISION(399, R.string.league_name_primeradivision),
    SEGUNDA_DIVISION(400, R.string.league_name_segundadivision),
    SERIE_A(401, R.string.league_name_seriea),
    PRIMERA_LIGA(402, R.string.league_name_primeraliga),
    Bundesliga3(403, R.string.league_name_bundesliga3),
    EREDIVISIE(404, R.string.league_name_eradivisie),
    CHAMPIONS_LEAGUE(405, R.string.league_name_championsleague);

    /**
     * @param id of a league
     * @return League or null
     */
    public static League getLeagueById(int id) {
        for(League league : values()) {
            if(league.getLeagueId() == id)
                return league;
        }
        return null;
    }

    /**
     * @param context Context
     * @param league League or Null
     * @return The name of a given league or an error string if league is null
     */
    public static String getLeagueName(Context context, @Nullable League league) {
        if(league != null)
            return league.getName(context);
        else
            return context.getString(R.string.league_name_not_found);
    }


    int leagueId;
    int nameResId;

    League(int leagueId, int nameResId) {
        this.leagueId = leagueId;
        this.nameResId = nameResId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public int getNameResId() {
        return nameResId;
    }

    public String getName(Context context) {
        return context.getString(getNameResId());
    }

}
