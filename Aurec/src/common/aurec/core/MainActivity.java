package common.aurec.core;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import common.aurec.R;

public class MainActivity extends Activity {
	
	private Thread mThread = null;
	private MediaRecorder mRecorder = null;
	private Boolean isRecording = false;
	private Boolean isPlaying = false;
	
	private Button recButton = null;
	private Button playButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initialize();
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
	}
	
	private void initialize()
	{
		recButton = (Button)findViewById(R.id.record_button);
		playButton = (Button)findViewById(R.id.play_button);
	}	
	
	private void startRecord() {
		
		if (isRecording) {
			recButton.setText("Stop");
			playButton.setEnabled(false);
		}
		else {
			recButton.setText("Record");
			playButton.setEnabled(true);
		}
		
	}
	
	private void startPlay() {
		
		if (isPlaying) {
			playButton.setText("Stop");
			recButton.setEnabled(false);
		}
		else {
			playButton.setText("Play");
			recButton.setEnabled(true);
		}
	}
}