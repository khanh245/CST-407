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
	
	private static TrackAudio mTrack = null;

	public AudioRecordingService() {
		super("AudioRecordingService");
	}
	
	private void broadcastNotification() {
		Intent i = new Intent("aurec.NOTIFICATION_ACTION").putExtra(MainActivity.NOTIFICATION_OBJ, mTrack);
		LocalBroadcastManager.getInstance(this).sendBroadcast(i);
	}
	
	private void broadcastTrack() {
		Intent i = new Intent("aurec.RECORD_ACTION").putExtra(MainActivity.TRACK_OBJ, mTrack);
		LocalBroadcastManager.getInstance(this).sendBroadcast(i);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String cmd = intent.getStringExtra(RECORDING_ACTION);
		Log.d("AUREC_RECORDING_SERVICE", cmd);
		
		if(cmd.equalsIgnoreCase("recording")) {
			mTrack = new TrackAudio(TrackAudio.SAMPLE_RATE_44K, TrackAudio.CHANNELS_STEREO, TrackAudio.AUDIO_ENCODING_16, TrackAudio.BPP_16);
			mTrack.setTrackWriter(WriterTypes.WAV_WRITER);
			mTrack.startRecording();	
			Log.d("NAME", mTrack.getName());
			Log.d("DATE", mTrack.getDate());
			broadcastNotification();
		}
		else if (cmd.equalsIgnoreCase("stop")) {
			mTrack.stopRecording();			
			broadcastTrack();
		}
	}
}
