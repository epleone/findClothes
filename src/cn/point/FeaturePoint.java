package cn.point;


public class FeaturePoint {

    public float          x;
    public float          y;

    public ImagePixelArray image;                   // 特征点所在的高斯图层
    public float          imgScale;                // 找到该点的高斯图层在8度空间尺度
    public float          scale;

    public float          orientation;

    public float[]        features;
    public boolean         hasFeatures = false;

    public int xDim; 
    public int yDim;
    public int oDim;
    public FeaturePoint(){
    }
    
    public FeaturePoint(float x, float y, float scale, float orientation,float[] features) throws Exception{
    	this.x = x;
    	this.y = y;
    	this.scale =scale;
    	this.orientation = orientation;
    	this.hasFeatures = true;
    	if(features.length == 128){
    		this.features = features;
    	}else{
    		throw(new Exception("初始化特征向量数据错误！"));
    	}
    }
    
    public FeaturePoint(ImagePixelArray image, float x, float y, float imgScale, float kfScale, float orientation){
        this.image = image;
        this.x = x;
        this.y = y;
        this.imgScale = imgScale;
        this.scale = kfScale;
        this.orientation = orientation;
    }
    
    public void createVector(int xDim, int yDim, int oDim) {
        this.hasFeatures = true;
        this.xDim = xDim;// 4 dim
        this.yDim = yDim;// 4 dim
        this.oDim = oDim;// 8 dim
        features = new float[yDim * xDim * oDim];
    }
    
    /*
     * 特征向量归一化(0-1之间)
     */
    public void normalization(){
    	float EM = getEM();
    	float sigma = getSigma();
    	for(int i=0;i<128;i++){
    		features[i] = ((features[i]-EM)/sigma);//(((features[i]-EM)/sigma*3)+1)/2;
    		if(features[i]>1)
    			features[i]=1;
    		if(features[i]<-1)
    			features[i]=-1;
    		features[i] =(features[i]+1)/2;
    	}
    }
    
    public float getEM(){
    	float EM= 0;
    	for(float p :features)
    		EM +=p;
    	return EM/128;
    }
    
    public float getSigma(){
    	float sigma=0;
    	float EM = getEM();
    	for(float p :features){
    		sigma += (p-EM)*(p-EM);
    	}
    	sigma /= 128; 
    	return (float)Math.sqrt(sigma);
    }
}

