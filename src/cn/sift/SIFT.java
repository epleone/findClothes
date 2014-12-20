package cn.sift;

import java.util.ArrayList;
import java.util.List;

import cn.point.FeaturePoint;
import cn.point.GaussianArray;
import cn.point.ImagePixelArray;
import cn.point.KDFeaturePoint;

/**
* ��SIFT.java��ʵ�������������û����������ӿ�
*
*/
public class SIFT {

	// ���³��������������Ƽ��Ĳ���ֵ
    float preprocSigma             = 1.5f;   // ���ڴ���double���ͼ��Ԥ�����ģ������
    float octaveSigma              = 1.6f;   // ���ڴ���ÿ��8�ȿռ�ͼ���ģ������
    int    minimumRequiredPixelsize = 32;    // ��˹����������Сʱ��С�ĳߴ�
    int    scaleSpaceLevels         = 3;     // ÿ��8�ȿռ���Ҫ��ȡ��ֵ��Ĳ��ͼ�㣬�������ڱȽϵ����²�����Ҫ��5�����ͼ����������Ҫ��6����˹ģ��ͼ��
    float dogThresh                = 0.0075f; // �ڲ��ͼ���ϵļ��µ�ֵ��Сֵ����ֹ��Ƭ��ģ����ĵ㱻ѡ�У����ֵԽСѡ�еĵ�Խ�ࡣ
    float dValueLowThresh          = 0.03f; // ����Χ��ȽϵĲ�ֵ�������ֵ�Ǿ�����������Ĳ�ֵ������ֱ�ӱȽϵġ������н���Ϊ0.03��page
    											// 11��������ȡ�ĵ���̫�٣������޸�Ϊ0.008
    float maximumEdgeRatio         = 20.0f;  // �ǽǵ�Ĺ��Ǳ�
    float scaleAdjustThresh        = 0.50f;  // �߶ȿռ�ľ�ȷ�����ʵͼ���ϵ���ɢ����Ͷлʱ��Ҫ�������������������Χ�������ֵ�Ϳ�������һ���㡣
    float peakRelThresh            = 0.8f;   //
    int    relocationMaximum        = 4;

    public List<FeaturePoint> detectFeatures(ImagePixelArray img) {
        return (detectFeaturesDownscaled(img, -1, 1.0f));
    }

    /**
    * @param img
    * @param preProcessMark ͼ��Ԥ����ı�ǣ�С��0��img��Ҫdouble,����0ʱ��˵��ͼ��ĳ��Ϳ�Ҫhalf������ߴ����£�����0��Ԥ����
    * @param startScale
    * @return
    */
    public List<FeaturePoint> detectFeaturesDownscaled(ImagePixelArray img, int preProcessMark, float startScale) {

        if (preProcessMark < 0) {
            img = img.doubled();
            startScale *= 0.5;
        } else if (preProcessMark > 0) {
            while (img.width > preProcessMark || img.height > preProcessMark) {
                img = img.halved();
                startScale *= 2.0;
            }
        }
        if (preprocSigma > 0.0) {
            GaussianArray gaussianPre = new GaussianArray(preprocSigma);
            img = gaussianPre.convolve(img);
        }

        Pyramid pyr = new Pyramid();
        pyr.buildOctaves(img, startScale, scaleSpaceLevels, octaveSigma, minimumRequiredPixelsize);
        
        globalFeaturePoints = new ArrayList<FeaturePoint>();
        // Generate featurePoints from each scalespace.
        for (int on = 0; on < pyr.octaves.size(); ++on) {
            OctaveSpace osp = pyr.octaves.get(on);

            ArrayList<ScalePeak> peaks = osp.findPeaks(dogThresh);// Ѱ��ͼƬ�еļ�ֵ��
            ArrayList<ScalePeak> peaksFilted = osp.filterAndLocalizePeaks(peaks, maximumEdgeRatio, dValueLowThresh,
                                                                          scaleAdjustThresh, relocationMaximum);

            // �Ƚ�Ҫ�����ͼ�����������ص��ݶȴ�С�ͷ���������
            osp.pretreatMagnitudeAndDirectionImgs();
            ArrayList<FeaturePoint> faturePoints = osp.makeFeaturePoints(peaksFilted, peakRelThresh, scaleSpaceLevels,
                                                                         octaveSigma);
            osp.clear();
            globalFeaturePoints.addAll(faturePoints);
        }
        return globalFeaturePoints;

    }
    
    private List<FeaturePoint> globalFeaturePoints;
    private List<KDFeaturePoint> globalKDFeaturePoints;
    
    public List<KDFeaturePoint> getGlobalKDFeaturePoints() {

        if (globalKDFeaturePoints != null) return (globalKDFeaturePoints);
        if (globalFeaturePoints == null) throw (new IllegalArgumentException("No featurePoints generated yet."));
        globalKDFeaturePoints = new ArrayList<KDFeaturePoint>();
        for (FeaturePoint fp : globalFeaturePoints) {
            globalKDFeaturePoints.add(new KDFeaturePoint(fp));
        }
        return globalKDFeaturePoints;
    }
}

