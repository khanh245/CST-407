package common.aurec.writers;

public class WavWriter implements ITrackWriter {

	private static final String TRACK_EXTENSION = ".wav";
	
	@Override
	public void writeToFile() {

	}
	
	@Override
	public String getExtension() {
		return TRACK_EXTENSION;
	}
}
