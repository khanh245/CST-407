package common.aurec.models;

public class TrackListItem {
	
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
}
