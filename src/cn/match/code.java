package cn.match;

/**
 * ������
 * @author eple 2014.5.29
 */
public class code {
	
	public code(){
		
	}
	
	public static String getCode(float[] p) throws Exception{
		char[] code = new char[60];
		if(p.length != 20){
			throw (new Exception("���ٱ��������λ������20����"));
		}
		for(int i=0;i<20;i++){
			if(p[i] > 0){
				code[i] = '1';
				if(p[i] > 0.5){
					code[i+20] = '1';
					if(p[i] > 0.75){
						code[i+40] = '1';
					}else{
						code[i+40] = '0';
					}
				}else{
					code[i+20] = '0';
					if(p[i]>0.25){
						code[i+40] = '1';
					}else{
						code[i+40] = '0';
					}
				}
				}else{
					code[i] = '0';
					if(p[i] > -0.5){
						code[i+20] = '1';
						if(p[i] > -0.25){
							code[i+40] = '1';
						}else{
							code[i+40] = '0';
						}
					}else{
						code[i+20] = '0';
						if(p[i]>-0.75){
							code[i+40] = '1';
						}else{
							code[i+40] = '0';
						}
					}
			}
		}
	
		return String.valueOf(code);
	}
}
