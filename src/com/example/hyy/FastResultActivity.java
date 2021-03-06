package com.example.hyy;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.hyy.R.drawable;

import cn.match.fastMatch;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class FastResultActivity extends Activity
{
	//private List<Map<String, Integer>> list=new ArrayList<Map<String,Integer>>();
	//private SimpleAdapter simpleAdapter=null;
	//public static Link[] links= new Link[9];
	
	private GridView myGridView=null;
	//Bitmap image=null;
	//private Resources mResources=null;
	private int[] picRes= new int[9];
	private TextView tViewInfo=null;	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activit_fastmatch);
		tViewInfo=(TextView)findViewById(R.id.tvInfo);
		tViewInfo.setVisibility(View.INVISIBLE);
		
		
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
		
		
		
		
		ImageView imageView=(ImageView)findViewById(R.id.ivOrig01);
		Bitmap bitmap=openfolder.bitmap;
		bitmap=refhtc.resizeBitmap(bitmap, openfolder.dm.widthPixels/5, openfolder.dm.heightPixels/5);
		imageView.setImageBitmap(bitmap);
		
		this.myGridView=(GridView)findViewById(R.id.gvFast01);
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
//			ImageView showImg = new ImageView(FastResultActivity.this);
//			showImg.setScaleType(ImageView.ScaleType.CENTER); // ͼƬ������ʾ 
//			showImg.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//			showImg.setImageResource(FastResultActivity.this.picRes[position]); // ������ʾͼƬ
			
			Intent intent = new Intent();
			
//			Link link=new Link();
//			link=refhtc.IdToBrand(picRes[position]);
//			link.position=position;
			
			intent.putExtra("Intent_int", picRes[position]);				
			intent.setClass(FastResultActivity.this, PicGalleryActivity.class);
			startActivity(intent);
			
			/*
			Dialog dialog = new AlertDialog.Builder(FastResultActivity.this)
						.setIcon(R.drawable.pic_m).setTitle("�鿴ͼƬ")
						.setView(showImg).setNegativeButton("�ر�", new DialogInterface.OnClickListener() {
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
