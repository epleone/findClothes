package com.example.hyy;


import java.io.FileNotFoundException;

import cn.match.GetFeature;
import cn.match.fastMatch;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class openfolder extends Activity {
	public static Bitmap bitmap = null;
	private ImageButton btnCu = null;
	private ImageButton btnFast = null;
	//private BufferedImage image = null;
	//private Link[] links9 = new Link[9];
	// Link[] links6 = new Link[6];
	// ������Ļ�ķֱ��ʱ���
	private fastMatch fmFastMatch =null;
	public static DisplayMetrics dm = null;
	//private matchRes resMatch = null;
	private String[] codes = null; //ǰ20
	private String[] Acode =null;//��20
	private Bitmap img =null;
//	private ProgressDialog pd;
//	private Handler handler = new Handler(){  
//		  
//        @Override  
//        public void handleMessage(Message msg) {  
//        	pd.dismiss();//�����ʧ
//        }};
//        
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		try {
			fmFastMatch = new fastMatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setContentView(R.layout.open_foler);
		
		btnCu = (ImageButton) this.findViewById(R.id.btnCU);
		btnFast = (ImageButton) this.findViewById(R.id.btnFast);
		
		btnCu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				new Thread(){ 
//					 public void run() {
//						 pd = ProgressDialog.show(openfolder.this, "���ڴ���", "��ȴ�......",true,false);
//					 }
//				}.start();
				
				try {
					String[] reStrings  = fmFastMatch.AMatch(Acode,codes);
					//handler.sendEmptyMessage(0); 
					Intent intent = new Intent();
					KdData data = new KdData();
					data.setBrand1(refhtc.BrandToId(reStrings[0]));
					data.setBrand2(refhtc.BrandToId(reStrings[1]));
					data.setBrand3(refhtc.BrandToId(reStrings[2]));
					data.setBrand4(refhtc.BrandToId(reStrings[3]));
					data.setBrand5(refhtc.BrandToId(reStrings[4]));
					data.setBrand6(refhtc.BrandToId(reStrings[5]));
					data.setBrand7(refhtc.BrandToId(reStrings[6]));
					data.setBrand8(refhtc.BrandToId(reStrings[7]));
					data.setBrand9(refhtc.BrandToId(reStrings[8]));
					
					intent.putExtra("intent_object", data);

					intent.setClass(openfolder.this, PreciseMatchActivity.class);
					startActivity(intent);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		});

		btnFast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				 //To FastMatch
				try {
					String[] reStrings = new String[9];
					reStrings = fmFastMatch.FastMatch(codes);// ���ٱ���ƥ�䣬ȡÿ������
					KdBrand brandData=new KdBrand();
								
					for(int i=0;i<9;i++){
						brandData.setBrand(i+1, reStrings[i]);
					}
				Intent intent = new Intent();
				KdData data = new KdData();

				data.setBrand1(refhtc.BrandToId(brandData.brand1));
				data.setBrand2(refhtc.BrandToId(brandData.brand2));
				data.setBrand3(refhtc.BrandToId(brandData.brand3));
				data.setBrand4(refhtc.BrandToId(brandData.brand4));
				data.setBrand5(refhtc.BrandToId(brandData.brand5));
				data.setBrand6(refhtc.BrandToId(brandData.brand6));
				data.setBrand7(refhtc.BrandToId(brandData.brand7));
				data.setBrand8(refhtc.BrandToId(brandData.brand8));
				data.setBrand9(refhtc.BrandToId(brandData.brand9));

				intent.putExtra("intent_object", data);
				intent.setClass(openfolder.this, FastResultActivity.class);
				startActivity(intent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});   
	        
		dm = new DisplayMetrics();
		super.getWindowManager().getDefaultDisplay().getMetrics(dm);
		//
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		// ����Pictures����Type�趨Ϊimage
		intent.setType("image/*");
		// ȡ����Ƭ�󷵻ر�����
		startActivityForResult(intent, 1);		
	}
	
	
	protected void InitF() {
		try {
			GetFeature gf= new GetFeature(img);
			codes = gf.getCode();
			Acode =gf.getAcode();
			//btnCu.setVisibility(View.VISIBLE);
			//btnFast.setVisibility(View.VISIBLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK) {
			Uri uri = data.getData();
			Log.e("Uri", uri.toString());
			ContentResolver crContentResolver = this.getContentResolver();

			try {
				bitmap = BitmapFactory.decodeStream(crContentResolver.openInputStream(uri));
				ImageView imageView = (ImageView) findViewById(R.id.ivFolder01);
				// ָ��ͼƬ��С
				Bitmap bitmapNorm = refhtc.resizeBitmap(bitmap,
						dm.heightPixels * 9 / 16, dm.heightPixels * 3 / 4);
				// ��Bitmap�趨��ImageView
				imageView.setImageBitmap(bitmapNorm);
				// Layout layout=(Layout)findViewById(R.id.layoutMan);
				// layout.
				img = refhtc.resizeB(bitmap, 320);
				
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				Log.e("Exception", e.getMessage(), e);
			}
			
			//��ȡ����
			InitF();
			

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
