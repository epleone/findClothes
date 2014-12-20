package com.example.hyy;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import cn.sql.AssetsDatabaseManager;

//import cn.sql.DataBaseOpenHelper;
//import cn.sql.DbTrans;

import android.R.integer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	// private DataBaseOpenHelper dbOpenHelper;
	ImageButton btnFolder;
	ImageButton btnCamera;
	Intent intent;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AssetsDatabaseManager.initManager(getApplication());// 初始化，只需要调用一次
		btnFolder = (ImageButton) findViewById(R.id.btnFolder);
		btnCamera = (ImageButton) findViewById(R.id.btnCamera);
		// btnFolder.setText("选择图片");
		btnFolder.setOnClickListener(this);
		btnCamera.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.btnFolder:
			intent = new Intent();
			// 开启Pictures画面Type设定为image
			intent.setType("image/*");
			// 向另一个
			intent.setClass(MainActivity.this, openfolder.class);
			break;

		case R.id.btnCamera:
			intent = new Intent();
			// 开启Pictures画面Type设定为image
			intent.setType("image/*");
			// 向另一个
			intent.setClass(MainActivity.this, CameraActivity.class);
			break;
		}			
		startActivity(intent);
	}

}
