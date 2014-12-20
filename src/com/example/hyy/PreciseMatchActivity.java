package com.example.hyy;

import java.awt.image.BufferedImage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import cn.match.fastMatch;


public class PreciseMatchActivity extends Activity
{
	//private List<Map<String, Integer>> list=new ArrayList<Map<String,Integer>>();
	//private SimpleAdapter simpleAdapter=null;
	private GridView myGridView=null;
	//BufferedImage image=null;
	private int[] picRes= new int[9];
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_precisematch);
		
		//get intent data
				KdData data=(KdData)getIntent().getExtras().get("intent_object");
				picRes[0]=data.brand1;
				picRes[1]=data.brand2;
				picRes[2]=data.brand3;
				picRes[3]=data.brand4;
				picRes[4]=data.brand5;
				picRes[5]=data.brand6;
				picRes[6]=data.brand7;
				picRes[7]=data.brand8;
				picRes[8]=data.brand9;
				
		ImageView imageView=(ImageView)findViewById(R.id.ivOrig02);
		Bitmap bitmap=openfolder.bitmap;
		bitmap=refhtc.resizeBitmap(bitmap, openfolder.dm.widthPixels/4, openfolder.dm.heightPixels/4);
		imageView.setImageBitmap(bitmap);
		
		this.myGridView=(GridView)findViewById(R.id.gvPrecise01);
		this.myGridView.setAdapter(new ImageAdapter(this, this.picRes));			//set picture
		
		this.myGridView.setOnItemClickListener(new OnItemClickListenerImpl());
		
//		try 
//		{
//		String[] reStrings = (new fastMatch()).FastMatch(openfolder.bitmap, 1);	
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
	}
	
	private class OnItemClickListenerImpl implements OnItemClickListener 
	{
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) 
		{
//			ImageView showImg = new ImageView(PreciseMatchActivity.this);
//			showImg.setScaleType(ImageView.ScaleType.CENTER); // Õº∆¨æ”÷–œ‘ æ 
//			showImg.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//			showImg.setImageResource(PreciseMatchActivity.this.picRes[position]); // …Ë÷√œ‘ æÕº∆¨
			
			Intent intent = new Intent();
			intent.putExtra("Intent_int", picRes[position]);
			intent.setClass(PreciseMatchActivity.this, PicGalleryActivity.class);
			startActivity(intent);
			
			/*
			Dialog dialog = new AlertDialog.Builder(FastResultActivity.this)
						.setIcon(R.drawable.pic_m).setTitle("≤Èø¥Õº∆¨")
						.setView(showImg).setNegativeButton("πÿ±’", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						}).create();
				dialog.show() ;
				*/
				
			}

		}

	

}
