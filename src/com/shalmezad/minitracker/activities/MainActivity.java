package com.shalmezad.minitracker.activities;

import com.shalmezad.minitracker.R;
import com.shalmezad.minitracker.music.Note;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity
{

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupPiano();
	}
	
	private void setupPiano()
	{
		findViewById(R.id.key_c).setOnClickListener(new PianoKeyListener(Note.C));
		findViewById(R.id.key_cs).setOnClickListener(new PianoKeyListener(Note.Cs));
		findViewById(R.id.key_d).setOnClickListener(new PianoKeyListener(Note.D));
		findViewById(R.id.key_ds).setOnClickListener(new PianoKeyListener(Note.Ds));
		findViewById(R.id.key_e).setOnClickListener(new PianoKeyListener(Note.E));
		findViewById(R.id.key_f).setOnClickListener(new PianoKeyListener(Note.F));
		findViewById(R.id.key_fs).setOnClickListener(new PianoKeyListener(Note.Fs));
		findViewById(R.id.key_g).setOnClickListener(new PianoKeyListener(Note.G));
		findViewById(R.id.key_gs).setOnClickListener(new PianoKeyListener(Note.Gs));
		findViewById(R.id.key_a).setOnClickListener(new PianoKeyListener(Note.A));
		findViewById(R.id.key_as).setOnClickListener(new PianoKeyListener(Note.As));
		findViewById(R.id.key_b).setOnClickListener(new PianoKeyListener(Note.B));
	}
	
	private class PianoKeyListener implements OnClickListener
	{
		Note key;
		public PianoKeyListener(Note key)
		{
			this.key = key;
		}
		@Override
		public void onClick(View arg0) 
		{
			//TODO: Do something on click. Don't know what yet...
			Log.i("PianoKeyListener", "Playing note: " + key);
		}
		
	}
}
