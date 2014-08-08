package common.aurec.core;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import common.aurec.R;
import common.aurec.models.TrackListItem;
import common.aurec.utils.TrackListViewAdapter;

public class MainActivity extends Activity {
	
	private Thread mThread = null;
	private MediaRecorder mRecorder = null;
	private Boolean isRecording = false;
	private Boolean isPlaying = false;
	
	private Button recButton = null;
	private Button playButton = null;
	private ListView list = null;
	
	private void initialize()
	{
		recButton = (Button)findViewById(R.id.record_button);
		playButton = (Button)findViewById(R.id.play_button);
		list = (ListView) findViewById(R.id.list_test);
		
		ArrayList<TrackListItem> tracks = new ArrayList<TrackListItem>();
		tracks.add(new TrackListItem("Song 1", "03:00", "08/08/2014", null, false ));
		tracks.add(new TrackListItem("Song 2", "04:00", "08/08/2014", null, false));
		tracks.add(new TrackListItem("Song 3", "05:30", "08/08/2014", null, false));
		tracks.add(new TrackListItem("Song 4", "09:00", "08/08/2014", null, true));
		tracks.add(new TrackListItem("Song 5", "13:30", "08/08/2014", null, false));
		
		TrackListViewAdapter lAdapter = new TrackListViewAdapter(this, tracks);
		list.setAdapter(lAdapter);
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
	
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
			.setTitle("Exit Confirmation")
			.setMessage("Are you sure you want to exit?")
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					finish();
				}
			})
			.setNegativeButton("No", null)
			.show();
	}
}
