package barqsoft.footballscores;

import android.content.Context;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilies
{

    public static String getLeague(Context context, int league_num)
    {
        League league = League.getLeagueById(league_num);
        return League.getLeagueName(context, league);
    }
    public static String getMatchDay(Context context, int match_day, int league_num)
    {
        if(league_num == League.CHAMPIONS_LEAGUE.getLeagueId())
        {
            if (match_day <= 6)
            {
                return context.getString(R.string.matchday_uefa_6);
            }
            else if(match_day == 7 || match_day == 8)
            {
                return context.getString(R.string.matchday_uefa_first_knockout);
            }
            else if(match_day == 9 || match_day == 10)
            {
                return context.getString(R.string.matchday_uefa_quarterfinal);
            }
            else if(match_day == 11 || match_day == 12)
            {
                return context.getString(R.string.matchday_uefa_semifinal);
            }
            else
            {
                return context.getString(R.string.matchday_uefa_final);
            }
        }
        else
        {
            return context.getString(R.string.matchday, match_day);
        }
    }

    public static String getScores(Context context, int home_goals,int awaygoals)
    {
        if(home_goals < 0 || awaygoals < 0)
        {
            return context.getString(R.string.scores, "", "").trim();
        }
        else
        {
            return context.getString(R.string.scores, home_goals, awaygoals);
        }
    }

    public static int getTeamCrestByTeamName (String teamname)
    {
        if (teamname==null){return R.drawable.no_icon;}
        switch (teamname)
        { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "Arsenal London FC" : return R.drawable.arsenal;
            case "Manchester United FC" : return R.drawable.manchester_united;
            case "Swansea City" : return R.drawable.swansea_city_afc;
            case "Leicester City" : return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC" : return R.drawable.everton_fc_logo1;
            case "West Ham United FC" : return R.drawable.west_ham;
            case "Tottenham Hotspur FC" : return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC" : return R.drawable.sunderland;
            case "Stoke City FC" : return R.drawable.stoke_city;
            default: return R.drawable.no_icon;
        }
    }
}
