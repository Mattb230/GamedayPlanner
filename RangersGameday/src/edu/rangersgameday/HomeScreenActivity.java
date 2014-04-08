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

	private Button mMyTicketsButton;
	private Button mPurchaseTicketButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		
		mMyTicketsButton = (Button)findViewById(R.id.myTicketsButton);
		mMyTicketsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeScreenActivity.this, MyTicketsActivity.class);
				startActivity(i);
			}
		});
		
		mPurchaseTicketButton = (Button)findViewById(R.id.buyNewTicketButton);
		mPurchaseTicketButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://texas.rangers.mlb.com/ticketing/singlegame.jsp?c_id=tex"));
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
