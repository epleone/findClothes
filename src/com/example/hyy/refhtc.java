package com.example.hyy;

import java.io.File;
import java.lang.reflect.Field;

import javax.security.auth.PrivateCredentialPermission;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3c.dom.stylesheets.LinkStyle;

import android.R.integer;
import android.R.string;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class refhtc {

	public class Link {
		public String brand;
		public int ID;
		public String sex;
		public int position;
	}
	
	public static String IdToSex(int id)
	{
		String brandString = "";
		String[] linkBrand = new String[25];
		int[] linksID = new int[25];
		
		linkBrand[0] = "女装";
		linksID[0] = R.drawable.hyy_01;
		linkBrand[1] = "女装";
		linksID[1] = R.drawable.hyy_02;
		linkBrand[2] = "男装";
		linksID[2] = R.drawable.hyy_03;
		linkBrand[3] = "男装";
		linksID[3] = R.drawable.hyy_04;
		linkBrand[4] = "男装";
		linksID[4] = R.drawable.hyy_05;
		linkBrand[5] = "男装";
		linksID[5] = R.drawable.hyy_06;
		linkBrand[6] = "男装";
		linksID[6] = R.drawable.hyy_07;
		linkBrand[7] = "男装";
		linksID[7] = R.drawable.hyy_08;
		linkBrand[8] = "女装";
		linksID[8] = R.drawable.hyy_09;
		linkBrand[9] = "女装";
		linksID[9] = R.drawable.hyy_10;
		linkBrand[10] = "女装";
		linksID[10] = R.drawable.hyy_11;
		linkBrand[11] = "女装";
		linksID[11] = R.drawable.hyy_12;
		linkBrand[12] = "男装";
		linksID[12] = R.drawable.hyy_13;
		linkBrand[13] = "男装";
		linksID[13] = R.drawable.hyy_14;
		linkBrand[14] = "男装";
		linksID[14] = R.drawable.hyy_15;
		linkBrand[15] = "女装";
		linksID[15] = R.drawable.hyy_16;
		linkBrand[16]= "女装";
		linksID[16] = R.drawable.hyy_17;
		linkBrand[17] = "女装";
		linksID[17] = R.drawable.hyy_18;
		linkBrand[18] = "女装";
		linksID[18] = R.drawable.hyy_19;
		linkBrand[19] = "男装";
		linksID[19] = R.drawable.hyy_20;
		linkBrand[20] = "男装";
		linksID[20] = R.drawable.hyy_21;
		linkBrand[21] = "男装";
		linksID[21] = R.drawable.hyy_22;
		linkBrand[22] = "男装";
		linksID[22] = R.drawable.hyy_23;
		linkBrand[23] = "男装";
		linksID[23] = R.drawable.hyy_24;
		linkBrand[24] = "男装";
		linksID[24] = R.drawable.hyy_25;

		for (int i = 0; i < 25; i++) {
			if (linksID[i] == id) {
				brandString = linkBrand[i];
				// break;
			}
		}
		return brandString;
	}

	public static String IdToBrand(int id) 
	{
		String brandString = "basichouse_HMCA723A";
		String[] linkBrand = new String[25];
		int[] linksID = new int[25];
		
		linkBrand[0] = "basichouse_HMCA723A";
		linksID[0] = R.drawable.hyy_01;
		linkBrand[1] = "basichouse_HMJP226C";
		linksID[1] = R.drawable.hyy_02;
		linkBrand[2] = "jackjones_212408029";
		linksID[2] = R.drawable.hyy_03;
		linkBrand[3] = "jackjones_212427006";
		linksID[3] = R.drawable.hyy_04;
		linkBrand[4] = "jackjones_212427017";
		linksID[4] = R.drawable.hyy_05;
		linkBrand[5] = "jackjones_212427019";
		linksID[5] = R.drawable.hyy_06;
		linkBrand[6] = "jackjones_212427021";
		linksID[6] = R.drawable.hyy_07;
		linkBrand[7] = "jackjones_213427006";
		linksID[7] = R.drawable.hyy_08;
		linkBrand[8] = "only_113327003";
		linksID[8] = R.drawable.hyy_09;
		linkBrand[9] = "only_113327005";
		linksID[9] = R.drawable.hyy_10;
		linkBrand[10] = "only_113327021";
		linksID[10] = R.drawable.hyy_11;
		linkBrand[11] = "only_113427003";
		linksID[11] = R.drawable.hyy_12;
		linkBrand[12] = "selected_412427014";
		linksID[12] = R.drawable.hyy_13;
		linkBrand[13] = "selected_412427040";
		linksID[13] = R.drawable.hyy_14;
		linkBrand[14] = "selected_412427048";
		linksID[14] = R.drawable.hyy_15;
		linkBrand[15] = "veromode_312427014";
		linksID[15] = R.drawable.hyy_16;
		linkBrand[16]= "veromode_313327003";
		linksID[16] = R.drawable.hyy_17;
		linkBrand[17] = "veromode_313427011";
		linksID[17] = R.drawable.hyy_18;
		linkBrand[18] = "veromode_313427012";
		linksID[18] = R.drawable.hyy_19;
		linkBrand[19] = "zara_0693_329";
		linksID[19] = R.drawable.hyy_20;
		linkBrand[20] = "zara_0706_304";
		linksID[20] = R.drawable.hyy_21;
		linkBrand[21] = "zara_0706_319";
		linksID[21] = R.drawable.hyy_22;
		linkBrand[22] = "zara_0706_326";
		linksID[22] = R.drawable.hyy_23;
		linkBrand[23] = "zara_1608_303";
		linksID[23] = R.drawable.hyy_24;
		linkBrand[24] = "zara_6096_451";
		linksID[24] = R.drawable.hyy_25;

		for (int i = 0; i < 25; i++) {
			if (linksID[i] == id) {
				brandString = linkBrand[i];
				// break;
			}
		}
		return brandString;

	}
	
	public static int BrandToId(String brandString) 
	{
		int intId=0;
		String[] linkBrand = new String[25];
		int[] linksID = new int[25];
		
		linkBrand[0] = "HMCA723A";
		linksID[0] = R.drawable.hyy_01;
		linkBrand[1] = "HMJP226C";
		linksID[1] = R.drawable.hyy_02;
		linkBrand[2] = "212408029";
		linksID[2] = R.drawable.hyy_03;
		linkBrand[3] = "212427006";
		linksID[3] = R.drawable.hyy_04;
		linkBrand[4] = "212427017";
		linksID[4] = R.drawable.hyy_05;
		linkBrand[5] = "212427019";
		linksID[5] = R.drawable.hyy_06;
		linkBrand[6] = "212427021";
		linksID[6] = R.drawable.hyy_07;
		linkBrand[7] = "213427006";
		linksID[7] = R.drawable.hyy_08;
		linkBrand[8] = "113327003";
		linksID[8] = R.drawable.hyy_09;
		linkBrand[9] = "113327005";
		linksID[9] = R.drawable.hyy_10;
		linkBrand[10] = "113327021";
		linksID[10] = R.drawable.hyy_11;
		linkBrand[11] = "113427003";
		linksID[11] = R.drawable.hyy_12;
		linkBrand[12] = "412427014";
		linksID[12] = R.drawable.hyy_13;
		linkBrand[13] = "412427040";
		linksID[13] = R.drawable.hyy_14;
		linkBrand[14] = "412427048";
		linksID[14] = R.drawable.hyy_15;
		linkBrand[15] = "312427014";
		linksID[15] = R.drawable.hyy_16;
		linkBrand[16]= "313327003";
		linksID[16] = R.drawable.hyy_17;
		linkBrand[17] = "313427011";
		linksID[17] = R.drawable.hyy_18;
		linkBrand[18] = "313427012";
		linksID[18] = R.drawable.hyy_19;
		linkBrand[19] = "0693_329";
		linksID[19] = R.drawable.hyy_20;
		linkBrand[20] = "0706_304";
		linksID[20] = R.drawable.hyy_21;
		linkBrand[21] = "0706_319";
		linksID[21] = R.drawable.hyy_22;
		linkBrand[22] = "0706_326";
		linksID[22] = R.drawable.hyy_23;
		linkBrand[23] = "1608_303";
		linksID[23] = R.drawable.hyy_24;
		linkBrand[24] = "6096_451";
		linksID[24] = R.drawable.hyy_25;

		for (int i = 0; i < 25; i++) {
			if (linkBrand[i].equals(brandString)) {
				intId = linksID[i];
				// break;
			}
		}
		return intId;

	}

	public static Bitmap[] getBitmapsFromPath(String name, String num) {
		String filepath = "/sdcard/Hyy_Jpg/";
		StringBuilder sbPath = new StringBuilder();
		int n = Integer.parseInt(num);
		Bitmap[] bitmaps = new Bitmap[n];
		sbPath.append("/sdcard/Hyy_Jpg/");
		// Field[] field=
		File file = new File(filepath);
		File[] files = null;
		if (file.getName().startsWith("veromode_313427011_")) {
			for (int i = 0; i < n; i++) {
				sbPath.append(files[i].getName());
				bitmaps[i] = BitmapFactory.decodeFile(files[i].getName());
			}
		}

		return bitmaps;
	}

	public static Drawable resizeImage(Bitmap bitmap, int w, int h) {
		// load the origial Bitmap
		Bitmap BitmapOrg = bitmap;

		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;

		// calculate the scale
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the Bitmap
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(45);

		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
				height, matrix, true);

		// make a Drawable from Bitmap to allow to set the Bitmap
		// to the ImageView, ImageButton or what ever
		return new BitmapDrawable(resizedBitmap);
	}

	public static Bitmap resizeBitmap(Bitmap bitmap, int w, int h) {
		// load the origial Bitmap
		Bitmap BitmapOrg = bitmap;

		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;

		// calculate the scale
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the Bitmap
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(45);

		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
				height, matrix, true);

		// make a Drawable from Bitmap to allow to set the Bitmap
		// to the ImageView, ImageButton or what ever
		return resizedBitmap;
	}
	
	
	public static Bitmap resizeB(Bitmap bitmap,int h) {
		// load the origial Bitmap
		Bitmap BitmapOrg = bitmap;

		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = width*h/height;
		int newHeight = h;

		// calculate the scale
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the Bitmap
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(45);

		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
				height, matrix, true);

		// make a Drawable from Bitmap to allow to set the Bitmap
		// to the ImageView, ImageButton or what ever
		return resizedBitmap;
	}

}
