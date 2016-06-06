package com.example.soundcreator;

import android.support.v7.app.ActionBarActivity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
	Button press;
	EditText freq,delai;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		press=(Button) findViewById(R.id.button1);
		press.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				freq=(EditText) findViewById(R.id.freq);
				delai=(EditText) findViewById(R.id.delai);
				Log.v("Edit text >>>>>>>>>>>>>>>Delai     >>>>>>>>>>>>>>>>>", delai.getText().toString());
				Log.v("Edit text >>>>>>>>>>>>>>>Frequence >>>>>>>>>>>>>>>>>", freq.getText().toString());
				int f=Integer.parseInt(freq.getText().toString());
				for(int i=f;i<f+1000;i++){
					playSound(i, Integer.parseInt(delai.getText().toString()));
				}
			}
		});
	}
	private void playSound(double frequency, int duration) {
	    // AudioTrack definition
	    int mBufferSize = AudioTrack.getMinBufferSize(44100,
	    AudioFormat.CHANNEL_OUT_STEREO,
	    AudioFormat.ENCODING_PCM_8BIT);

	    AudioTrack mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
	    AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
	    mBufferSize, AudioTrack.MODE_STREAM);

	    // Sine wave
	    double[] mSound = new double[4410];
	    short[] mBuffer = new short[duration];
	    for (int i = 0; i < mSound.length; i++) {
	        mSound[i] = Math.sin(2.0*Math.PI * i/(44100/frequency));
	        mBuffer[i] = (short) (mSound[i]*Short.MAX_VALUE);
	    }

	    mAudioTrack.setStereoVolume(AudioTrack.getMaxVolume(), AudioTrack.getMaxVolume());
	    mAudioTrack.play();

	    mAudioTrack.write(mBuffer, 0, mSound.length);
	    mAudioTrack.stop();
	    mAudioTrack.release();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
