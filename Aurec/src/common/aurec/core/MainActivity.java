package common.aurec.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.ListView;
import android.widget.RemoteViews;

import common.aurec.R;
import common.aurec.models.TrackAudio;
import common.aurec.services.AudioRecordingService;

/**
 * 
 * @author Khanh
 * @brief Code Snippet for Adding Track into Adapter
 * 
 *        ArrayList<TrackListItem> tracks = new ArrayList<TrackListItem>();
 *        tracks.add(new TrackListItem("Song 1", "03:00", "08/08/2014", null,
 *        false )); tracks.add(new TrackListItem("Song 2", "04:00",
 *        "08/08/2014", null, false)); tracks.add(new TrackListItem("Song 3",
 *        "05:30", "08/08/2014", null, false)); tracks.add(new
 *        TrackListItem("Song 4", "09:00", "08/08/2014", null, true));
 *        tracks.add(new TrackListItem("Song 5", "13:30", "08/08/2014", null,
 *        false));
 * 
 *        TrackListViewAdapter lAdapter = new TrackListViewAdapter(this,
 *        tracks); list.setAdapter(lAdapter);
 */

public class MainActivity extends Activity {

	public static final String TRACK_OBJ = "track";

	private Boolean isRecording = false;
	private Boolean isPlaying = false;

	private Button recButton = null;
	private Button playButton = null;
	//private ListView list = null;

	private NotificationManager mNotificationMgr = null;
	private Intent mServiceIntent = null;

	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			showNotification(intent);
		}
	};

	private void initialize() {
		recButton = (Button) findViewById(R.id.record_button);
		playButton = (Button) findViewById(R.id.play_button);
		//list = (ListView) findViewById(R.id.list_test);

		mNotificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mServiceIntent = new Intent(this, AudioRecordingService.class);

		IntentFilter filter = new IntentFilter("aurec.RECORD_ACTION");
		LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, filter);
	}

	private void startRecord() {

		if (isRecording) {
			recButton.setText("Stop");
			playButton.setEnabled(false);

			Log.d("MainActivity", "Sending Recording");
			mServiceIntent.putExtra(AudioRecordingService.RECORDING_ACTION,
					"recording");
		} else {
			recButton.setText("Record");
			playButton.setEnabled(true);
			
			mServiceIntent.putExtra(AudioRecordingService.RECORDING_ACTION,
					"stop");
		}

		startService(mServiceIntent);
	}

	private void startPlay() {

		if (isPlaying) {
			playButton.setText("Stop");
			recButton.setEnabled(false);
		} else {
			playButton.setText("Play");
			recButton.setEnabled(true);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initialize();
		
		//region SetClickListener
		recButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				isRecording = !isRecording;
				startRecord();
			}
		});

		playButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				isPlaying = !isPlaying;
				startPlay();
			}
		});
		//endregion
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setTitle("Exit Confirmation")
				.setMessage("Are you sure you want to exit?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								mNotificationMgr.cancelAll();
								finish();
							}
						}).setNegativeButton("No", null).show();
	}

	@Override
	public void onDestroy() {

		LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
		super.onDestroy();
	}

	private void showNotification(Intent received) {
		TrackAudio obj = (TrackAudio) received.getExtras().get(TRACK_OBJ);

		if (obj != null) {
			RemoteViews notView = new RemoteViews(getPackageName(),	R.layout.remote_notification);
			notView.setTextViewText(R.id.rmtTrackName, obj.getName());
			notView.setTextViewText(R.id.rmtLength, obj.getLength());

			Notification notification = new NotificationCompat.Builder(getApplicationContext()).setContent(notView)
												.setSmallIcon(R.drawable.ic_launcher)
												.build();
			
			Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
			PendingIntent contentIntent = PendingIntent.getActivity( MainActivity.this, 0, notificationIntent, 0 );
			notification.contentIntent = contentIntent;
			notification.flags = Notification.FLAG_NO_CLEAR;

			mNotificationMgr.notify(10, notification);
		}
	}
}
