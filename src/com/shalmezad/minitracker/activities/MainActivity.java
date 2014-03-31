package com.shalmezad.minitracker.activities;

import com.shalmezad.minitracker.R;
import com.shalmezad.minitracker.music.Note;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class MainActivity extends Activity
{
	private final int STREAM_TYPE = AudioManager.STREAM_MUSIC;
	private final int SAMPLE_RATE_IN_HZ=14000;
	private final int CHANNEL_CONFIG= AudioFormat.CHANNEL_OUT_MONO;
	private final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_8BIT;
	private final int BUFFER_SIZE_IN_BYTES = 1000;
	private final int MODE = AudioTrack.MODE_STATIC;
	
	AudioTrack mAudioPlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupPiano();
		mAudioPlay = new AudioTrack(STREAM_TYPE, 
									SAMPLE_RATE_IN_HZ, 
									CHANNEL_CONFIG, 
									AUDIO_FORMAT, 
									BUFFER_SIZE_IN_BYTES, 
									MODE);
		//mAudioPlay.write(byte[] audioData, int offsetInBytes, in sizeInBytes);
	}
	
	private void setupPiano()
	{
		findViewById(R.id.key_c).setOnTouchListener(new PianoKeyListener(Note.C));
		findViewById(R.id.key_cs).setOnTouchListener(new PianoKeyListener(Note.Cs));
		findViewById(R.id.key_d).setOnTouchListener(new PianoKeyListener(Note.D));
		findViewById(R.id.key_ds).setOnTouchListener(new PianoKeyListener(Note.Ds));
		findViewById(R.id.key_e).setOnTouchListener(new PianoKeyListener(Note.E));
		findViewById(R.id.key_f).setOnTouchListener(new PianoKeyListener(Note.F));
		findViewById(R.id.key_fs).setOnTouchListener(new PianoKeyListener(Note.Fs));
		findViewById(R.id.key_g).setOnTouchListener(new PianoKeyListener(Note.G));
		findViewById(R.id.key_gs).setOnTouchListener(new PianoKeyListener(Note.Gs));
		findViewById(R.id.key_a).setOnTouchListener(new PianoKeyListener(Note.A));
		findViewById(R.id.key_as).setOnTouchListener(new PianoKeyListener(Note.As));
		findViewById(R.id.key_b).setOnTouchListener(new PianoKeyListener(Note.B));
	}
	
	private class PianoKeyListener implements OnTouchListener
	{
		Note key;
		public PianoKeyListener(Note key)
		{
			this.key = key;
		}
		@Override
		public boolean onTouch(View arg0, MotionEvent event) {
			//TODO: Do something on click. Don't know what yet...
			switch(event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				float freq = getFrequency(key);
				Log.i("PianoKeyListener", "Playing note: " + key);
				Log.i("PianoKeyListener", "With frequency: " + freq);
				playNote(freq);
				break;
			case MotionEvent.ACTION_UP:
				Log.i("PianoKeyListener", "Stopping note");
				mAudioPlay.stop();
				break;
			}
			return false;
		}
		
	}
	
	private float getFrequency(Note n)
	{
		switch(n)
		{
		case C:
			return 261.63f;
		case Cs:
			return 277.18f;
		case D:
			return 293.66f;
		case Ds:
			return 311.13f;
		case E:
			return 329.63f;
		case F:
			return 349.23f;
		case Fs:
			return 369.99f;
		case G:
			return 392.00f;
		case Gs:
			return 415.30f;
		case A:
			return 440.00f;
		case As:
			return 466.16f;
		case B:
			return 493.88f;
		default:
			break;
			
		}
		return 440.0f;
	}
	
	private void playNote(float frequency)
	{
		//So, we have our frequency.
		//Now it's a matter of filling the buffer.
		//We only need to fill one full wavelength.
		
		//We're going to work with square waves for now (how original...)
		//So, high 1/2, low 1/2, 
		float waveLengthSamples = SAMPLE_RATE_IN_HZ/frequency;
		//This will be in the ammount of samples
		byte[] samples = new byte[BUFFER_SIZE_IN_BYTES];
		for(int i=0; i<BUFFER_SIZE_IN_BYTES; i++)
		{
			if(Math.floor(i/waveLengthSamples/2.0)%2 == 0)
			{
				samples[i] = 0;
			}
			else
			{
				samples[i] = (byte) 255;
			}
		}
		mAudioPlay.write(samples, 0, BUFFER_SIZE_IN_BYTES);
		mAudioPlay.setLoopPoints(0, (int) waveLengthSamples, -1);
		mAudioPlay.play();
	}
}
