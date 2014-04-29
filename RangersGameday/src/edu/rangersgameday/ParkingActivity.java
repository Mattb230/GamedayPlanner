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
	private Uri mTicketImageUri;
	private int mSavedTypeIndex;
	private int mSavedLotIndex;
	private boolean mFromSavedState;
	private double mCurrentLat;
	private double mCurrentLong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking);
		
		if(savedInstanceState != null){
			mFromSavedState = true;
			mSavedTypeIndex = savedInstanceState.getInt(STATE_TYPE_SELECTION_INDEX);
			mSavedLotIndex = savedInstanceState.getInt(STATE_LOT_SELECTION_INDEX);
			
		}
		
		//list to hold all the parking lot objects
		mLotList = new ArrayList<ParkingLot>();
		mLotTextView = (TextView)findViewById(R.id.lotTextView);
		mTailgateTextView = (TextView)findViewById(R.id.tailgateTextView);
		mHowLongTextView = (TextView)findViewById(R.id.howLongTextView);
		mMinutesTextView = (TextView)findViewById(R.id.minutesTextView);
		mHowLongEditText = (EditText)findViewById(R.id.howLongEditText);
		mTailgateRadio = (RadioGroup)findViewById(R.id.tailgateRadioGroup);
		

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
		mLotList.add(new ParkingLot("A","Season","32.748888","-97.085141","15"));
		mLotList.add(new ParkingLot("A","Cash","32.747074","-97.085518","15"));
		mLotList.add(new ParkingLot("B","Season","32.748996","-97.081978","15"));	
		mLotList.add(new ParkingLot("B","Cash","32.746677","-97.083973","15"));	
		mLotList.add(new ParkingLot("C","Reserved","32.748996","-97.081978","15"));	
		mLotList.add(new ParkingLot("C","Cash","32.749267","-97.079815","15"));	
		mLotList.add(new ParkingLot("D","Season","32.751654","-97.077937","15"));	
		mLotList.add(new ParkingLot("D","Cash","32.750219","-97.077181","15"));	
		mLotList.add(new ParkingLot("E","Reserved","32.752732","-97.080272","15"));	
		mLotList.add(new ParkingLot("F","Cash","32.757261","-97.08495","15"));	
		mLotList.add(new ParkingLot("G","Cash","32.75801","-97.085625","15"));	
		mLotList.add(new ParkingLot("H","Reserved","32.757054","-97.08878","15"));	
		mLotList.add(new ParkingLot("J","Reserved","32.75275","-97.084707","15"));	
		mLotList.add(new ParkingLot("K","Reserved","32.752249","-97.080577","15"));	
		mLotList.add(new ParkingLot("L","Reserved","32.752177","-97.079193","15"));	
		mLotList.add(new ParkingLot("M","Season","32.753639","-97.086585","15"));	
		mLotList.add(new ParkingLot("M","Cash","32.75326","-97.087465","15"));	
		mLotList.add(new ParkingLot("N","Cash","32.756007","-97.085528","15"));	
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
			mTailgateRadio.performClick();
		}
		mDepartureList.add("Current Location");
		setLatLongResult(mCurrentLat, mCurrentLong);
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
		        mCurrentLong = Double.parseDouble(mLotList.get(position).getLatitude());
			}
			@Override
			public void onNothingSelected(AdapterView<?> parentView){
			}
		});
		
		/*
		 * Minutes Confirm Button Listener
		 */
		mMinutesConfirmButton = (Button)findViewById(R.id.minutesConfirmButton);
		mMinutesConfirmButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				String toast;
				if(mHowLongEditText.getText().toString().length() != 0){
					mTailgatingLength = mHowLongEditText.getText().toString();
					toast = "You're set to tailgate for " + mTailgatingLength + " minutes";
		        	Toast.makeText(getApplicationContext(), toast,
		        			Toast.LENGTH_LONG).show();
				}
				//mTailgatingLength = mHowLongEditText.getText().toString();
			}
		});
		/*
		 * View Ticket Button Listener
		 */
		mViewTicketButton = (Button)findViewById(R.id.viewTicketButton);
		mViewTicketButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ParkingActivity.this, ViewTicketActivity.class);
				i.putExtra(ViewTicketActivity.EXTRA_TICKET_IMAGE, mTicketImageUri);
				startActivity(i);
			}
		});
		
		

		
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
	 * Radio Button Functionality. Sets the value of tailgating
	 */
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

	private void setLatLongResult(double lat, double lon){
		Intent data = new Intent();
		data.putExtra(EXTRA_PARKING_LAT, lat);
		data.putExtra(EXTRA_PARKING_LONG, lon);
		setResult(RESULT_OK, data);
	}
}
