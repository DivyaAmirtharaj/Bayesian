package com.divya.sciencefair.bayesian.disease.outbreak;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.divya.sciencefair.bayesian.disease.outbreak.BaseDemoActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.geojson.GeoJsonFeature;
import com.google.maps.android.geojson.GeoJsonLayer;
import com.google.maps.android.geojson.GeoJsonPointStyle;
import com.divya.sciencefair.bayesian.disease.outbreak.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONLocalOutbreak extends BaseDemoActivity {

    private final static String mLogTag = "TodaysOutbreak";

    // GeoJSON file to download
    private final String mGeoJsonUrl
            = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson";

    private GeoJsonLayer mLayer;

    /**
     * Assigns a color based on the given magnitude
     */
    private static float magnitudeToColor(String magnitude) {
        if (magnitude.equals(new String("Secondary"))) {
            return BitmapDescriptorFactory.HUE_CYAN;
        } else if (magnitude.equals(new String("Isolated"))) {
            return BitmapDescriptorFactory.HUE_GREEN;
        } else if (magnitude.equals(new String("Cluster"))) {
            return BitmapDescriptorFactory.HUE_YELLOW;
        } else if (magnitude.equals(new String("Epidemic"))) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        else return BitmapDescriptorFactory.HUE_AZURE;
    }

    protected int getLayoutId() {
        return R.layout.geojson_outbreak;
    }

    @Override
    protected void startDemo() {
        DownloadGeoJsonFile downloadGeoJsonFile = new DownloadGeoJsonFile();
        // Download the GeoJSON file
        downloadGeoJsonFile.execute(mGeoJsonUrl);
    }

    /**
     * Adds a point style to all features to change the color of the marker based on its magnitude
     * property
     */
    private void addColorsToMarkers() {
        // Iterate over all the features stored in the layer
        for (GeoJsonFeature feature : mLayer.getFeatures()) {
            // Check if the magnitude property exists
            if (feature.hasProperty("mag") && feature.hasProperty("region")) {

                String magnitude = feature.getProperty("mag");

                // Get the icon for the feature
                BitmapDescriptor pointIcon = BitmapDescriptorFactory
                        .defaultMarker(magnitudeToColor(magnitude));

                // Create a new point style
                GeoJsonPointStyle pointStyle = new GeoJsonPointStyle();

                // Set options for the point style
                pointStyle.setIcon(pointIcon);
                pointStyle.setTitle(feature.getProperty("disease") + " : " + feature.getProperty("cases") + " cases");
                pointStyle.setSnippet(feature.getProperty("msg"));

                // Assign the point style to the feature
                        feature.setPointStyle(pointStyle);
            }
        }
    }

    private class DownloadGeoJsonFile extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                // Open a stream from the URL
//                InputStream stream = new URL(params[0]).openStream();

                InputStream stream = getResources().openRawResource(R.raw.outbreak_geojson);

                String line;
                StringBuilder result = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                while ((line = reader.readLine()) != null) {
                    // Read and save each line of the stream
                    result.append(line);
                }

                // Close the stream
                reader.close();
                stream.close();

                // Convert result to JSONObject
                return new JSONObject(result.toString());
            } catch (IOException e) {
                Log.e(mLogTag, "GeoJSON file could not be read");
            } catch (JSONException e) {
                Log.e(mLogTag, "GeoJSON file could not be converted to a JSONObject");
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if (jsonObject != null) {
               // Create a new GeoJsonLayer, pass in downloaded GeoJSON file as JSONObject
                mLayer = new GeoJsonLayer(getMap(), jsonObject);

                Location myLocation = getLocation() ;
                LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());

                getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 4));


                // Add the layer onto the map
                addColorsToMarkers();
                mLayer.addLayerToMap();
            }
        }


    }
}

