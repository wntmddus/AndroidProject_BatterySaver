//Incomplete right now

package com.example.batterysaverv;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Setting extends Activity {

  SeekBar seekbar1, seekbar2;
	DataStore DS1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		setSeekBarChangeListener();

	}
	
	private void setSeekBarChangeListener() {
		DS1 = new DataStore(this);
		seekbar1 = (SeekBar) findViewById(R.id.seekbar1);
		seekbar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			public void onProgressChanged(SeekBar seekbar1, int progress, boolean fromUser) {
				// seekbar has a range between 0 to 100
				//long time = progress * 60000 ;
				DS1.setTimeSetting1(progress);
				savedInstanceState.putInt("MyInt");
			}
			public void onStartTrackingTouch(SeekBar seekbar1) {
				
			}
			public void onStopTrackingTouch(SeekBar seekbar1) {
				
			}
		});
		seekbar2 = (SeekBar) findViewById(R.id.seekbar2);
		seekbar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			public void onProgressChanged(SeekBar seekbar1, int progress, boolean fromUser) {
				// seekbar has a range between 0 to 100
				DS1.setTimeSetting2(progress);
			}
			public void onStartTrackingTouch(SeekBar seekbar1) {
				
			}
			public void onStopTrackingTouch(SeekBar seekbar1) {
				
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	  super.onSaveInstanceState(savedInstanceState);
	  // Save UI state changes to the savedInstanceState.
	  // This bundle will be passed to onCreate if the process is
	  // killed and restarted.
	  savedInstanceState.putInt("MyInt", 30);
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	  super.onRestoreInstanceState(savedInstanceState);
	  // Restore UI state from the savedInstanceState.
	  // This bundle has also been passed to onCreate.
	  int myInt = savedInstanceState.getInt("MyInt");
	}

}
