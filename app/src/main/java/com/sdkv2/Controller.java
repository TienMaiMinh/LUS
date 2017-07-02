package com.sdkv2;

import android.content.Context;
import android.content.Intent;

import java.util.Random;

public class Controller {
    public static void startLock(Context context){
        SharedPreferencesUtil.setShowing(context,"first",true);
        context.startService(new Intent(context, StartLockscreenService.class));
    }
}
