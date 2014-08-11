package common.aurec.core;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import common.aurec.R;
import common.aurec.services.AudioRecordingService;

public class NotificationRecordActivity extends Activity {
	
	public static final int RECORDING_NOTIFICATION = 0x10;
	
	private TextView txtRmtTrackName = null;
	private TextView txtRmtTrackLength = null;
	private Button btnRmtRecord = null;
	private Button btnRmtStop = null;
	
	private Boolean isRecording = false;
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		txtRmtTrackName = (TextView) findViewById (R.id.rmtTrackName);
		txtRmtTrackLength = (TextView) findViewById (R.id.rmtLength);
		btnRmtRecord = (Button) findViewById (R.id.btnNotifRec);
		btnRmtStop = (Button) findViewById (R.id.btnNotifStop);
		
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.cancelAll();
		
		Intent i = getIntent();
		txtRmtTrackName.setText(i.getStringExtra(AudioRecordingService.TRACK_NAME));
		txtRmtTrackLength.setText(i.getStringExtra(AudioRecordingService.TRACK_LENGTH));
		isRecording = i.getBooleanExtra(AudioRecordingService.TRACK_RECORDING, false);
		
		if (isRecording) {
			btnRmtRecord.setText("Pause");
			btnRmtRecord.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_action_pause),
											null, 
											null, 
											null);
		}
	}
}
