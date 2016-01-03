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

package com.divya.sciencefair.bayesian.disease.outbreak;

import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
import com.divya.sciencefair.bayesian.disease.outbreak.model.OutbreakItem;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

/**
 * Simple activity demonstrating ClusterManager.
 */
public class LocalOutbreak extends BaseLocalMapActivity {
    private ClusterManager<OutbreakItem> mClusterManager;

    @Override
    protected void startMap() {

        Location myLocation = getLocation() ;
        LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());

        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 2));

        mClusterManager = new ClusterManager<OutbreakItem>(this, getMap());

        getMap().setOnCameraChangeListener(mClusterManager);

        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of outbreaks.", Toast.LENGTH_LONG).show();
        }
    }

    private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.outbreak_history);
        List<OutbreakItem> items = new OutBreakReader().read(inputStream);

        mClusterManager.addItems(items);

    }
}