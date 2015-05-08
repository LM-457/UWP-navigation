/*
 * Copyright 2014 University Of Wisconsin Parkside
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uwp.cs.edu.parkingtracker.parking;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;

import uwp.cs.edu.parkingtracker.CONSTANTS;
import uwp.cs.edu.parkingtracker.MainActivity;
import uwp.cs.edu.parkingtracker.ThisApp;


/**
 * Service to handle background zone syncing.
 * Created by nate eisner
 */
public class ZoneService extends IntentService {

    public static final String ACTION = "uwp.cs.edu.parkingtracker.ZoneService";
    public static final String TAG = "MyServiceTag";

    /**
     * An IntentService must always have a constructor that calls the super constructor. The
     * string supplied to the super constructor is used to give a name to the IntentService's
     * background thread.
     */
    public ZoneService() {
        super("ZoneService");
        Log.i("Service", "New");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        Log.d("ZoneService", "Loading from server");
        final Intent intent = new Intent(ACTION);
        intent.putExtra(CONSTANTS.DATA_STATUS, false);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        int i = 1;
        Log.i("Service", "getting ids");
        ArrayList<String> zIDs = ZoneList.getInstance().getZoneIDs();
        for (String ID : zIDs) {
            Log.i("Service", "loading" + ID);
            ZoneList.getInstance().setFullness(ID);
            intent.putExtra(CONSTANTS.DATA_AMOUNT, i);
            LocalBroadcastManager.getInstance(ZoneService.this).sendBroadcast(intent);
            i++;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        final Intent intent = new Intent(ACTION);
        intent.putExtra(CONSTANTS.DATA_STATUS, true);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        ThisApp thisApp = (ThisApp) getApplication();
        MainActivity main = thisApp.getMain();
        main.loadingComplete();
        Log.i("Service", "Destroyed");
    }
}
