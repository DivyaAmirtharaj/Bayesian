/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.divya.sciencefair.bayesian.disease.outbreak.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class OutbreakItem implements ClusterItem {
    private final LatLng mPosition;
    private int mYear, mCases;

/*
   public OutbreakItem(double lat, double lng)
    {
        mPosition = new LatLng(lat, lng);

    }

*/
    public OutbreakItem(double lat, double lng, int year, int cases)
    {
        mPosition = new LatLng(lat, lng);
        mYear = year;
        mCases = cases;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    public int getYear() {
        return mYear;
    }
    public int getCases() {
        return mCases;
    }


}
