package com.example.batterysaverv;

import android.content.Context;
import android.content.SharedPreferences;

public class DataStore {

  private SharedPreferences prefs, prefForSetting1, prefForSetting2;
	private static final String DATA_STORE_NAME = "volt_data_store";
	
	private static final String BATTERY_SAVING_MODE_KEY = "BATTERY_SAVING_MODE_KEY";
	

// This key saves values for duration when 3g is off
	private static final String DATA_STORE_SETTING_NAME1 = "volt_setting_data_store";
	private static final String SETTING_SAVE_MODE_KEY1 = "SETTING_SAVE_MODE_KEY";

	private static final String DATA_STORE_SETTING_NAME2 = "volt_setting_data_store";
	private static final String SETTING_SAVE_MODE_KEY2 = "SETTING_SAVE_MODE_KEY";
	
	public DataStore(Context context) {
		prefs = context.getSharedPreferences(DATA_STORE_NAME, 0);
		prefForSetting1 = context.getSharedPreferences(DATA_STORE_SETTING_NAME1, 0);
		prefForSetting2 = context.getSharedPreferences(DATA_STORE_SETTING_NAME2, 0);
	}
	
	public void setBatterySavingModeEnabled(boolean on) {
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean(BATTERY_SAVING_MODE_KEY, on);
		editor.commit();
	}
	
	public boolean getBatterySavingModeEnabled() {
		return prefs.getBoolean(BATTERY_SAVING_MODE_KEY, false);
	}
	
	public void setTimeSetting1(long durationWhile3GOff) {
		SharedPreferences.Editor editor = prefForSetting1.edit();
		editor.putLong(SETTING_SAVE_MODE_KEY1, durationWhile3GOff);
		editor.commit();
	}
	public long getTimeSetting1() {
		return prefForSetting1.getLong(SETTING_SAVE_MODE_KEY1, 900000);
	}
	
	public void setTimeSetting2(long durationWhile3GOn) {
		SharedPreferences.Editor editor = prefForSetting2.edit();
		editor.putLong(SETTING_SAVE_MODE_KEY2, durationWhile3GOn);
		editor.commit();		
	}
	public long getTimeSetting2() {
		return prefForSetting2.getLong(SETTING_SAVE_MODE_KEY2, 20000);
	}
}
