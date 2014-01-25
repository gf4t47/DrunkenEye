package org.opencv.samples.tutorial1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//private Button mMainButton;
	private String result = "NA";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//mMainButton = (Button)findViewById(R.id.start);
		//mMainButton.setText("Start!");
	}

	/**
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    **/
	
	public void startTest() {
		Intent intent = new Intent(this, TestActivity.class);
		
		startActivityForResult(intent, 9527);
	}
	
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	    
	    //Intent intent = null;
	    //intent = getIntent();
	    //if (intent != null) {
	    //	result = intent.getStringExtra(TestActivity.KEY_RESPONSE);
	    //}

	    // Once the activity is resumed, it will get result from Test Activity
	    // It will also display result and change the button to "RE DO?"
	    //if (!result.equals("NA")) {
	    //	mMainButton.setText("RE DO?");
	    //}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 9527:
                if (resultCode == Activity.RESULT_OK) {
                    result = data.getStringExtra(TestActivity.KEY_RESPONSE);
                }
                break;
        }

        //Log.d("Xing", result);


        assert result != null;
        if (!result.equals("NA")) {
            ((TextView)findViewById(R.id.start)).setText("RE DO?");
	    }
	}

}
