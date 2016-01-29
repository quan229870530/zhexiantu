package com.example.articledetails;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		com.example.articledetails.CustomProgressBar link = (CustomProgressBar) findViewById(R.id.link);
		int position[][] = {{300,500},{600,300},{1000,1000},{2000,200}};
		CustomProgressBar.setOnPositionListener(position);
	}
}
