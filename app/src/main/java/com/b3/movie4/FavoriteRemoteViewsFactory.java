package com.b3.movie4;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.b3.movie4.database.DatabaseContract;
import com.b3.movie4.model.FavoriteMovie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.b3.movie4.database.DatabaseContract.FilmColumn.POSTER;

public class FavoriteRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final List<FavoriteMovie> widgetItems = new ArrayList<>();
    private Context context;
    private Cursor cursor;

    public FavoriteRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        widgetItems.clear();
        final long identityToken = Binder.clearCallingIdentity();
        cursor = context.getContentResolver().query( DatabaseContract.FilmColumn.CONTENT_URI, null, null, null, null);
        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);

        if (cursor.moveToPosition(i)){
            String movieFavorite = cursor.getString(cursor.getColumnIndexOrThrow(POSTER));
            String urlImage = "https://image.tmdb.org/t/p/w500/";
            Bitmap bitmap;
            try {
                bitmap = Glide.with(context.getApplicationContext())
                        .asBitmap()
                        .load(urlImage+ movieFavorite)
                        .apply(new RequestOptions().fitCenter())
                        .submit()
                        .get();
                remoteViews.setImageViewBitmap(R.id.imageView, bitmap);
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(FavoriteWidget.EXTRA_ITEM, widgetItems.get(i).getTitle());
        Intent intent = new Intent();
        intent.putExtras(bundle);

        remoteViews.setOnClickFillInIntent(R.id.imageView, intent);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
