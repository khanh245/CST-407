package common.aurec.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import common.aurec.core.MainActivity;
import common.aurec.models.TrackAudio;
import common.aurec.writers.WriterTypes;

public class AudioRecordingService extends IntentService {

	public final static String RECORDING_ACTION = "recording";
	
	private TrackAudio mTrack = null;

	public AudioRecordingService() {
		super("AudioRecordingService");
	}
	
	/* Old Service Implementation
	public final static String TRACK_NAME = "name";
	public final static String TRACK_LENGTH = "length";
	public final static String TRACK_DATE = "date";
	public final static String TRACK_RECORDING = "isRecording";
	
	// OLD SERVICE IMPLEMENTATION
	@Override
	public void onCreate() {
		super.onCreate();
		mTrack = new TrackAudio(TrackAudio.SAMPLE_RATE_44K, TrackAudio.CHANNELS_STEREO, TrackAudio.AUDIO_ENCODING_16);
	}
	
	@Override
	public void onDestroy() {
		this.stopSelf();
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent) { return null; }
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("AUDIO_RECORDING", "Service started.");
		
		return START_NOT_STICKY;
	}
	*/
	
	private void broadcast() {
		Intent i = new Intent("aurec.RECORD_ACTION").putExtra(MainActivity.TRACK_OBJ, mTrack);
		LocalBroadcastManager.getInstance(this).sendBroadcast(i);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String cmd = intent.getStringExtra(RECORDING_ACTION);
		Log.d("AUREC_RECORDING_SERVICE", cmd);
		
		if(cmd.equalsIgnoreCase("recording")) {
			mTrack = new TrackAudio(TrackAudio.SAMPLE_RATE_44K, TrackAudio.CHANNELS_STEREO, TrackAudio.AUDIO_ENCODING_16);
			mTrack.setTrackWriter(WriterTypes.WAV_WRITER);
			mTrack.startRecording();			
			broadcast();
		}
		else if (cmd.equalsIgnoreCase("stop")) {
			mTrack = new TrackAudio(TrackAudio.SAMPLE_RATE_44K, TrackAudio.CHANNELS_STEREO, TrackAudio.AUDIO_ENCODING_16);
			mTrack.setTrackWriter(WriterTypes.WAV_WRITER);
			mTrack.stopRecording();			
			broadcast();
		}
	}
}
