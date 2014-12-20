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
		Long insertNote;//Ҫ���������
		Long[] array=arr;
	    
		//������ĵڶ���Ԫ�ؿ�ʼѭ���������е�Ԫ�ز���
		for (i=1;i<array.length;i++)
		{
			//���������еĵ�2��Ԫ��Ϊ��һ��ѭ��Ҫ����������
			insertNote = array[i];
			j=i-1;
			while(j>=0&&insertNote<array[j])   
			{
				//���Ҫ������Ԫ��С�ڵ�j��Ԫ��,�ͽ���j��Ԫ������ƶ�
				array[j+1]=array[j];
				j--;
			}
			//ֱ��Ҫ�����Ԫ�ز�С�ڵ�j��Ԫ��,��insertNote���뵽������
			array[j+1]=insertNote; 
	  }
	  //��ӡ����������
	  //System.out.println(Arrays.toString(array));
	  return array;	  
	 }
	
	//ð������
	 public Long[] quickSort(Long[] arr)
	 {
		  int i,j;
		  Long temp;
		  Long[] array=arr;
		  //n��Ԫ�ص��������n-1������
		  for(i=0;i<array.length-1;i++)
		  {
			  
			  //��Ϊÿһ��ѭ����ȷ��һ������Ԫ�ص�λ��,
			  //����ÿһ�ֵıȽϴ�������ݼ�
			  for(j=0;j<array.length-i-1;j++)
			  {
				  if(array[j]>array[j+1])
				  {
					  //�����j��Ԫ�ر�����������ڵ�Ԫ�ش�Ļ��ͽ���
					  temp=array[j];     
					  array[j]=array[j+1];
					  array[j+1]=temp;
				  }
		   }
	  }
	  //��ӡ������������
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
