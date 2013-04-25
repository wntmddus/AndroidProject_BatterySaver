package com.example.batterysaverv;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

  @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        setButtonClickListener();
	}
    
    private void setButtonClickListener() {
    	ImageButton newActivityButton = (ImageButton)findViewById(R.id.goToNext);
    	newActivityButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			startNewActivity();
    		}
    	});
    	
    }
    
    private void startNewActivity() {
    	Intent intent = new Intent(this, StartingActivity.class);
    	this.startActivity(intent);
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
