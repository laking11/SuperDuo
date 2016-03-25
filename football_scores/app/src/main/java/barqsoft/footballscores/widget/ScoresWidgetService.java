package barqsoft.footballscores.widget;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.Date;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;

/**
 * Created by laking on 3/17/16.
 */
public class ScoresWidgetService extends RemoteViewsService {

    private static final String[] SCORE_PROJECTION = {
            DatabaseContract.scores_table.HOME_COL,
            DatabaseContract.scores_table.AWAY_COL,
            DatabaseContract.scores_table.HOME_GOALS_COL,
            DatabaseContract.scores_table.AWAY_GOALS_COL,
    };

    public static final int COL_HOME = 0;
    public static final int COL_AWAY = 1;
    public static final int COL_HOME_GOALS = 2;
    public static final int COL_AWAY_GOALS = 3;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor dataCursor = null;

            @Override
            public void onDataSetChanged() {
                if (dataCursor != null) {
                    dataCursor.close();
                }

                final long identityToken = Binder.clearCallingIdentity();
                Uri dateUri = DatabaseContract.scores_table.buildScoreWithDate();
                String[] date = new String[1];
                SimpleDateFormat mformat = new SimpleDateFormat(getString(R.string.date_year_month_day));
                //Date d = new Date(System.currentTimeMillis() + (2 * 86400000));
                Date d = new Date(System.currentTimeMillis());
                date[0] = mformat.format(d);
                dataCursor = getContentResolver().query(dateUri, SCORE_PROJECTION, null, date, null);
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (dataCursor != null) {
                    dataCursor.close();
                    dataCursor = null;
                }

            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        dataCursor == null ||
                        !dataCursor.moveToPosition(position)) {
                    return null;
                }
                RemoteViews rview = new RemoteViews(getPackageName(),
                        R.layout.scores_widget_list_item);

                String hName = dataCursor.getString(COL_HOME);
                String aName = dataCursor.getString(COL_AWAY);
                Integer hGoals = dataCursor.getInt(COL_HOME_GOALS);
                Integer aGoals = dataCursor.getInt(COL_AWAY_GOALS);

                rview.setTextViewText(R.id.widg_home_name, hName);
                rview.setTextViewText(R.id.widg_away_name, aName);

                String hScore="-", aScore = "-";
                if (hGoals != -1) {
                    hScore = hGoals.toString();
                    aScore = aGoals.toString();
                }

                rview.setTextViewText(R.id.widg_home_score, hScore);
                rview.setTextViewText(R.id.widg_away_score, aScore);

                final Intent intent = new Intent();

                Uri scoreUri = DatabaseContract.scores_table.buildScoreWithDate();

                intent.setData(scoreUri);
                rview.setOnClickFillInIntent(R.id.widg_list_item, intent);
                return rview;
            }

            @Override
            public int getCount() {
                if (dataCursor == null) return 0;
                return dataCursor.getCount();
            }

            @Override
            public RemoteViews getLoadingView() {

                return new RemoteViews(getPackageName(), R.layout.scores_widget_list_item);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public void onCreate() {
                // Nothing here to do
            }

            @Override
            public long getItemId(int position) {
                if (dataCursor.moveToPosition(position))
                    //return dataCursor.getLong(INDEX_WEATHER_ID);
                    return dataCursor.getLong(0);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }
        };
    }
}
