package edu.rangersgameday;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.rangersgameday.R;

public class MyTicketsActivity extends Activity {
	
	private Button mDummyButton;
	private Button mLetsGoButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_tickets);
		
		
		mDummyButton = (Button)findViewById(R.id.dummyButton);
		mDummyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MyTicketsActivity.this, ParkingActivity.class);
				startActivity(i);
				
			}
		});
		
		mLetsGoButton = (Button)findViewById(R.id.letsGoButton);
		mLetsGoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=32.747074	-97.085518")); 
				startActivity(i);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	

}
