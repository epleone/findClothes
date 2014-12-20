package com.example.hyy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class PicGalleryActivity extends Activity {
	private Gallery gallery = null;
	private SimpleAdapter simpleAdapter = null;
	private List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
	private ImageView ivLast = null;
	private TextView tvBrandName = null;
	
	//private Link link = null;
	private String brandName;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_picgallery);

		ivLast = (ImageView) findViewById(R.id.ivLast01);
		Bitmap bitmap = refhtc.resizeBitmap(openfolder.bitmap, 120, 160);
		ivLast.setImageBitmap(bitmap);

		// get intent
		int Id = getIntent().getExtras().getInt("Intent_int");

		// set textview
		tvBrandName = (TextView) findViewById(R.id.tvBrandNameBrand);
		brandName = refhtc.IdToBrand(Id);
		//String ddString=refhtc.IdToBrand(Id);
		tvBrandName.setText(refhtc.IdToSex(Id) +brandName);

		this.initAdapter();
		this.gallery = (Gallery) super.findViewById(R.id.myGallery);
		this.gallery.setAdapter(this.simpleAdapter);
		this.gallery.setOnItemClickListener(new OnItemClickListenerImpl());
	}

	private void initAdapter() 
	{
		Field[] fields = R.drawable.class.getDeclaredFields(); // 取得全部的属性
		for (int x = 0; x < fields.length; x++) {
			if (fields[x].getName().startsWith(brandName.toLowerCase())) 
			{ // 我们所需要的图片
				Map<String, Integer> map = new HashMap<String, Integer>();
				try 
				{
					map.put("img", fields[x].getInt(R.drawable.class)); // 必须定义好名称是img
				} 
				catch (Exception e) {
				}
				this.list.add(map);
			}
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,	R.layout.grid_layout, new String[] { "img" },
				new int[] { R.id.ivImg });
	}

	private class OnItemClickListenerImpl implements OnItemClickListener 
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) 
		{
			Toast.makeText(PicGalleryActivity.this, String.valueOf(position),
					Toast.LENGTH_SHORT).show();
		}
	}
}
