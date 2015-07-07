package edu.rangersgameday;

public class ParkingLot {
	// {Stadium, Cash, Reserved}
	private String mType;
	//{A, B, C, ... , M, N}
	private String mLot;
	private String mLatitude;
	private String mLongitude;
	//walk length in minutes
	private String mWalkLength;
	
	
	public ParkingLot(){
		mLot = "";		
		mType = "";
		mLatitude = "";
		mLongitude = "";
		mWalkLength = "";
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
