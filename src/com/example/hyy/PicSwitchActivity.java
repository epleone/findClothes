package com.example.hyy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class PicSwitchActivity extends Activity {
	private Button butPrevious = null;
	private Button butNext = null;
	private ImageSwitcher myImageSwitcher = null;
	private ImageView picImageView=null;
	private TextView tvBrand=null;
	private int[] imgRes = new int[] { R.drawable.jackjones_212408029_1, R.drawable.jackjones_212408029_2,
			R.drawable.jackjones_212408029_3, R.drawable.jackjones_212408029_4, R.drawable.jackjones_212408029_5 };
	private int foot = 0; // 表示当前已经显示的数组图片的脚标

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.picswitchactivity);
		this.myImageSwitcher = (ImageSwitcher) super
				.findViewById(R.id.myImageSwitcher);
		this.butPrevious = (Button) super.findViewById(R.id.butPrevious);
		this.butNext = (Button) super.findViewById(R.id.butNext);
		this.tvBrand=(TextView)super.findViewById(R.id.tvBrandName);
		this.picImageView = (ImageView)super.findViewById(R.id.ivPicSwitch);
		
		//set 
		tvBrand.setText("gadfafa");
		picImageView.setImageResource(R.drawable.jackjones_212408029_1);

		this.myImageSwitcher.setFactory(new ViewFactoryImpl());
		this.myImageSwitcher.setImageResource(this.imgRes[this.foot++]);

		this.myImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		
		this.myImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		
		this.butPrevious.setOnClickListener(new OnClickListenerPrevious());
		this.butNext.setOnClickListener(new OnClickListenerNext());
	}

	private class OnClickListenerPrevious implements OnClickListener {

		@Override
		public void onClick(View view) {
			PicSwitchActivity.this.myImageSwitcher
					.setImageResource(PicSwitchActivity.this.imgRes[PicSwitchActivity.this.foot--]);
			PicSwitchActivity.this.checkButEnable() ;
		}

	}

	private class OnClickListenerNext implements OnClickListener {

		@Override
		public void onClick(View view) {
			PicSwitchActivity.this.myImageSwitcher
					.setImageResource(PicSwitchActivity.this.imgRes[PicSwitchActivity.this.foot++]);
			PicSwitchActivity.this.checkButEnable() ;
		}

	}
	
	private void checkButEnable(){	// 判断按钮是否可用
		if(this.foot < this.imgRes.length - 1) {
			this.butNext.setEnabled(true) ;
		} else {
			this.butNext.setEnabled(false) ;
		}
		if(this.foot == 0){
			this.butPrevious.setEnabled(false) ;
		} else {
			this.butPrevious.setEnabled(true) ;
		}
	}

	private class ViewFactoryImpl implements ViewFactory {
		@Override
		public View makeView() {
			ImageView img = new ImageView(PicSwitchActivity.this);
			img.setBackgroundColor(0xFFFFFFFF); // 设置背景
			img.setScaleType(ImageView.ScaleType.CENTER);
			img.setLayoutParams(new ImageSwitcher.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)); // 定义组件
			return img;
		}

	}
}
