package edu.rangersgameday;

import java.net.URI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.rangersgameday.R;

public class MyTicketsActivity extends Activity {
	
	private Button mDummyButton;
	private Button mLetsGoButton;
	private ImageButton mDummyImageButton;
	private Uri mDummyImage;
	private double mLat;
	private double mLong;

	public static final String EXTRA_TICKET_IMAGE = "edu.rangersgameday.extra_ticket_image";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_tickets);
		
		mDummyImage = getIntent().getParcelableExtra(EXTRA_TICKET_IMAGE);
		
		

//////////////////////////////////////////////////////////////////////////////////////////////
		/*
		 * On Click Listeners
		 */
//////////////////////////////////////////////////////////////////////////////////////////////

		/*
		 * Image Button Listener
		 */
		mDummyImageButton = (ImageButton)findViewById(R.id.dummyImageButton);
		mDummyImageButton.setImageURI(mDummyImage);
		if(mDummyImage == null)
			mDummyImageButton.setVisibility(View.GONE);
		mDummyImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MyTicketsActivity.this, ParkingActivity.class);
				i.putExtra(ParkingActivity.EXTRA_TICKET_IMAGE, mDummyImage);
				startActivityForResult(i, 0);
			}
		});
		
		/*
		 * Lets Go Button Listener
		 */
		mLetsGoButton = (Button)findViewById(R.id.letsGoButton);
		mLetsGoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+mLat+"	"+mLong)); 
				startActivity(i);
			}
		});
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (data == null){
			return;
		}
		mLong = data.getDoubleExtra(ParkingActivity.EXTRA_PARKING_LONG, 0);
		mLat = data.getDoubleExtra(ParkingActivity.EXTRA_PARKING_LAT, 0);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	

}
