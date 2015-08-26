package edu.rangersgameday;

import java.util.ArrayList;
import java.util.List;

import com.example.rangersgameday.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ParkingActivity extends Activity {
	
	public static final String EXTRA_PARKING_LAT = "edu.rangersgameday.extra_parking_lat";
	public static final String EXTRA_PARKING_LONG = "edu.rangersgameday.extra_parking_long";
	public static final String EXTRA_TICKET_IMAGE = "edu.rangersgameday.extra_ticket_image";
	public static final String STATE_TYPE_SELECTION_INDEX = "typeSelectionIndex";
	public static final String STATE_LOT_SELECTION_INDEX = "lotSelectionIndex";

	private String mCurrentType;
	private String mCurrentLot;
	private String mTailgatingLength;
	private boolean mTailgating = false;
	
	private Spinner mTypeSpinner;
	private Spinner mLotSpinner;	
	private Spinner mDepartFromSpinner;
	private ArrayAdapter<String> mTypeAdapter;
	private ArrayAdapter<String> mLotLetterAdapter;
	private ArrayAdapter<String> mTailgateAdapter;
	private ArrayAdapter<String> mDepartFromAdapter;
	private TextView mLotTextView;
    private TextView mParkingTextView;

    private TextView mTailgateTextView;
	private TextView mHowLongTextView;
	private TextView mMinutesTextView;	
	private EditText mHowLongEditText;
	private List<ParkingLot> mLotList; 
	private List<String> mTypeList;
	private List<String> mLotLetterList;
	private List<String> mDepartureList;
	private RadioGroup mTailgateRadio;
	private Button mMinutesConfirmButton;
	private Button mViewTicketButton;
	private Button mLetsGoButton;
	private Button mPurchaseTicketsButton;
	private Uri mTicketImageUri;
	private int mSavedTypeIndex;
	private int mSavedLotIndex;
	private boolean mFromSavedState;
	private double mCurrentLat;
	private double mCurrentLong;
    private String mStadium;
    private String mTicketURL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking);
		
		if(savedInstanceState != null){
			mFromSavedState = true;
			mSavedTypeIndex = savedInstanceState.getInt(STATE_TYPE_SELECTION_INDEX);
			mSavedLotIndex = savedInstanceState.getInt(STATE_LOT_SELECTION_INDEX);
			
		}

        Intent intent = getIntent();
        mStadium = intent.getExtras().getString("stadium");

        mParkingTextView = (TextView)findViewById(R.id.parkingTextView);

		
		//list to hold all the parking lot objects
		mLotList = new ArrayList<ParkingLot>();
		mLotTextView = (TextView)findViewById(R.id.lotTextView);

		//mTailgateTextView = (TextView)findViewById(R.id.tailgateTextView);
		//mHowLongTextView = (TextView)findViewById(R.id.howLongTextView);
		//mMinutesTextView = (TextView)findViewById(R.id.minutesTextView);
		//mHowLongEditText = (EditText)findViewById(R.id.howLongEditText);
		//mTailgateRadio = (RadioGroup)findViewById(R.id.tailgateRadioGroup);
		

		mTypeSpinner = (Spinner)findViewById(R.id.typeSpinner);		
		mLotSpinner = (Spinner)findViewById(R.id.lotSpinner);
		
		//list to give to the adapter
		mTypeList = new ArrayList<String>();
		mLotLetterList = new ArrayList<String>();
		mDepartureList = new ArrayList<String>();
		
		//get the ticket image
		mTicketImageUri = getIntent().getParcelableExtra(EXTRA_TICKET_IMAGE);
///////////////////////////////////////////////////////////////////////////////////////////////////		
		/*
		 * Test Parking Lots until I get a .csv hosted online or find another way
		 */
        if(mStadium.equalsIgnoreCase("rangers")) {
            mLotList.add(new ParkingLot("A", "Season", "32.748888", "-97.085141", "15"));
            mLotList.add(new ParkingLot("A", "Cash", "32.747074", "-97.085518", "15"));
            mLotList.add(new ParkingLot("B", "Season", "32.748996", "-97.081978", "15"));
            mLotList.add(new ParkingLot("B", "Cash", "32.746677", "-97.083973", "15"));
            mLotList.add(new ParkingLot("C", "Reserved", "32.748996", "-97.081978", "15"));
            mLotList.add(new ParkingLot("C", "Cash", "32.749267", "-97.079815", "15"));
            mLotList.add(new ParkingLot("D", "Season", "32.751654", "-97.077937", "15"));
            mLotList.add(new ParkingLot("D", "Cash", "32.750219", "-97.077181", "15"));
            mLotList.add(new ParkingLot("E", "Reserved", "32.752732", "-97.080272", "15"));
            mLotList.add(new ParkingLot("F", "Cash", "32.757261", "-97.08495", "15"));
            mLotList.add(new ParkingLot("G", "Cash", "32.75801", "-97.085625", "15"));
            mLotList.add(new ParkingLot("H", "Reserved", "32.757054", "-97.08878", "15"));
            mLotList.add(new ParkingLot("J", "Reserved", "32.75275", "-97.084707", "15"));
            mLotList.add(new ParkingLot("K", "Reserved", "32.752249", "-97.080577", "15"));
            mLotList.add(new ParkingLot("L", "Reserved", "32.752177", "-97.079193", "15"));
            mLotList.add(new ParkingLot("M", "Season", "32.753639", "-97.086585", "15"));
            mLotList.add(new ParkingLot("M", "Cash", "32.75326", "-97.087465", "15"));
            mLotList.add(new ParkingLot("N", "Cash", "32.756007", "-97.085528", "15"));
            mTicketURL = "http://texas.rangers.mlb.com/ticketing/singlegame.jsp?c_id=tex";
            mParkingTextView.setText("Rangers Parking");
        } else {
            mLotList.add(new ParkingLot("1","Cowboys Blue","32.749424","-97.093337","15"));
            mLotList.add(new ParkingLot("2","Cowboys Blue","32.749489","-97.091896","15"));
            mLotList.add(new ParkingLot("3","Cowboys Blue","32.749610","-97.090471","15"));
            mLotList.add(new ParkingLot("4","Cowboys Silver","32.748585","-97.088795","15"));
            mLotList.add(new ParkingLot("5","Cowboys Silver","32.747214","-97.090558","15"));
            mLotList.add(new ParkingLot("6","Cowboys Silver","32.746516","-97.092202","15"));
            mLotList.add(new ParkingLot("7","Cowboys Blue","32.746209","-97.093806","15"));
            mLotList.add(new ParkingLot("10","Cowboys Blue","32.748769","-97.095736","15"));
            mLotList.add(new ParkingLot("11","Cowboys Blue","32.744471","-97.096163","15"));
            mLotList.add(new ParkingLot("12","Cowboys Silver","32.744349","-97.091498","15"));
            mLotList.add(new ParkingLot("13","Cowboys Silver","32.743830","-97.089897","15"));
            mLotList.add(new ParkingLot("14","Cowboys Silver","32.741590","-97.089663","15"));
            mLotList.add(new ParkingLot("15","Cowboys Blue","32.749025","-97.100483","15"));
            mLotList.add(new ParkingLot("A","Rangers Pre-Sold","32.748285","-97.085423","15"));
            mLotList.add(new ParkingLot("B","Rangers Pre-Sold","32.748733","-97.082890","15"));
            mLotList.add(new ParkingLot("C","Rangers Cash Lots","32.748950","-97.079941","15"));
            mLotList.add(new ParkingLot("D","Rangers Cash Lots","32.750628","-97.077201","15"));
            mLotList.add(new ParkingLot("E","Rangers Cash Lots","32.753316","-97.080466","15"));
            mLotList.add(new ParkingLot("F","Rangers Cash Lots","32.758022","-97.084004","15"));
            mLotList.add(new ParkingLot("G","Rangers Cash Lots","32.758282","-97.086386","15"));
            mLotList.add(new ParkingLot("H","Rangers Cash Lots","32.756329","-97.089671","15"));
            mLotList.add(new ParkingLot("K","Rangers Cash Lots","32.750741","-97.080387","15"));
            mLotList.add(new ParkingLot("L","Rangers Cash Lots","32.751749","-97.078187","15"));
            mLotList.add(new ParkingLot("M","Rangers Pre-Sold","32.754220","-97.087534","15"));
            mLotList.add(new ParkingLot("N","Rangers Cash Lots","32.755419","-97.086005","15"));
            mTicketURL = "http://www.ticketmaster.com/Dallas-Cowboys-tickets/artist/805931?camefrom=CFC_COWBOYS_15DCSGTIX&intcmp=tm110181&wt.mc_id=NFL_TEAM_DAL_MAIN_TIX_PG_LINK_PRI&brand=nfl";
            mParkingTextView.setText("Cowboys Parking");

        }
///////////////////////////////////////////////////////////////////////////////////////////////////
		
		//populate lot type list
		for(int i = 0; i < mLotList.size(); i++){
			if(!mTypeList.contains(mLotList.get(i).getType())){
				mTypeList.add(mLotList.get(i).getType());
			}
		}
		//set adapter for type list
		mTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mTypeList);
		mTypeSpinner.setAdapter(mTypeAdapter);
		
		if(mFromSavedState){
			mTypeSpinner.setSelection(mSavedTypeIndex);
			//mTailgateRadio.performClick();
		}
		mDepartureList.add("Current Location");
		//setLatLongResult(mCurrentLat, mCurrentLong);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		/*
		 * On Click Listeners
		 * 
		 * Parking Type Spinner Listener. {Cash, Season, Reserved}
		 * 
		 * additional menu functionality commented out for later work
		 */
		mTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			int temp = 0;
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        mCurrentType = (String) parentView.getItemAtPosition(position);
		        //clear spinner and make lot items visible
		        if(temp != 0){
		        	mLotLetterAdapter.clear();
			        //mLotTextView.setVisibility(View.VISIBLE);		        	
			        //mLotSpinner.setVisibility(View.VISIBLE);	        	
		        }
	        	temp++;
	        	//populate LotLetter spinner
		        populateLotLetterSpinner(mFromSavedState);

		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		    }
		});
		
		/*
		 * Lot Letter Spinner Listener. Contains {A, B,...M, N} excluding I
		 * 
		 * additional menu functionality commented out for later work
		 */
		mLotSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        mCurrentLot = (String) parentView.getItemAtPosition(position);
		        mCurrentLat = Double.parseDouble(mLotList.get(position).getLatitude());
		        mCurrentLong = Double.parseDouble(mLotList.get(position).getLongitude());
			}
			@Override
			public void onNothingSelected(AdapterView<?> parentView){
			}
		});
		
		/*
		 * Minutes Confirm Button Listener -- @TODO uncomment when reado to implement
		 *
		 */
		/*
		mMinutesConfirmButton = (Button)findViewById(R.id.minutesConfirmButton);
		mMinutesConfirmButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				String toast;
				if(mHowLongEditText.getText().toString().length() != 0){
					mTailgatingLength = mHowLongEditText.getText().toString();
					toast = "You're set to tailgate for " + mTailgatingLength + " minutes";
		        	Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
				}
				//mTailgatingLength = mHowLongEditText.getText().toString();
			}
		});
		*/
		/*
		 * Let's Go Button Listener. Launched Navigation Intent to desires GPS coordinates
		 */
        mLetsGoButton = (Button)findViewById(R.id.letsGoButton);
        mLetsGoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + mCurrentLat + "," + mCurrentLong ));
                startActivity(i);
            }
        });

		/*
		 * Purchase Tickets Button Listener. Launches web broser to allow user to purchase tickets
		 */
        mPurchaseTicketsButton = (Button)findViewById(R.id.purchaseTicketsButton);
        mPurchaseTicketsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(mTicketURL));
                startActivity(i);
            }
        });

		/*
		 * View Ticket Button Listener -- @TODO Comment out when ready to implement
		 * Will be implemened at a later date
		 * Will save current GPS location, and when called again, will navigate back to gps location
		 */
		/*
		mViewTicketButton = (Button)findViewById(R.id.viewTicketButton);
		mViewTicketButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ParkingActivity.this, ViewTicketActivity.class);
				i.putExtra(ViewTicketActivity.EXTRA_TICKET_IMAGE, mTicketImageUri);
				startActivity(i);
			}
		});
		*/

		

		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
	}//end OnCreate
	
	/*
	 * Method to populate the Lot Letter Spinner. Only populates it with lots that match the current lot type
	 */
	private void populateLotLetterSpinner(boolean fromSavedState){
		//populate the lot spinner
		for(int i = 0; i < mLotList.size(); i++){
			if(mLotList.get(i).getType().equals(mCurrentType))
				mLotLetterList.add(mLotList.get(i).getLot());		
		}
	    mLotLetterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mLotLetterList);
		mLotSpinner.setAdapter(mLotLetterAdapter);
		
		//if loading from saved state set selection to saved selection
		if(fromSavedState){
			mLotSpinner.setSelection(mSavedLotIndex);
		}
	}//end populateLotLetterSpinner
	
	/*
	 * @TODO uncomment when ready to implement tailgating
	 * Radio Button Functionality. Sets the value of tailgating
	 */
	/*
	public void onRadioButtonClicked(View view){
		
		boolean checked = ((RadioButton)view).isChecked();
		
		switch(view.getId()){
		case R.id.tailgateYesRadio:
			if(checked){
				mHowLongEditText.setVisibility(View.VISIBLE);
				mHowLongTextView.setVisibility(View.VISIBLE);
				mMinutesTextView.setVisibility(View.VISIBLE);
				mMinutesConfirmButton.setVisibility(View.VISIBLE);
				mTailgating = true;
			}
			break;
		case R.id.tailgateNoRadio:
			if(checked){
				mHowLongEditText.setVisibility(View.GONE);
				mHowLongTextView.setVisibility(View.GONE);
				mMinutesTextView.setVisibility(View.GONE);
				mMinutesConfirmButton.setVisibility(View.GONE);				
				mTailgating = false;
			}
			break;
		}//end switch
	}
	*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
		
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putInt(STATE_TYPE_SELECTION_INDEX, mTypeSpinner.getSelectedItemPosition());
		savedInstanceState.putInt(STATE_LOT_SELECTION_INDEX, mLotSpinner.getSelectedItemPosition());
		super.onSaveInstanceState(savedInstanceState);
	}

}
