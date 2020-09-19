package com.example.area51.helper

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*
import kotlin.collections.ArrayList

object Perm {
    fun perm(arrays: Array<String>, activity: Activity?, code: Int): Boolean {
        val listOfPermission: ArrayList<String> =
            ArrayList()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (item in arrays) {
                val permission = ContextCompat.checkSelfPermission(
                    activity!!,
                    item
                ) == PackageManager.PERMISSION_GRANTED
                if (!permission) listOfPermission.add(item)
                val per =
                    arrayOfNulls<String>(listOfPermission.size)
                listOfPermission.toArray(per)
                ActivityCompat.requestPermissions(activity, per, code)
            }
        }
        return true
    }
}