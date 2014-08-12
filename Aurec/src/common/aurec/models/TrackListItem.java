package common.aurec.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TrackListItem implements Parcelable {
	
	private String TrackName = "";
	private String TrackLength = "";
	private String DateRecorded = "";
	private byte[] Buffer = null;
	private Boolean Playing = false;
	
	public TrackListItem (String name, byte[] data) {
		TrackName = name;
		Buffer = data;
	}

	public TrackListItem (String name, byte[] data, Boolean play) {
		TrackName = name;
		Buffer = data;
		Playing = play;
	}
	
	public TrackListItem (String name, String length, String date, byte[] data, Boolean play) {
		TrackName = name;
		TrackLength = length;
		DateRecorded = date;
		Buffer = data;
		Playing = play;
	}
	
	public TrackListItem(Parcel in) {
		TrackName = in.readString();
		TrackLength = in.readString();
		DateRecorded = in.readString();
		in.readByteArray(Buffer);
	}

	public String getTrackName() {
		return TrackName;
	}
	
	public void setTrackName(String trackName) {
		TrackName = trackName;
	}
	
	public String getTrackLength() {
		return TrackLength;
	}
	
	public void setTrackLength(String trackLength) {
		TrackLength = trackLength;
	}
	
	public String getDateRecorded() {
		return DateRecorded;
	}
	
	public void setDateRecorded(String dateRecorded) {
		DateRecorded = dateRecorded;
	}
	
	public byte[] getBuffer() {
		return Buffer;
	}
	
	public void setBuffer(byte[] data) {
		this.Buffer = data;
	}
	
	public Boolean isPlaying() {
		return Playing;
	}
	
	public void setPlaying(Boolean play) {
		Playing = play;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(TrackName);
		out.writeString(TrackLength);
		out.writeString(DateRecorded);
		out.writeByteArray(Buffer);
	}
	
    public static final Parcelable.Creator<TrackListItem> CREATOR = new Parcelable.Creator<TrackListItem>() {
        public TrackListItem createFromParcel(Parcel in) {
            return new TrackListItem(in);
        }

        public TrackListItem[] newArray(int size) {
            return new TrackListItem[size];
        }
    };	
}
