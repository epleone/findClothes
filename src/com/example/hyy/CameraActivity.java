package com.example.hyy;

import cn.match.GetFeature;
import cn.match.fastMatch;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CameraActivity extends Activity {

	private Button btnCamera = null;
	private ImageView imageView;
	private DisplayMetrics dm = null;
	private ImageButton btnCu = null;
	private ImageButton btnFast = null;
	private Bitmap bitmap =null;
	private String[] codes = null; // 前20
	private String[] Acode = null;// 后20
	private Bitmap img = null;
	private GetFeature gf;
	private fastMatch fmFastMatch = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_camera);

		//init
		this.btnCamera = (Button) super.findViewById(R.id.btnTakePic);
		imageView = (ImageView) findViewById(R.id.ivCamera02);
		dm = new DisplayMetrics();
		super.getWindowManager().getDefaultDisplay().getMetrics(dm);
		openfolder.dm = dm;
		btnCu = (ImageButton) this.findViewById(R.id.btnCU02);
		btnFast = (ImageButton) this.findViewById(R.id.btnFast02);
		//不可视
//		btnCu.setVisibility(View.INVISIBLE);
//		btnFast.setVisibility(View.INVISIBLE);
		
		try {
			fmFastMatch = new fastMatch();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			
		//拍照并保存图片
		btnCamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// initCamera();
				Intent openCameraIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(openCameraIntent, 1);
			}
		});
		
		btnCu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					String[] reStrings = fmFastMatch.AMatch(Acode, codes);

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

					intent.setClass(CameraActivity.this, PreciseMatchActivity.class);
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
				// To FastMatch
				try {
					String[] reStrings = new String[9];
					reStrings = fmFastMatch.FastMatch(codes);// 快速编码匹配，取每组最大的
					KdBrand brandData = new KdBrand();

					for (int i = 0; i < 9; i++) {
						brandData.setBrand(i + 1, reStrings[i]);
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

					intent.setClass(CameraActivity.this, FastResultActivity.class);
					startActivity(intent);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1 && resultCode == Activity.RESULT_OK
				&& null != data) {
			Bundle bundle = data.getExtras();
			// 获取相机返回的数据，
			bitmap = (Bitmap) bundle.get("data");
			openfolder.bitmap = bitmap;
			Bitmap bitmapNorm = refhtc.resizeBitmap(bitmap,
					dm.heightPixels * 9 / 16, dm.heightPixels * 3 / 4);
			imageView.setImageBitmap(bitmapNorm);
			img = refhtc.resizeB(bitmap, 240);
			//初始化
			try {
				GetFeature gf= new GetFeature(img);
				codes = gf.getCode();
				Acode =gf.getAcode();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			btnCu.setVisibility(View.VISIBLE);
			btnFast.setVisibility(View.VISIBLE);
			btnCamera.setVisibility(View.GONE);
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}
