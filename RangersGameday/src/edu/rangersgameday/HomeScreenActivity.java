package edu.rangersgameday;

import com.example.rangersgameday.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class HomeScreenActivity extends Activity {

	private boolean mStartedFromLauncher;
	private Button mMyTicketsButton;
	private Button mPurchaseTicketButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		
		//get intent, action, and MIME type
		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();
		
		//if started from sharing image
		if (Intent.ACTION_SEND.equals(action) && type != null){
			if(type.startsWith("image/")){
				handleSentImage(intent);
			}
		}//end if 
		

		/*
		 * Button Listeners
		 */
		mMyTicketsButton = (Button)findViewById(R.id.myTicketsButton);
		mMyTicketsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					Intent i = new Intent(HomeScreenActivity.this, MyTicketsActivity.class);
					startActivity(i);
			}
		});//end listener
		
		mPurchaseTicketButton = (Button)findViewById(R.id.buyNewTicketButton);
		mPurchaseTicketButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://texas.rangers.mlb.com/ticketing/singlegame.jsp?c_id=tex"));
				startActivity(i);
			}
		});
	}//end onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	
	void handleSentImage(Intent intent){
		mStartedFromLauncher = false;
	    Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
	    if (imageUri != null){
	    	Intent i = new Intent(HomeScreenActivity.this, MyTicketsActivity.class);
	    	i.putExtra(MyTicketsActivity.EXTRA_TICKET_IMAGE, imageUri);
	    	startActivity(i);
	    }
	}

}
