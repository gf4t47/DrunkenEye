package org.opencv.samples.tutorial1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private String strButton = "Start";
    private String strResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.start)).setText(strButton);
	}

	/**
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    **/
	
	public void startTest(View view) {
		Intent intent = new Intent(this, TestActivity.class);
		startActivityForResult(intent, 9527);
	}
	
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean result = false;

        switch (requestCode) {
            case 9527:
                if (resultCode == Activity.RESULT_OK) {
                    //result = data.getStringExtra(TestActivity.KEY_RESPONSE);
                    result = data.getBooleanExtra(TestActivity.KEY_RESPONSE, false);
                }
                break;
        }

        if (result) {
            strResult = "You are drunk!";
	    }
        else {
            strResult = "You are Clear!";
        }
        ((TextView)findViewById(R.id.drunk)).setText(strResult);

        strButton = "RE DO?";
        ((TextView)findViewById(R.id.start)).setText(strButton);
	}

}
