package cn.match;

import java.util.ArrayList;
import java.util.Arrays;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.sql.AssetsDatabaseManager;


public class fastMatch {
	/*
	 * ����ƥ��
	 */
	
	String[] brand = new String[25];//25����Ʒ��
	String[][] name =new String[25][10];//25����Ʒ���µ�ÿһ����Ƭ
	//int[][] ps = new int[25][10];//ÿ��ͼƬ������������
	
	public fastMatch() throws Exception{
		this.Init();//��ʼ������
	}
	/*
	 * ��ʼ������
	 */
	public void Init() throws Exception {
		Init it = new Init();
		this.brand =it.getBrand();
		this.name = it.getName();
		//this.ps = it.getPs();
	}
	/**
	 * ����ƥ��
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
			System.out.println("��"+(i+1)+"ƥ���Ʒ��Ϊ:"+reString[i]);
		}
		//double end2=System.currentTimeMillis();
		//System.out.println("ƥ������ʱ�䣺"+(end2-end1)+"ms");
		return reString;
	}
	/**
	 * �ྫȷƥ��
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
	 * ����ǰ������������±꣬ÿһ�������ƥ���ʱ�򷵻�ǰ��
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
	 * �ж��������Ƿ���Ԫ��n��
	 */
	private boolean isHave(int[] p,int n){
		for(int s :p){
			if(s == n)
				return true;
		}
		return false;
	}
	
	/*
	 * ����ͼƬ����ƥ���ʱ�򣬷��ص�ǰ8
	 */
	private int[] max8(int[][] p){
		int[] temp = new int[25];
		for(int i=0;i<25;i++){
			temp[i] = maxV(p[i]);
			//System.out.println("�÷�Ϊ��"+temp[i]);
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
	 * ����ͼƬ����ƥ���ʱ�򣬷��ص�ǰ8��ǰ���͵�ǰ��
	 */
	private int[] max83(int[][] p){
		int[] temp = new int[25];
		for(int i=0;i<25;i++){
			temp[i] = maxV3(p[i]);
			//System.out.println("�÷�Ϊ��"+temp[i]);
		}
		int[] res = new int[8];
		res = max1(temp);
		return res;
	}
	
	/*
	 * ǰ�����ĺ�
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
	 * �����ݿ����������룬������
	 */
	private ArrayList<String> getCode(String name) throws Exception{
		ArrayList<String> reStrings = new ArrayList<String>();
		// ��ȡ���������Ϊ���ݿ���Ҫͨ�����������ܹ���ȡ  
		AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
		// ͨ����������ȡ���ݿ�  
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
	 * �����ݿ��ô��������룬������
	 */
	private ArrayList<String> getACode(String name) throws Exception{
		ArrayList<String> reStrings = new ArrayList<String>();
		// ��ȡ���������Ϊ���ݿ���Ҫͨ�����������ܹ���ȡ  
		AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
		// ͨ����������ȡ���ݿ�  
		SQLiteDatabase db = mg.getDatabase("kdsift.db");
		Cursor c = db.query("Acode", new String[]{"code"}, "name ='"+name+"'", null, null, null, "code");
		 while(c.moveToNext()){   
			 reStrings.add(c.getString(0));
		 }
		return reStrings;
	}
	
	
	/*
	 * ����ƥ�䣬���ط���
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
