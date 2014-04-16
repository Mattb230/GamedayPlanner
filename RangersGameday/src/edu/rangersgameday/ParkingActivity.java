package edu.rangersgameday;

import java.util.ArrayList;
import java.util.List;

import com.example.rangersgameday.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class ParkingActivity extends Activity {

	private Spinner mTypeSpinner;
	private Spinner mLotSpinner;	
	private Spinner mDepartFromSpinner;
	private String mCurrentType;
	private String mCurrentLot;
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
	private RadioGroup mTailgateRadio;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking);
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
		mDepartFromSpinner = (Spinner)findViewById(R.id.departFromSpinner);
		
		//list to give to the adapter
		mTypeList = new ArrayList<String>();
		mLotLetterList = new ArrayList<String>();
		
		
		/*
		 * Test Parking Lots until I get a .csv hosted online or find another way
		 */
		//ParkingLot parkingLot = new ParkingLot("A","season","32.748888","-97.085141","15");
		mLotList.add(new ParkingLot("A","Season","32.748888","-97.085141","15"));
		//parkingLot = new ParkingLot("A","cash","32.747074","-97.085518","15");
		mLotList.add(new ParkingLot("A","Cash","32.747074","-97.085518","15"));
		//parkingLot = new ParkingLot("B","season","32.748996","-97.081978","15");
		mLotList.add(new ParkingLot("B","Season","32.748996","-97.081978","15"));	
		//parkingLot = new ParkingLot("B","cash","32.746677","-97.083973","15");
		mLotList.add(new ParkingLot("B","Cash","32.746677","-97.083973","15"));	
		//parkingLot = new ParkingLot("C","reserved","32.748996","-97.081978","15");
		mLotList.add(new ParkingLot("C","Reserved","32.748996","-97.081978","15"));	
		
		//hard code the types
		mTypeList.add("Cash");
		mTypeList.add("Reserved");
		mTypeList.add("Season");
		
		//adapter
		mTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mTypeList);
		mTypeSpinner.setAdapter(mTypeAdapter);
		

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		/*
		 * On Click Listeners
		 * 
		 * Parking Type Spinner. {Cash, Season, Reserved}
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
		        populateLotLetterSpinner();	        
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		    }
		});
		
		/*
		 * Lot Letter Spinner. Contains {A, B,...M, N} excluding I
		 * 
		 * additional menu functionality commented out for later work
		 */
		mLotSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			//int temp = 0;
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        mCurrentLot = (String) parentView.getItemAtPosition(position);
		        
		        /*if(temp != 0){
		        	mTailgateTextView.setVisibility(View.VISIBLE);
		        	mTailgateRadio.setVisibility(View.VISIBLE);
		        }
		        temp++;
		        */
			}
			@Override
			public void onNothingSelected(AdapterView<?> parentView){
			}
		});
		
		mHowLongEditText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
	}//end OnCreate
	
	private void populateLotLetterSpinner(){
		//populate the lot spinner
		for(int i = 0; i < mLotList.size(); i++){
			if(mLotList.get(i).getType().equals(mCurrentType))
				mLotLetterList.add(mLotList.get(i).getLot());		
		}
	    mLotLetterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mLotLetterList);
		mLotSpinner.setAdapter(mLotLetterAdapter);
	}//end populateLotLetterSpinner
	
	/*
	 * Radio Button Functionality
	 */
	public void onRadioButtonClicked(View view){
		
		boolean checked = ((RadioButton) view).isChecked();
		
		switch(view.getId()){
		case R.id.tailgateYesRadio:
			if(checked){
				mHowLongEditText.setVisibility(View.VISIBLE);
				mHowLongTextView.setVisibility(View.VISIBLE);
				mMinutesTextView.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.tailgateNoRadio:
			if(checked){
				mHowLongEditText.setVisibility(View.GONE);
				mHowLongTextView.setVisibility(View.GONE);
				mMinutesTextView.setVisibility(View.GONE);
			}
			break;
		}//end switch
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
		
	}

}
