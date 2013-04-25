package com.example.batterysaverv;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartingActivity extends Activity {

  private boolean on;
	private DataStore DS;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting);
		setButtonClickListener();
	}
	
	private void togglePicture() {
		ImageView imageView = (ImageView)findViewById(R.id.toggleUi);
		Drawable newPhoneImage;
		
		if(on) {
			newPhoneImage = getResources().getDrawable(R.drawable.turned_off);
		} else {
			newPhoneImage = getResources().getDrawable(R.drawable.turned_on);
		}
		
		imageView.setImageDrawable(newPhoneImage);
	}

	private void setButtonClickListener() {
		//final MobileDataToggler toggle = new MobileDataToggler(this);
		//on = Toggle.isMobileDataEnabled();
		DS = new DataStore(this);
		//DS.setBatterySavingModeEnabled(false);
		// first setBatterySavingModeEnabled to false in the memory
		Button threeGonOff = (Button)findViewById(R.id.button1);
		threeGonOff.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				on = DS.getBatterySavingModeEnabled();
				//3G turn on and off feature will be included in here
				if(!on) {
					//Toggle.toggleMobileData(on);
					//usually when user hit toggle button for the very first time,
					//it will receive false from getBatterySavingModeEnabled();
					on = true; // change boolean on to true
					toggleBatterySavingMode(on); // true value turns on broadcast receiver
					DS.setBatterySavingModeEnabled(on); // on value saved as true				
				} else {
					//Toggle.toggleMobileData(on);
					on = false; // when true is received from on, on is switched to false
					toggleBatterySavingMode(on); // false value received from on will 
												// turns off broadcast receiver
					DS.setBatterySavingModeEnabled(on); // on value saved as false by saving false 
														// in the memory
				}
				togglePicture(); // toggle turn off and turn on picture
								// in order to see current status
			}
		});

		Button setting = (Button)findViewById(R.id.button2);
		setting.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startNewActivity();
			}
		});
	}

	private void toggleBatterySavingMode(boolean on) {
		PackageManager pm = this.getPackageManager();
		ComponentName ScreenWatchReceiverName = new ComponentName(this, ScreenWatchReceiver.class);
		if (on) {
			pm.setComponentEnabledSetting(ScreenWatchReceiverName, 
					PackageManager.COMPONENT_ENABLED_STATE_ENABLED, 0);
		} else {
			pm.setComponentEnabledSetting(ScreenWatchReceiverName, 
					PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 0);
		}
	}

	private void startNewActivity() {
		Intent intent = new Intent(this, Setting.class);
		this.startActivity(intent);
	}
/*
	@Override
	protected void onResume() {
		super.onResume();
		togglePicture();
		
	}
	*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.starting, menu);
		return true;
	}
}
