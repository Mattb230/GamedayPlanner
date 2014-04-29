package edu.rangersgameday;

import com.example.rangersgameday.R;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class ViewTicketActivity extends Activity {

	private ImageView mTicketImageView;
	private Uri mTicketImageUri;
	
	public static final String EXTRA_TICKET_IMAGE = "edu.rangersgameday.extra_ticket_image";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_ticket);
		
		mTicketImageUri = getIntent().getParcelableExtra(EXTRA_TICKET_IMAGE);
		mTicketImageView = (ImageView)findViewById(R.id.viewTicketImageView);
		mTicketImageView.setImageURI(mTicketImageUri);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
