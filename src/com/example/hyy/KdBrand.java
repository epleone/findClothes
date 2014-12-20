package com.example.hyy;

import java.io.Serializable;

public class KdBrand implements Serializable
{
	public String brand1;
	public String brand2;
	public String brand3;
	public String brand4;
	public String brand5;
	public String brand6;
	public String brand7;
	public String brand8;
	public String brand9;
	
	public void setBrand(int i,String s){
		switch (i) {
		case 1:
			this.brand1 = s;
			break;
		case 2:
			this.brand2 = s;
			break;
		case 3:
			this.brand3 = s;
			break;
		case 4:
			this.brand4 = s;
			break;
		case 5:
			this.brand5 = s;
			break;
		case 6:
			this.brand6 = s;
			break;
		case 7:
			this.brand7 = s;
			break;
		case 8:
			this.brand8 = s;
			break;
		case 9:
			this.brand9 = s;
			break;

		}
	}
}
