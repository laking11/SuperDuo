package barqsoft.footballscores;

import android.content.Context;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilies
{
//    public static final int SERIE_A = 357;
//    public static final int PREMIER_LEGAUE = 354;
//    public static final int CHAMPIONS_LEAGUE = 362;
//    public static final int PRIMERA_DIVISION = 358;
//    public static final int BUNDESLIGA = 351;
    public static final int BUNDESLIGA        = 394;
    public static final int LIGUE             = 396;
    public static final int PREMIER_LEAGUE    = 398;
    public static final int PRIMERA_DIVISION  = 399;
    public static final int SEGUNDA_DIVISION  = 400;
    public static final int SERIE_A           = 401;
    public static final int PRIMERA_LIGA      = 402;
    public static final int EREDIVISIE        = 404;
    public static final int CHAMPIONS_LEAGUE  = 405;
    public static final int DUMMYLEAGUE       = 357;

    public static String getLeague(Context c, int league_num)
    {
        switch (league_num)
        {
            case SERIE_A : return c.getString(R.string.name_seria_a);
            case PREMIER_LEAGUE : return c.getString(R.string.name_premier_league);
            case CHAMPIONS_LEAGUE : return c.getString(R.string.name_champions_league);
            case PRIMERA_DIVISION : return c.getString(R.string.name_primera_division);
            case BUNDESLIGA : return c.getString(R.string.name_bundesliga);
            default: return c.getString(R.string.name_unknown_league);
        }
    }
    public static String getMatchDay(Context c, int match_day,int league_num)
    {
        if(league_num == CHAMPIONS_LEAGUE)
        {
            if (match_day <= 6)
            {
                return c.getString(R.string.match_group_stage);
            }
            else if(match_day == 7 || match_day == 8)
            {
                return c.getString(R.string.match_first_knockout);
            }
            else if(match_day == 9 || match_day == 10)
            {
                return c.getString(R.string.match_quarterfinal);
            }
            else if(match_day == 11 || match_day == 12)
            {
                return c.getString(R.string.match_semifinal);
            }
            else
            {
                return c.getString(R.string.match_final);
            }
        }
        else
        {
            return c.getString(R.string.match_matchday_header) + String.valueOf(match_day);
        }
    }

    public static String getScores(int home_goals,int awaygoals)
    {
        if(home_goals < 0 || awaygoals < 0)
        {
            return " - ";
        }
        else
        {
            return String.valueOf(home_goals) + " - " + String.valueOf(awaygoals);
        }
    }

    public static int getTeamCrestByTeamName (Context c, String teamname)
    {
        if (teamname==null){return R.drawable.no_icon;}
        if (teamname.equals(c.getString(R.string.teamname_arsenal))) {
            return R.drawable.arsenal;
        } else if (teamname.equals(c.getString(R.string.teamname_manchester))) {
            return R.drawable.manchester_united;
        } else if (teamname.equals(c.getString(R.string.teamname_swansea))) {
            return R.drawable.swansea_city_afc;
        } else if (teamname.equals(c.getString(R.string.teamname_leicester))) {
            return R.drawable.leicester_city_fc_hd_logo;
        } else if (teamname.equals(c.getString(R.string.teamname_everton))) {
            return R.drawable.everton_fc_logo1;
        } else if (teamname.equals(c.getString(R.string.teamname_westham))) {
            return R.drawable.west_ham;
        } else if (teamname.equals(c.getString(R.string.teamname_tottenham))) {
            return R.drawable.tottenham_hotspur;
        } else if (teamname.equals(c.getString(R.string.teamname_westbromwich))) {
            return R.drawable.west_bromwich_albion_hd_logo;
        } else if (teamname.equals(c.getString(R.string.teamname_sunderland))) {
            return R.drawable.sunderland;
        } else if (teamname.equals(c.getString(R.string.teamname_stokecity))) {
            return R.drawable.stoke_city;
        } else {
            return R.drawable.no_icon;
        }
    }
}
