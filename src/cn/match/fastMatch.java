package cn.match;

import java.util.ArrayList;
import java.util.Arrays;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.sql.AssetsDatabaseManager;


public class fastMatch {
	/*
	 * 快速匹配
	 */
	
	String[] brand = new String[25];//25个子品牌
	String[][] name =new String[25][10];//25个子品牌下的每一张照片
	//int[][] ps = new int[25][10];//每幅图片的特征点数量
	
	public fastMatch() throws Exception{
		this.Init();//初始化参数
	}
	/*
	 * 初始化参数
	 */
	public void Init() throws Exception {
		Init it = new Init();
		this.brand =it.getBrand();
		this.name = it.getName();
		//this.ps = it.getPs();
	}
	/**
	 * 快速匹配
	 * @param codes
	 * @return
	 * @throws Exception
	 */
	public String[] FastMatch(String[] codes) throws Exception{
		int[][] points = new int[25][10];
		//String[] codes = (new GetFeature()).getCode(image);
		for(int i=0;i<25;i++){
			for(int j=0;j<10;j++){
				if(name[i][j] == null)
					continue;
				ArrayList<String> sourceArrayList = getCode(name[i][j]);
				points[i][j] = matchs(codes, sourceArrayList);
			}
		}
		
		/*for(int i=0;i<25;i++){
			for(int j=0;j<10;j++){
				if(ps[i][j] !=0){
					points[i][j] = (int)((1*points[i][j])/ps[i][j]);
				}
			}
		}*/
		
		String[] reString = new String[9];
		int[] a = new int[9];
//		if(num == 3){
//			a = max83(points);
//		}else{
			a = max8(points);
		//}
		
		for(int i=0;i<9;i++){
			reString[i] = brand[a[i]];
			System.out.println("第"+(i+1)+"匹配的品牌为:"+reString[i]);
		}
		//double end2=System.currentTimeMillis();
		//System.out.println("匹配所用时间："+(end2-end1)+"ms");
		return reString;
	}
	/**
	 * 类精确匹配
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public String[] AMatch(String[] codesA,String[] codes) throws Exception{
		int[][] points = new int[25][10];
		
		//String[] codes = (new GetFeature()).getCode(image);
		//String[] codesA = (new GetFeature()).getAcode(image);
		
		for(int i=0;i<25;i++){
			for(int j=0;j<10;j++){
				if(name[i][j] == null)
					continue;
				ArrayList<String> sourceArrayList = getCode(name[i][j]);
				ArrayList<String> sou1 = getACode(name[i][j]);
				points[i][j] = matchs(codes, sourceArrayList);
				points[i][j] += matchs(codesA, sou1);
			}
		}
		
		String[] reString = new String[9];
		int[] a = new int[9];
		a = max8(points);
		for(int i=0;i<9;i++){
			reString[i] = brand[a[i]];
		}
		return reString;
	}
	
	/*
	 * 返回前五个最大的数的下标，每一类合起来匹配的时候返回前五
	 */
	private int[] max1(int[] p){
		int[] temp = new int[p.length];
		System.arraycopy(p,0,temp,0,temp.length);
		Arrays.sort(temp);
		int[] res = new int[9];
		int k=0;
		for(int j=temp.length-1;j>0;j--){
			for(int i=0;i<p.length;i++){
				if(temp[j] == p[i]){
					if(isHave(res, i))
						continue;
					res[k] = i;
					k++;
					if(k==9)
						return res;
				}
			}
		}
		return res;
	}
	
	/*
	 * 判断数组中是否有元素n；
	 */
	private boolean isHave(int[] p,int n){
		for(int s :p){
			if(s == n)
				return true;
		}
		return false;
	}
	
	/*
	 * 单个图片进行匹配的时候，返回的前8
	 */
	private int[] max8(int[][] p){
		int[] temp = new int[25];
		for(int i=0;i<25;i++){
			temp[i] = maxV(p[i]);
			//System.out.println("得分为："+temp[i]);
		}
		int[] res = new int[9];
		res = max1(temp);
		return res;
	}
	
	private int maxV(int[] p) {
		int[] temp = new int[p.length];
		System.arraycopy(p,0,temp,0,temp.length);
		Arrays.sort(temp);
		int a=0;
		for(int i=p.length-1;i>p.length-2;i--){
			a+=temp[i];
		}
		return a;
	}
	
	/*
	 * 单个图片进行匹配的时候，返回的前8，前三和的前八
	 */
	private int[] max83(int[][] p){
		int[] temp = new int[25];
		for(int i=0;i<25;i++){
			temp[i] = maxV3(p[i]);
			//System.out.println("得分为："+temp[i]);
		}
		int[] res = new int[8];
		res = max1(temp);
		return res;
	}
	
	/*
	 * 前三个的和
	 */
	private int maxV3(int[] p) {
		int[] temp = new int[p.length];
		System.arraycopy(p,0,temp,0,temp.length);
		Arrays.sort(temp);
		int a=0;
		for(int i=p.length-1;i>p.length-4;i--){
			a+=temp[i];
		}
		return a;
	}
	
	/*
	 * 从数据库获得特征编码，并排序
	 */
	private ArrayList<String> getCode(String name) throws Exception{
		ArrayList<String> reStrings = new ArrayList<String>();
		// 获取管理对象，因为数据库需要通过管理对象才能够获取  
		AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
		// 通过管理对象获取数据库  
		SQLiteDatabase db = mg.getDatabase("kdsift.db");
		Cursor c = db.query("Fcode", new String[]{"code"}, "name ='"+name+"'", null, null, null, "code");
		 while(c.moveToNext()){   
			 reStrings.add(c.getString(0));
		 }
		/*
		String sqlString = "select code from Fcode where name ='"+name+"' order by code";
		ResultSet rs = sqlite.getDateSet(sqlString);
		while(rs.next()){
			reStrings.add(rs.getString(1));
		}*/
		return reStrings;
	}
	
	
	/*
	 * 从数据库获得次特征编码，并排序
	 */
	private ArrayList<String> getACode(String name) throws Exception{
		ArrayList<String> reStrings = new ArrayList<String>();
		// 获取管理对象，因为数据库需要通过管理对象才能够获取  
		AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
		// 通过管理对象获取数据库  
		SQLiteDatabase db = mg.getDatabase("kdsift.db");
		Cursor c = db.query("Acode", new String[]{"code"}, "name ='"+name+"'", null, null, null, "code");
		 while(c.moveToNext()){   
			 reStrings.add(c.getString(0));
		 }
		return reStrings;
	}
	
	
	/*
	 * 特征匹配，返回分数
	 */
	private int matchs(String[] in,ArrayList<String> sou){
		int res =0;
		for(int i=0,j=0;i<in.length&&j<sou.size();){
			switch (in[i].compareTo(sou.get(j))) {
			case 0:
				res +=1;
				i++;
				j++;
				break;
			case 1:
				j++;
				break;
			case -1:
				i++;
				break;

			}
		}
		return res;
	}
}
