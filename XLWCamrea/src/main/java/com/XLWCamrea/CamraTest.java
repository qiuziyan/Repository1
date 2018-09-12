package com.XLWCamrea;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.XLWCamrea.StructData.CImageInfo;
import com.XLWCamrea.StructData.ResultClass;
import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public class CamraTest {
	private Pointer MainHandle = null;
	private StructData.ResultClass	CallBackInfo = null;
	private StructData.ResultClass.ByReference ResultRefer = new StructData.ResultClass.ByReference();
	
	private CallBackFun OnRecordResult = new CallBackFun();
	
	public CamraTest(){
		System.setProperty("jna.encoding", "GBK");
	}
	
	public int OpenDevice(String IP)
	{	
		
		int Result = 2;
		if (MainHandle != null)
		{
			return -1;
		}
		MainHandle = HvDeviceSDK.instanceDll.HVAPI_OpenEx(IP, null);
		if (MainHandle != null)
		{
			System.out.println("调用摄像头");
			Result = 0;
		}
		return Result;
	}
	
	public Boolean  SetResultCallBack(String IP) throws ParseException
	{
		ResultRefer.IP = IP;
		ResultRefer.BaseHandle = this.MainHandle;
		if(ResultRefer.BaseHandle != null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    	Date date = df.parse("2018-09-11 22:30:00");
	    	Date date2 = df.parse("2018-09-12 01:30:00");
			long beginTime = date.getTime()/1000;
			long endTime = date2.getTime()/1000;
			System.out.println("开始时间:" +beginTime);
			System.out.println("开始时间:" +endTime);
			if(HvDeviceSDK.instanceDll.HVAPI_StartRecvResult(MainHandle, OnRecordResult, ResultRefer, 0, beginTime, endTime, 0, HvDeviceSDK.RESULT_RECV_FLAG_HISTORY) == 0)
			{
				System.out.println("回调成功");
				return true;
			}
			else
			{
				System.out.println("回调失败");
				return false;
			}
			
		}
		return true;
	}
	

	public int StopDeviceConn()
	{
		if (MainHandle == null)
		{
			return -1;
		}
		
		if (HvDeviceSDK.instanceDll.HVAPI_CloseEx(MainHandle) == 0)
		{
			return 0;
		}
		return 1;
	}
	
	public class CallBackFun implements Callback {
		   public int apply(ResultClass.ByReference pUserData, int dwImageFlag, int dwImageType, int dwCarID, String pcPlateNo,
                   String pcAppendInfo, long dw64TimeMS,
                   CImageInfo.ByValue pPlate, //Сͼ
                   CImageInfo.ByValue pPlateBin,
                   CImageInfo.ByValue pBestSnapshot,
                   CImageInfo.ByValue pLastSnapshot, //��ͼ
                   CImageInfo.ByValue pBeginCapture,
                   CImageInfo.ByValue pBestCapture,
                   CImageInfo.ByValue pLastCapture) {

		      //���ƺ�
		      pUserData.strPlateNo = pcPlateNo;
		      //����ʱ��
		      pUserData.PlateTimeMS = dw64TimeMS;
		      //������Ϣ
		      pUserData.strAppendInfo = pcAppendInfo;
		
		
		      //��ͼ��ֵ
		      if (pLastSnapshot.fHasData == 1) {
		    	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		          Date date = new Date(pUserData.PlateTimeMS);
		          System.out.println(pcPlateNo +"时间"+date.toString());
		      }
		    
		      return 0;
		  }
		
	}

}
