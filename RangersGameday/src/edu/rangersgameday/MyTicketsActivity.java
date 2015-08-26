package edu.rangersgameday;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rangersgameday.R;

public class MyTicketsActivity extends Activity {
	
	private Button mDummyButton;
	private ImageButton mDummyImageButton;
	private TextView mAddTicketTextView;
	private Button mLaunchGalleryButton;
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
		mLaunchGalleryButton = (Button)findViewById(R.id.launchGalleryButton);
		mAddTicketTextView = (TextView)findViewById(R.id.addTicketTextView);
		mAddTicketTextView.setText(R.string.addTicketPrompt);
		if(mDummyImage != null){
			mAddTicketTextView.setVisibility(View.GONE);
			mLaunchGalleryButton.setVisibility(View.GONE);
		}
//////////////////////////////////////////////////////////////////////////////////////////////
		/*
		 * On Click Listeners
		 */
//////////////////////////////////////////////////////////////////////////////////////////////

		/*
		 * Image Button Listener
		 */
		mDummyImageButton = (ImageButton)findViewById(R.id.ticketImageButton);
		mDummyImageButton.setImageURI(mDummyImage);
		//If there is no ticket, hide the imageButton and display prompt for the user
		if(mDummyImage == null){
			mDummyImageButton.setVisibility(View.GONE);
		}
		mDummyImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MyTicketsActivity.this, RangersParkingActivity.class);
				i.putExtra(RangersParkingActivity.EXTRA_TICKET_IMAGE, mDummyImage);
				startActivity(i);
			}
		});
		
		/*
		 * Launch Gallery Button Listener. I couldn't get it totally working, gallery closes once I select an image. To preview it change the 
		 * iff statement condition to "mDummyImage != null"
		 */
		mLaunchGalleryButton.setText(R.string.launchGallery);
		mLaunchGalleryButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType("image/*");
				startActivity(i);
			}
		});
////////////////////////////////////////////////////////////////////////////////////////////////		
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	

}
