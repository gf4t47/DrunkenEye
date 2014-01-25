package org.opencv.samples.tutorial1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private String strButton = "Start";

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

        TextView drunk = (TextView)findViewById(R.id.drunk);
        //FrameLayout main =(FrameLayout)findViewById(R.id.mainLayout);
        String strResult;
        int iRed;
        if (result) {
            strResult = "You are drunk!";
            iRed = 0xFFFF0000;
            //main.setBackgroundColor(0xFFFF0000);   //red
	    }
        else {
            strResult = "You are Clear!";
            iRed = 0xff00ff00;
            //main.setBackgroundColor(0xff00ff00);   //green
        }
        drunk.setText(strResult);
        drunk.setTextColor(iRed);

        strButton = "RE DO?";
        ((TextView)findViewById(R.id.start)).setText(strButton);
	}

}
