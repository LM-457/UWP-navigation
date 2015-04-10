/*
 *
 *   Copyright 2014 University Of Wisconsin Parkside
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package uwp.cs.edu.parkingtracker;

import java.util.ArrayList;


 //Created by Russ and Nate

public class DatabaseExchange {

    //sends vote to server
    protected static void sendVote(String zID, int vote) {
        ArrayList<String> params = new ArrayList<>();
        params.add(CONSTANTS.PUT);
        params.add(CONSTANTS.VOTE + zID + "/" + vote + "/" + CONSTANTS.AUTH_KEY);
        new RESTClient().execute(params);
    }

    //gets fullness
    protected static String getAverageVote(String zID) {
        ArrayList<String> params = new ArrayList<>();
        params.add(CONSTANTS.GET);
        params.add(CONSTANTS.VOTE_AVG + zID + "/" + CONSTANTS.AUTH_KEY);
        String returnValue = "";
        try {
            returnValue = new RESTClient().execute(params).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
