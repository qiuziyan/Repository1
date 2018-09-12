package com.XLWCamrea;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class StructData {

    public static class ResultClass extends Structure {
        public String IP;
        public String strPlateNo;
        public Pointer BaseHandle;
        public String BigPicAddr = null;
        public String SmallPicAddr = null;
        public String strAppendInfo;
        public long PlateTimeMS = 0;


        public static class ByReference extends ResultClass implements Structure.ByReference {
        }

        public static class ByValue extends ResultClass implements Structure.ByValue {
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList(new String[]{"IP", "strPlateNo", "BaseHandle", "BigPicAddr", "SmallPicAddr", "strAppendInfo", "PlateTimeMS"});
        }
    }

    public static class CImageInfo extends Structure {
        /**
         * �ṹ���С
         */
        public int wSize = 0;
        /**
         * ͼƬ����
         */
        public int wImgType = 0;
        /**
         * ͼƬ��
         */
        public int wWidth = 0;
        /**
         * ͼƬ�߶�
         */
        public int wHeigth = 0;
        /**
         * ͼƬ����ָ��
         */
        public Pointer pbData;
        /**
         * ͼƬ����ָ�볤��
         */
        public int dwDataLen = 0;
        /**
         * ͼƬʱ��
         */
        public long dw64TimeMS = 0;
        public int fHasData = 0;

        public static class ByReference extends CImageInfo implements Structure.ByReference {
        }


        public static class ByValue extends CImageInfo implements Structure.ByValue {
        }


        @Override
        protected List<String> getFieldOrder() {

            return Arrays.asList(new String[]{"wSize", "wImgType", "wWidth", "wHeigth", "pbData", "dwDataLen", "dw64TimeMS", "fHasData"});
        }
    }

}