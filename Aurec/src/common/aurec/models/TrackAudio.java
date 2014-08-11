package common.aurec.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import common.aurec.writers.TrackWriter;
import common.aurec.writers.WavWriter;
import common.aurec.writers.WriterTypes;

public class TrackAudio implements Parcelable{
	
    public static final int BPP_16 = 16;
    public static final int BPP_24 = 24;
    public static final int BPP_32 = 32;
    
    public static final int SAMPLE_RATE_44K = 44100;
    public static final int SAMPLE_RATE_48K = 48000;
    
    public static final int CHANNELS_STEREO = AudioFormat.CHANNEL_IN_STEREO;
    public static final int CHANNELS_MONO = AudioFormat.CHANNEL_IN_MONO;
    
    public static final int AUDIO_ENCODING_8 = AudioFormat.ENCODING_PCM_8BIT;
    public static final int AUDIO_ENCODING_16 = AudioFormat.ENCODING_PCM_16BIT;     
    
    /// FOR RECORDING PURPOSES
	private AudioRecord mRecorder = null;
	private boolean isRecording = false;
	private Thread mRecordingThread = null;
	private int mSamplingRate = 0;
	private int mChannels = 0;
	private int mAudioFormat = 0;
	private int mBufferSize = 0;
	
	/// AUDIO TRACK PROPERTY
	private String mName = null;
	private String mLength = null;
	private String mDate = null;
	private byte[] mBuffer = null;

	/// EXTENSION WRITER
	private TrackWriter mWriter = null;
	
	public TrackAudio(int sampleRate, int channels, int audioFormat) {
		mSamplingRate = sampleRate;
		mChannels = channels;
		mAudioFormat = audioFormat;
		
		mBufferSize = AudioRecord.getMinBufferSize(mSamplingRate, mChannels, mAudioFormat);
	}
	
	public TrackAudio(Parcel in) {
		mName = in.readString();
		mLength = in.readString();
		mDate = in.readString();
		in.readByteArray(mBuffer);
	}

	public void startRecording() {
		mRecorder = new AudioRecord(MediaRecorder.AudioSource.MIC, mSamplingRate, mChannels, mAudioFormat, mBufferSize);
		mRecorder.startRecording();
		isRecording = true;

		mRecordingThread = new Thread(new Runnable() {

			@Override
			public void run() {
				writeAudioDataToFile();
			}
		}, "AudioRecorder Thread");

		mRecordingThread.start();
	}
	
	public void stopRecording() {
        if(null != mRecorder){
            isRecording = false;
           
            mRecorder.stop();
            mRecorder.release();
           
            mRecorder = null;
            mRecordingThread = null;
        }
   
        mWriter.writeToFile(this);
	}
	
	public void setTrackWriter(WriterTypes wt) {
		switch(wt)
		{
		case WAV_WRITER:
			mWriter = new WavWriter();
			break;
		case DEFAULT_WRITER:
		default:
			// TODO: to be determined
			break;
		}
	}
	
	private void writeAudioDataToFile() {
		byte data[] = new byte[mBufferSize];
		String filename = mWriter.getTempFilename();
		FileOutputStream os = null;

		try {
			os = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			Log.e("RECORDING_ERROR", "FILE NOT FOUND!!");
			e.printStackTrace();
		}

		int read = 0;

		if (null != os) {
			while (isRecording) {
				read = mRecorder.read(data, 0, mBufferSize);

				if (AudioRecord.ERROR_INVALID_OPERATION != read) {
					try {
						os.write(data);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	

	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mName);
		dest.writeString(mLength);
		dest.writeString(mDate);
		dest.writeByteArray(mBuffer);
	}
	
    public static final Parcelable.Creator<TrackAudio> CREATOR = new Parcelable.Creator<TrackAudio>() {
        public TrackAudio createFromParcel(Parcel in) {
            return new TrackAudio(in);
        }

        public TrackAudio[] newArray(int size) {
            return new TrackAudio[size];
        }
    };
    
    // Getters and Setters
	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public String getLength() {
		return mLength;
	}

	public void setLength(String mLength) {
		this.mLength = mLength;
	}
	
	public String getDate() {
		return mDate;
	}
	
	public void setDate(String date) {
		mDate = date;
	}

	public byte[] getBuffer() {
		return mBuffer;
	}

	public void setBuffer(byte[] mBuffer) {
		this.mBuffer = mBuffer;
	}    
	
	public int getBufferSize() {
		return mBufferSize;
	}
}
