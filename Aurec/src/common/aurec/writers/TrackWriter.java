package common.aurec.writers;

import java.io.File;

import android.os.Environment;
import common.aurec.models.TrackAudio;

public abstract class TrackWriter {	
	
	protected static String AUREC_FOLDER = "Aurec";
	protected static String AUREC_TEMP_FILE = "record_temp.raw";   
	
	public TrackWriter() {
		
	}
	
	protected String getFilename() {
		String filepath = Environment.getExternalStorageDirectory().getPath();
		File file = new File(filepath, AUREC_FOLDER);

		if (!file.exists()) {
			file.mkdirs();
		}

		return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + getFileExtension());
	}

	protected void deleteTempFile() {
		File file = new File(getTempFilename());

		file.delete();
	}
	
	/**
	 * @brief Point to location of temp audio file
	 * @return The Location of Temp File
	 */
	public String getTempFilename() {
		String filepath = Environment.getExternalStorageDirectory().getPath();
		File file = new File(filepath, AUREC_FOLDER);

		if (!file.exists()) {
			file.mkdirs();
		}

		File tempFile = new File(filepath, AUREC_TEMP_FILE);

		if (tempFile.exists())
			tempFile.delete();

		return (file.getAbsolutePath() + "/" + AUREC_TEMP_FILE);
	}
	
    public abstract void writeToFile(TrackAudio obj);
    public abstract String getFileExtension();
}
