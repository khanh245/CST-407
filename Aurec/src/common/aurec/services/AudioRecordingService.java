package common.aurec.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class AudioRecordingService extends Service {

	public final static String TRACK_NAME = "name";
	public final static String TRACK_LENGTH = "length";
	public final static String TRACK_DATE = "date";
	public final static String TRACK_RECORDING = "isRecording";
	
	private LocalBroadcastManager mBroadcastMgr = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mBroadcastMgr = LocalBroadcastManager.getInstance(this);
		
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
	
	private void broadcast() {
		//TODO: need to figure out what to broadcast
	}
}
