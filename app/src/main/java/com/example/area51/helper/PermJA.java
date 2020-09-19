package com.example.area51.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PermJA {

    public static Boolean permi(String[] arrays, Activity activity, int code){
        List<String> listOfPermission = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            for (String item : arrays) {
                 boolean permission = ContextCompat.checkSelfPermission(activity, item) == PackageManager.PERMISSION_GRANTED;

                if (!permission) listOfPermission.add(item);
                String[] per = new String[listOfPermission.size()];
                listOfPermission.toArray(per);
                ActivityCompat.requestPermissions(activity, per, code);
            }
        }

        return true;
    }
}
