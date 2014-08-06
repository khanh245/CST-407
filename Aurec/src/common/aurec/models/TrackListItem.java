package common.aurec.models;

public class TrackListItem {
	private String TrackName;
	private String TrackLength;
	private String DateRecorded;
	private byte[] Buffer;
	
	public TrackListItem (String name, byte[] data) {
		TrackName = name;
		Buffer = data;
	}

	public TrackListItem (String name, String length, String date, byte[] data) {
		TrackName = name;
		TrackLength = length;
		DateRecorded = date;
		Buffer = data;
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
}
