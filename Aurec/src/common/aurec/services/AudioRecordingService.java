package common.aurec.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AudioRecordingService extends Service {

	public final static String TRACK_NAME = "name";
	public final static String TRACK_LENGTH = "length";
	public final static String TRACK_DATE = "date";
	public final static String TRACK_RECORDING = "isRecording";
	
	@Override
	public IBinder onBind(Intent intent) { return null; }

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("AUDIO_RECORDING", "Service started.");
		
		return START_NOT_STICKY;
	}
}
