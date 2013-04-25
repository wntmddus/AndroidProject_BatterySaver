package com.example.batterysaverv;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.enspirit.data.toggle.MobileDataToggler;

/**
 * 
 * BroadcastReceiver that receives screen off and screen on events.
 * 
 * @author Seung Yeon Joo
 *
 */
public class ScreenWatchReceiver extends BroadcastReceiver {

  final public static String ONE_TIME = "onetime";
	MobileDataToggler dataToggler;
	DataStore DS;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//DS = new DataStore(context);
		dataToggler = new MobileDataToggler(context);
		if (Intent.ACTION_SCREEN_OFF == intent.getAction()) {
			dataToggler.toggleMobileData(false);// when screen off is received from intent, 3g is turned off
			TurnOnOff3GForXmins(context);
		} else if (Intent.ACTION_SCREEN_ON == intent.getAction()) {
			dataToggler.toggleMobileData(true);
		}
		
	}

//This function will turn off 3g for x amount of mins and turns 3g back on for y amount of seconds while screen is off
	public void TurnOnOff3GForXmins(Context context)
	{
		long i;
		DS = new DataStore(context);
		AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, TurnOnOff3GReceiver.class);
		intent.setAction("turn_off_3g");
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		//After after 30 min
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 30 * 60 * 1000, pi);
		i = System.currentTimeMillis() + 30 * 60 * 1000;
		Intent intent2 = new Intent(context, TurnOnOff3GReceiver.class);
		intent2.setAction("turn_on_3g");
		PendingIntent pi2 = PendingIntent.getBroadcast(context, 1, intent2, 0);
		am.set((int) i, i + 20 * 1000, pi2);
	}
/*
	public void TurnOn3GForXseconds(Context context)
	{
		DS = new DataStore(context);
		AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, TurnOnOff3GReceiver.class);
		intent.setAction("turn_on_3g");
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		//After after 20 seconds
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000 * 20 , pi); //20 secs
	}
*/
}
