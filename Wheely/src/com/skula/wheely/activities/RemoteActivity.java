package com.skula.wheely.activities;

import android.app.Activity;
import android.os.Bundle;

import com.skula.wheely.activities.views.RemoteView;

public class RemoteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(new RemoteView(this));
	}

}
