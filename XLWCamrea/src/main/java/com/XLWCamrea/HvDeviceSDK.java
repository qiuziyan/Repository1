package com.XLWCamrea;

import com.sun.jna.Callback;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author master
 * @FileName: HvDeviceSDK
 * @create 2018/7/19 16:00
 * @since 1.0.0
 */
public interface HvDeviceSDK extends StdCallLibrary {
    HvDeviceSDK instanceDll = (HvDeviceSDK) Native.loadLibrary("C:\\Windows\\System32\\HvDevice.dll", HvDeviceSDK.class);


    /**
     * 回调数据类型
     */
    static int CALLBACK_TYPE_RECORD_PLATE = (int) 0xFFFF0001;
    static int CALLBACK_TYPE_RECORD_BIGIMAGE = (int) 0xFFFF0002;
    static int CALLBACK_TYPE_RECORD_SMALLIMAGE = (int) 0xFFFF0003;
    static int CALLBACK_TYPE_RECORD_BINARYIMAGE = (int) 0xFFFF0004;
    static int CALLBACK_TYPE_RECORD_INFOBEGIN = (int) 0xFFFF0005;
    static int CALLBACK_TYPE_RECORD_INFOEND = (int) 0xFFFF0006;
    static int CALLBACK_TYPE_STRING = (int) 0xFFFF0007;
    static int CALLBACK_TYPE_JPEG_FRAME = (int) 0xFFFF0008;
    static int CALLBACK_TYPE_H264_VIDEO = (int) 0xFFFF0009;
    static int CALLBACK_TYPE_HISTORY_VIDEO = (int) 0xFFFF0010;

    /**
     * 请求结果类型
     */
    static int RESULT_RECV_FLAG_REALTIME = (int) 0xFFFF0500;
    static int RESULT_RECV_FLAG_HISTORY = (int) 0xffff0501;
    static long RESULT_RECV_FLAG_HISTROY_ONLY_PECCANCY = (long) 0x00ffff0502;

    /**
     * 连接设备
     *
     * @param szIP
     * @param szApiVer
     * @return
     */
    Pointer HVAPI_OpenEx(String szIP, String szApiVer);

    /**
     * 断开连接
     *
     * @param hHandle
     * @return
     */
    int HVAPI_CloseEx(Pointer hHandle);

    /**
     * 设置回调
     *
     * @param hHandle
     * @param pFunc
     * @param pUserData
     * @param iVideoID
     * @param iCallBackType
     * @param szConnCmd
     * @return
     */
    int HVAPI_SetCallBackEx(Pointer hHandle, Callback pFunc, StructData.ResultClass.ByReference pUserData,
                            int iVideoID, int iCallBackType, String szConnCmd);

    /**
     * 触发开闸
     *
     * @param hHandle
     * @param iVideoID
     * @return
     */
    int HVAPI_TriggerSignal(Pointer hHandle, int iVideoID);

    /**
     * 发送命令
     *
     * @param hHandle
     * @param szCmd
     * @param szRetBuf
     * @param nBufLen
     * @param pnRetLen
     * @return
     */
    int HVAPI_ExecCmdEx(Pointer hHandle, String szCmd, byte[] szRetBuf, int nBufLen, IntByReference pnRetLen);

    /**
     * 小图转换
     *
     * @param pbSmallImageData
     * @param nSmallImageWidth
     * @param nSmallImageHeight
     * @param pbBitmapData
     * @param pnBitmapDataLen
     * @return
     */
    int HVAPIUTILS_SmallImageToBitmapEx(Pointer pbSmallImageData, int nSmallImageWidth, int nSmallImageHeight, Memory pbBitmapData, IntByReference pnBitmapDataLen);

    /**
     * 抓拍
     *
     * @param hHandle
     * @param iVideoID
     * @param ImageAddr
     * @return
     */
    int HVAPI_GetCaptureImage(Pointer hHandle, int iVideoID, String ImageAddr);

    /**
     * XML提取
     *
     * @param fIsNewXmlProtocol
     * @param retXmlStr
     * @param cmdName
     * @param infoName
     * @param infoValue
     * @return
     */
    int HVAPIUTILS_GetExeCmdRetInfoEx(boolean fIsNewXmlProtocol, String retXmlStr, String cmdName, String infoName, byte[] infoValue);

    /**
     * 加载黑白名单
     *
     * @param hHandle
     * @param szWhiteNameList
     * @param iWhiteListLen
     * @param szBlackNameList
     * @param iBlackListLen
     * @return
     */
    int HVAPI_InportNameList(Pointer hHandle, String szWhiteNameList, int iWhiteListLen, String szBlackNameList, int iBlackListLen);

    /**
     * 读取黑白名单
     *
     * @param hHandle
     * @param szWhiteNameList
     * @param iWhiteListLen
     * @param szBlackNameList
     * @param iBlackListLen
     * @return
     */
    int HVAPI_GetNameList(Pointer hHandle, byte[] szWhiteNameList, IntByReference iWhiteListLen, byte[] szBlackNameList, IntByReference iBlackListLen);

    /**
     * 单独回调
     *
     * @param hHandle
     * @param pFunc
     * @param pUserData
     * @param iVideoID
     * @param dw64BeginTimeMS
     * @param dw64EndTimeMS
     * @param dwStartIndex
     * @param dwRecvFlag
     * @return
     */
    int HVAPI_StartRecvResult(Pointer hHandle, Callback pFunc, StructData.ResultClass.ByReference pUserData, int iVideoID,
                              long dw64BeginTimeMS, long dw64EndTimeMS, int dwStartIndex, int dwRecvFlag);

    /**
     * 附加信息XML解析
     *
     * @param XMLAppend
     * @param AppendList
     * @param AppendLen
     * @return
     */
    int HVAPIUTILS_ParsePlateXmlStringEx(String XMLAppend, Memory AppendList, IntByReference AppendLen);
}
