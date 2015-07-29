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

package uwp.cs.edu.parkingtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import uwp.cs.edu.parkingtracker.parking.ZoneService;

/**
 * Creates the splash screen for the application and sets everything accordingly
 * */
public class SplashActivity extends Activity {
    //Introduce a delay
    private final int WAIT_TIME = 1100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //start service
        Intent mServiceIntent = new Intent(getApplicationContext(), ZoneService.class);
        mServiceIntent.addCategory(ZoneService.TAG);
        startService(mServiceIntent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, WAIT_TIME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
