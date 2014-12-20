package com.example.hyy;

import java.util.Random;

public class Sort 
{
	//Bubble Sort
	//Bubble
	Long[][] insertLongs(Long[][] bbLongs)
	{
		Long[][] aLongs=new Long[bbLongs.length][];
		for(int i=0; i<bbLongs[i].length;i++)
		{
			aLongs[i]=InsertSort(bbLongs[i]);
		}
		
		return aLongs;
	}
	
	Long[][] quickLongs(Long[][] bbLongs)
	{
		Long[][] aLongs=new Long[bbLongs.length][];
		for(int i=0; i<bbLongs[i].length;i++)
		{
			aLongs[i]=quickSort(bbLongs[i]);
		}
		
		return aLongs;
	}
	
	public Long[] InsertSort(Long[] arr)
	 {
		int i,j;
		Long insertNote;//要插入的数据
		Long[] array=arr;
	    
		//从数组的第二个元素开始循环将数组中的元素插入
		for (i=1;i<array.length;i++)
		{
			//设置数组中的第2个元素为第一次循环要播讲的数据
			insertNote = array[i];
			j=i-1;
			while(j>=0&&insertNote<array[j])   
			{
				//如果要播讲的元素小于第j个元素,就将第j个元素向后移动
				array[j+1]=array[j];
				j--;
			}
			//直到要插入的元素不小于第j个元素,将insertNote插入到数组中
			array[j+1]=insertNote; 
	  }
	  //打印排序后的数组
	  //System.out.println(Arrays.toString(array));
	  return array;	  
	 }
	
	//冒泡排序法
	 public Long[] quickSort(Long[] arr)
	 {
		  int i,j;
		  Long temp;
		  Long[] array=arr;
		  //n个元素的数组进行n-1轮排序
		  for(i=0;i<array.length-1;i++)
		  {
			  
			  //因为每一轮循环将确定一个数组元素的位置,
			  //所以每一轮的比较次数将会递减
			  for(j=0;j<array.length-i-1;j++)
			  {
				  if(array[j]>array[j+1])
				  {
					  //如果第j个元素比它后面的相邻的元素大的话就交换
					  temp=array[j];     
					  array[j]=array[j+1];
					  array[j+1]=temp;
				  }
		   }
	  }
	  //打印出排序后的数组
	  //System.out.println(Arrays.toString(array));
	  return array;
	 }


	
	//generate random long int
	Long[][] ranLongs(int row, int col)
	{
		Random random = new Random(10000);
		Long[][] aLongs=new Long[row][col];
		for (int i=0; i<row; i++)
		{
			for(int j=0; j<col; j++)
			{
				//random=;
				aLongs[i][j]=random.nextLong();
			}
		}
		
		return aLongs;
	}
	
}
