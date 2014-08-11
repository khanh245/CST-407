package common.aurec.models;

import common.aurec.writers.ITrackWriter;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

public class TrackAudio {
	
    public static final int BPP_16 = 16;
    public static final int BPP_24 = 24;
    public static final int BPP_32 = 32;
    
    public static final int SAMPLE_RATE_44K = 44100;
    public static final int SAMPLE_RATE_48K = 48000;
    
    public static final int CHANNELS_STEREO = AudioFormat.CHANNEL_IN_STEREO;
    public static final int CHANNELS_MONO = AudioFormat.CHANNEL_IN_MONO;
    
    public static final int AUDIO_ENCODING_8 = AudioFormat.ENCODING_PCM_8BIT;
    public static final int AUDIO_ENCODING_16 = AudioFormat.ENCODING_PCM_16BIT;   
    
	private AudioRecord mRecorder = null;
	private int bufferLength = 0;
	
	private int mSamplingRate = 0;
	private int mChannels = 0;
	private int mAudioFormat = 0;
	
	private ITrackWriter mWriter;
	
	public TrackAudio(int sampleRate, int channels, int audioFormat) {
		mSamplingRate = sampleRate;
		mChannels = channels;
		mAudioFormat = audioFormat;
		
		bufferLength = AudioRecord.getMinBufferSize(sampleRate, channels, audioFormat);
	}
	
	public void startRecording() {
		mRecorder = new AudioRecord(MediaRecorder.AudioSource.MIC, mSamplingRate, mChannels, mAudioFormat, bufferLength );
	}
}
