package com.b3.movie4;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class FavoriteWidgetService  extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FavoriteRemoteViewsFactory(this.getApplicationContext());
    }
}
