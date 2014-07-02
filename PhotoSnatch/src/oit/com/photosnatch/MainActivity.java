package oit.com.photosnatch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	static final int REQUEST_IMAGE_CAPTURE = 1;
	
	static final String CAMERA_IMAGE_STORAGE 	= "imagebitmap";
	static final String CAMERA_IMAGE_VISIBILITY = "imagevisible"; 
	
	private Button mIntendButton = null;
	private ImageView mImageView = null;
	private Bitmap mBitmapFromCam = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mImageView = (ImageView)findViewById(R.id.mImage);
		mIntendButton = (Button) findViewById(R.id.btnIntend);
		
		mIntendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dispatchTakePictureIntent();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			mBitmapFromCam = (Bitmap) extras.get("data");
			mImageView.setImageBitmap(mBitmapFromCam);
		}
	}

	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle state)
	{
		state.putParcelable(CAMERA_IMAGE_STORAGE, mBitmapFromCam);
		state.putBoolean(CAMERA_IMAGE_VISIBILITY, (mImageView != null));
		super.onSaveInstanceState(state);
	}
	
	@Override
	protected void onRestoreInstanceState (Bundle state)
	{
		mBitmapFromCam = (Bitmap)state.getParcelable(CAMERA_IMAGE_STORAGE);
		mImageView.setImageBitmap(mBitmapFromCam);
		Boolean visible = (Boolean)state.getBoolean(CAMERA_IMAGE_VISIBILITY);
		if (visible) mImageView.setVisibility(0);
	}
}
