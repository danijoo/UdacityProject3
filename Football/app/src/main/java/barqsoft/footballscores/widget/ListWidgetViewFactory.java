package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
class ListWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    public static final String TAG = ListWidgetViewFactory.class.getSimpleName();

    int mAppWidgetId;
    Context mContext;

    Cursor mCursor;

    public ListWidgetViewFactory(Context context, int appWidgetId) {
        mContext = context;
        this.mAppWidgetId = appWidgetId;
    }

    @Override
    public void onCreate() {
        // nothing to do here
    }

    @Override
    public void onDataSetChanged() {
        if(mCursor != null)
            mCursor.close();
        mCursor = mContext.getContentResolver().query(DatabaseContract.BASE_CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onDestroy() {
        if(mCursor != null)
            mCursor.close();
    }

    @Override
    public int getCount() {
        if(mCursor == null) {
            Log.d(TAG, "get Count: cursor is null!");
            return 0;
        }

        return mCursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.list_widget_item);
        mCursor.moveToPosition(position);

        String homeName = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.scores_table.HOME_COL));
        String awayName = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.scores_table.AWAY_COL));

        String score;
        int homeGoals = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.scores_table.HOME_GOALS_COL));
        int awayGoals = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.scores_table.AWAY_GOALS_COL));
        if(homeGoals < 0 || awayGoals < 0)
            score = mContext.getString(R.string.scores, " ", " ");
        else
            score = mContext.getString(R.string.scores, homeGoals, awayGoals);


        String dateString =
                        mCursor.getString(mCursor.getColumnIndex(DatabaseContract.scores_table.DATE_COL))
                        + " "
                        +  mCursor.getString(mCursor.getColumnIndex(DatabaseContract.scores_table.TIME_COL));
        Log.d(TAG, homeName + " " + awayName + " " + score + " " + dateString);

        rv.setTextViewText(R.id.list_widget_item_hostname, homeName);
        rv.setTextViewText(R.id.list_widget_item_awayname, awayName);
        rv.setTextViewText(R.id.list_widget_item_score, score);
        rv.setTextViewText(R.id.list_widget_item_date, dateString);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() { return null; }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}