package edu.rangersgameday;

public class ParkingLot {
	// {Stadium, Cash, Reserved}
	private String mType;
	//{A, B, C, ... , M, N}
	private String mLot;
	//walk length in minutes
	private String mWalkLength;
	private String mLatitude;
	private String mLongitude;
	
	
	public ParkingLot(){
		mLot = "";		
		mType = "";
		mWalkLength = "";
		mLatitude = "";
		mLongitude = "";
	}
	
	public ParkingLot(String lot, String type, String latitude, String longitude, String walkLength){
		mLot = lot;
		mType = type;
		mLatitude = latitude;
		mLongitude = longitude;
		mWalkLength = walkLength;
	}
	
	public String getType(){
		return mType;
	}
	public String getLot(){
		return mLot;
	}
	public String getWalkLength(){
		return mWalkLength;
	}
	public String getLatitude(){
		return mLatitude;
	}
	public String getLongitude(){
		return mLongitude;
	}
}
