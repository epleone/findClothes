package cn.match;

import cn.sql.AssetsDatabaseManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * ����ݿ��ʼ������
 */


public class Init {
	String[] brand = new String[25];//25����Ʒ��
	String[][] name =new String[25][10];//25����Ʒ���µ�ÿһ����Ƭ
	//int[][] ps = new int[25][10];//ÿ��ͼƬ������������
	
	public Init() throws Exception{
		// ��ȡ���������Ϊ��ݿ���Ҫͨ����������ܹ���ȡ  
		AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
		// ͨ���������ȡ��ݿ�  
		SQLiteDatabase db = mg.getDatabase("kdsift.db");
		Cursor c = db.query("PicName", new String[]{"brand"}, null, null, "brand", null, null);
		int i=0;
		 while(c.moveToNext()){   
			 this.brand[i] = c.getString(0);
			 i++;
		 }
		 /*
		String sqlString = "select brand from PicName group by brand";
		ResultSet rs = sqlite.getDateSet(sqlString);
		int i=0;
		while (rs.next()){
				this.brand[i]=new String(rs.getString(1));
				i++;  
		}*/
		
		i=0;
		
		for(String subBrand:this.brand){
			c = db.query("PicName", new String[]{"name"}, "brand = '"+subBrand+"'", null, null, null, null);
			int j=0;
			while(c.moveToNext()){   
				this.name[i][j] = c.getString(0);
				 j++;
			 }
			i++;
			/*
			sqlString ="select name from picName where brand ='"+subBrand+"'";
			rs = sqlite.getDateSet(sqlString);
			int j=0;
			while (rs.next()){
				this.name[i][j]=new String(rs.getString(1));
					j++;  
			}
			i++;*/
		}
		
		/*i=0;
		for(String subBrand:this.brand){
			sqlString ="select num from picName where brand ='"+subBrand+"'";
			rs = sqlite.getDateSet(sqlString);
			int j=0;
			while (rs.next()){
				this.ps[i][j]=Integer.parseInt(new String(rs.getString(1)));
					j++;  
			}
			i++;
		}*/
	}

	public String[] getBrand() {
		return brand;
	}

	public String[][] getName() {
		return name;
	}

/*	public int[][] getPs() {
		return ps;
	}*/
	

}
