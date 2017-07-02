package com.sdkv2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;
import java.util.concurrent.locks.Lock;


public class StartLockscreenReceiver extends BroadcastReceiver {
	CountDownTimer countDownTimer;
	@Override
	public void onReceive(final Context context, final Intent intent) {
		Log.e("test","intent : " + intent.getAction());
		if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
			MobileAds.initialize(context, "ca-app-pub-8634259134319673~6932397142");
			final InterstitialAd mInterstitialAd = new InterstitialAd(context);
			mInterstitialAd.setAdUnitId("ca-app-pub-8634259134319673/8129928746");
			mInterstitialAd.loadAd(new AdRequest.Builder().build());
			mInterstitialAd.setAdListener(new AdListener() {
				@Override
				public void onAdLoaded() {
					super.onAdLoaded();
					Random random = new Random();
					int num = 10 + random.nextInt(20);
					num = num * 1000;
					countDownTimer = new CountDownTimer(num,1000){

						@Override
						public void onTick(long millisUntilFinished) {
							Log.e("test","time : " + millisUntilFinished);
						}

						@Override
						public void onFinish() {
							mInterstitialAd.show();
						}
					};
					countDownTimer.start();

				}

				@Override
				public void onAdClosed() {
					super.onAdClosed();

					Intent intent1 = new Intent(context,Nothing.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
				}

			});
		}
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
			if (countDownTimer != null)
			countDownTimer.cancel();
		}
	}
}
