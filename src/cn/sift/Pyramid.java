package cn.sift;

import java.util.ArrayList;

import cn.point.ImagePixelArray;

/**
* ��Pyramid.java��ʵ��������TODO ��ʵ������
*/
public class Pyramid {

    public ArrayList<OctaveSpace> octaves; // ������һ���м���8�ȿռ�

    public int buildOctaves(ImagePixelArray source, float scale, int levelsPerOctave, float octaveSigm, int minSize) {
        this.octaves = new ArrayList<OctaveSpace>();
        OctaveSpace downSpace = null;
        ImagePixelArray prev = source;

        while (prev != null && prev.width >= minSize && prev.height >= minSize) {
            OctaveSpace osp = new OctaveSpace();

            // Create both the gaussian filtered images and the DOG maps
            osp.makeGaussianImgs(prev, scale, levelsPerOctave, octaveSigm);
            osp.makeGaussianDiffImgs();
            octaves.add(osp);
            prev = osp.getLastGaussianImg().halved();
            if (downSpace != null) downSpace.up = osp;
            osp.down = downSpace;
            downSpace = osp;
            scale *= 2.0;
        }
        return (octaves.size());
    }
}
