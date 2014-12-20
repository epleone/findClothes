package cn.match;

import java.util.Arrays;
import java.util.List;

import android.graphics.Bitmap;
import cn.point.FeaturePoint;
import cn.point.KDFeaturePoint;
import cn.point.RenderImage;
import cn.sift.SIFT;

public class GetFeature {
	private int[] index = {56,16,32,39,48,8,25,57,88,7,49,40,24,112,79,17,104,18,81,72};//前20个选中的特征向量
	private int[] indexA = {41,15,26,47,80,71,64,55,120,96,93,44,89,50,6,37,45,9,113,87};//次20个
	private List<FeaturePoint> fps = null;
	
	public GetFeature(Bitmap image){
		RenderImage ri1 = new RenderImage(image);
		SIFT sift1 = new SIFT(); 
		this.fps= sift1.detectFeatures(ri1.toPixelFloatArray());
	}
	
	/*
	 * 返回用于匹配的KD特征点,用于精确匹配
	 */
	public List<KDFeaturePoint> getKDP(Bitmap image){
		RenderImage ri1 = new RenderImage(image);
		SIFT sift1 = new SIFT(); 
		sift1.detectFeatures(ri1.toPixelFloatArray());  
		List<KDFeaturePoint> Or = sift1.getGlobalKDFeaturePoints();
		return Or;
	}
	
	/**
	 * 返回前20的特征编码
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public String[] getCode() throws Exception{
		float[] In = new float[20];
		String[] codes = new String[fps.size()];
		int i=0;
		for(FeaturePoint fp:fps){
			In = getFcode(fp.features);
			codes[i] = code.getCode(In);//得到所有的特征向量编码
			i++;
		}
		//进行从小到大排序
		Arrays.sort(codes);
		return codes;
	}
	
	/*
	 * 返回前20的特征向量
	 */
	private float[] getFcode(float[] p){
		float[] res = new float[20];
		for(int i=0;i<20;i++){
			res[i] = p[index[i]];
		}
		return res;
	}
	
	/**
	 * 返回次20的特征编码
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public String[] getAcode() throws Exception{
		float[] In = new float[20];
		String[] codes = new String[fps.size()];
		int i=0;
		for(FeaturePoint fp:fps){
			In = getAcode(fp.features);
			codes[i] = code.getCode(In);//得到所有的特征向量编码
			i++;
		}
		//进行从小到大排序
		Arrays.sort(codes);
		return codes;
	}
	
	private float[] getAcode(float[] p){
		float[] res = new float[20];
		for(int i=0;i<20;i++){
			res[i] = p[indexA[i]];
		}
		return res;
	}
}
