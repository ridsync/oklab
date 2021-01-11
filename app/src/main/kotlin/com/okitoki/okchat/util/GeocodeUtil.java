package com.okitoki.okchat.util;

/**
 * Created by okwon on 2021/01/08.
 */

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.util.ArrayList;
import java.util.List;

public class GeocodeUtil {
    final Geocoder geocoder;

    public static class GeoLocation {

        double latitude;
        double longitude;

        public GeoLocation(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    public GeocodeUtil(Context context) {
        geocoder = new Geocoder(context);
    }

    public ArrayList<GeoLocation> getGeoLocationListUsingAddress(String address) {
        ArrayList<GeoLocation> resultList = new ArrayList<>();


        try {
            List<Address> list = geocoder.getFromLocationName(address, 10);

            for (Address addr : list) {
                resultList.add(new GeoLocation(addr.getLatitude(), addr.getLongitude()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public ArrayList<String> getAddressListUsingGeolocation(GeoLocation location) {
        ArrayList<String> resultList = new ArrayList<>();

        try {
            List<Address> list = geocoder.getFromLocation(location.latitude, location.longitude, 2);

            for (Address addr : list) {
                resultList.add(addr.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }
}