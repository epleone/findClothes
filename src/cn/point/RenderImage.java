package cn.point;
import android.graphics.Bitmap;

public class RenderImage {

    //private BufferedImage srcImage;
    private Bitmap srcImage;
/*
    public RenderImage(BufferedImage srcImage){
        this.srcImage = srcImage;
    }*/
    
    public RenderImage(Bitmap bitmap){
    	this.srcImage = bitmap;
    }
    public int getWidth() {
        return this.srcImage.getWidth();
    }

    public int getHeight() {
        return this.srcImage.getHeight();
    }

    /*
    public float scaleWithin(int dim) {
        if (this.srcImage.getWidth() <= dim && this.srcImage.getHeight() <= dim) return 1.0f;
        float xScala = (float) dim / this.srcImage.getWidth();
        float yScala = (float) dim / this.srcImage.getHeight();

        float smallestScala = xScala <= yScala ? xScala : yScala; /// 取最小的比例

        /// 创建一个缩小后的位图
        BufferedImage bmScalaed = new BufferedImage((int) (this.srcImage.getWidth() * smallestScala + 0.5),
                                                    (int) (this.srcImage.getHeight() * smallestScala + 0.5),
                                                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bmScalaed.createGraphics();
        g.drawImage(this.srcImage, 0, 0, (int) (this.srcImage.getWidth() * smallestScala),
                    (int) (this.srcImage.getHeight() * smallestScala), null);
     // TODO,这里可以优化
        this.srcImage = bmScalaed;
        return smallestScala;
    }
*/
    public ImagePixelArray toPixelFloatArray() {
        int h = this.srcImage.getHeight();
        int w = this.srcImage.getWidth();
   
        ImagePixelArray res = new ImagePixelArray(w, h);
        
        int[] pix = new int[w * h];
        srcImage.getPixels(pix, 0, w, 0, 0, w, h);
        
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int c = pix[x + y * w];
                int R = (c >> 16) & 0xFF;
                int G = (c >> 8) & 0xFF;
                int B = (c >> 0) & 0xFF;
                res.data[x + y * w] = (0.3f*R + 0.59f*G + 0.11f*B)/255.0f;
            }
        }
        return res;
    }

}

