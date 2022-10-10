/*******************************************************************
 * Copyright (C) 2013 Neusoft Group Ltd. All rights reserved.
 *
 * @fileName:CacheUtils.java
 * @author:zhaoyong.neu
 * @version:v1.0.0 
 * Modification History:
 * Date        Author           Version      Description
 * -----------------------------------------------------------------
 * 2013-9-11     zhaoyong.neu     v1.0.0       create
 *
 *******************************************************************/
package com.shc.north;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.neusoft.library.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>CacheUtils</b>
 * <p>TODO [缓存管理工具]</p>
 * @author zhaoyong.neu
 * @version 1.0.0
 * Created on 2013-9-11 下午3:28:41
 */
public class CacheUtils {
	
//	private static final String tag="CacheUtils";
	private static final String whichSp="north";

	/** 
     *  
     * @param mContext 上下文，来区别哪一个activity调用的 
     * @param field SharedPreferences的哪一个字段
     * @return 
     */  
    //取出whichSp中field字段对应的string类型的值  
    public static String getSharePreStr(Context mContext, String field){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        String s=sp.getString(field,"");//如果该字段没对应值，则取出字符串0
        return s;
    }

  //取出whichSp中field字段对应的bool类型的值
    public static Boolean getSharePreBool(Context mContext, String field){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        Boolean s=sp.getBoolean(field,false);//如果该字段没对应值，则取出字符串false
        return s;
    }
  //取出whichSp中field字段对应的bool类型的值
    public static Boolean getSharePreBoolTrue(Context mContext, String field){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        Boolean s=sp.getBoolean(field,true);//如果该字段没对应值，则取出字符串false
        return s;
    }
    //取出whichSp中field字段对应的int类型的值
    public static int getSharePreInt(Context mContext, String field){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        int i=sp.getInt(field,0);//如果该字段没对应值，则取出0
        return i;
    }
    //取出whichSp中field字段对应的int类型的值
    public static int getSharePreInt(Context mContext, String field, int defaultInt){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        int i=sp.getInt(field,defaultInt);//如果该字段没对应值，则取出0
        return i;
    }
    //取出whichSp中field字段对应的int类型的值
    public static int getSharePreIntDefault2(Context mContext, String field){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        int i=sp.getInt(field,2);//如果该字段没对应值，则取出0
        return i;
    }
    //取出whichSp中field字段对应的int类型的值
    public static int getSharePreIntDefault1(Context mContext, String field){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        int i=sp.getInt(field,1);//如果该字段没对应值，则取出0
        return i;
    }
    //取出whichSp中field字段对应的int类型的值
    public static long getSharePreLong(Context mContext, String field){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        long i=sp.getLong(field,0);//如果该字段没对应值，则取出0
        return i;
    }
    //保存string类型的value到whichSp中的field字段
    public static void setSharePre(Context mContext, String field, String value){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        sp.edit().putString(field, value).commit();
    }
    //保存int类型的value到whichSp中的field字段
    public static void setSharePre(Context mContext, String field, int value){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        sp.edit().putInt(field, value).commit();
    }
    //保存bool类型的value到whichSp中的field字段
    public static void setSharePre(Context mContext, String field, Boolean value){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        sp.edit().putBoolean(field, value).commit();
    }
    public static void setSharePre(Context mContext, String field, long value){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        sp.edit().putLong(field, value).commit();
    }
    public static void remove(Context mContext, String key) {
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        sp.edit().remove(key).commit();
    }

    /**
     * 缓存List
     * @param tag
     * @param datalist
     */
    public static <T> void setDataList(Context context, String tag, List<T> datalist) {
        SharedPreferences sp= context.getSharedPreferences(whichSp, 0);
        if (null == datalist || datalist.size() <= 0){
            sp.edit().putString(tag, "").commit();
            return;
        }
        String strJson = JsonUtils.jsonToStr(datalist);
        sp.edit().putString(tag, strJson).commit();
    }

    /**
     * 获取List
     * @param tag
     * @return
     */
    public static <T> List<T> getDataList(Context context, String tag, Class<T> t) {
        SharedPreferences sp= context.getSharedPreferences(whichSp, 0);
        List<T> dataList=new ArrayList<>();
        String strJson = sp.getString(tag, null);
        if (TextUtils.isEmpty(strJson)) {
            return dataList;
        }
        dataList = JsonUtils.jsonToList(strJson, t);
        return dataList;
    }

    public static int getCacheInt(Context mContext, String spName, String field){
        SharedPreferences sp= mContext.getSharedPreferences(spName, Context.MODE_MULTI_PROCESS);
        return sp.getInt(field, 1);
    }

    public static void cacheInt(Context mContext, String spName, String field, int value){
        SharedPreferences sp= mContext.getSharedPreferences(spName, Context.MODE_MULTI_PROCESS);
        sp.edit().putInt(field, value).commit();
    }

    public static void clearSp(Context mContext, String spName){
        SharedPreferences sp= mContext.getSharedPreferences(spName, Context.MODE_MULTI_PROCESS);
        sp.edit().clear().clear();
    }

    //保存bool类型的value到whichSp中的field字段  屏幕消息亲情网的引导是否点击过
    public static void setQinScreenMsgSharePre(Context mContext, String field, Boolean value){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        sp.edit().putBoolean(field, value).commit();
    }

    //取出whichSp中field字段对应的bool类型的值 屏幕消息亲情网的引导是否点击过
    public static Boolean getQinScreenMsgSharePre(Context mContext, String field){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        Boolean s=sp.getBoolean(field,false);//如果该字段没对应值，则取出字符串false
        return s;
    }

    //保存bool类型的value到whichSp中的field字段  屏幕消息企业网的引导是否点击过
    public static void setQiScreenMsgSharePre(Context mContext, String field, Boolean value){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        sp.edit().putBoolean(field, value).commit();
    }

    //取出whichSp中field字段对应的bool类型的值 屏幕消息企业网的引导是否点击过
    public static Boolean getQiScreenMsgSharePre(Context mContext, String field){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        Boolean s=sp.getBoolean(field,false);//如果该字段没对应值，则取出字符串false
        return s;
    }

    //保存bool类型的value到whichSp中的field字段  屏幕消息状态获取时间
    public static void setGetScreenMsgTimeSharePre(Context mContext, String field, String value){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        sp.edit().putString(field, value).commit();
    }

    //取出whichSp中field字段对应的bool类型的值 屏幕消息状态获取时间
    public static String getGetScreenMsgTimeSharePre(Context mContext, String field){
        SharedPreferences sp= mContext.getSharedPreferences(whichSp, Context.MODE_MULTI_PROCESS);
        String s=sp.getString(field,"0");////如果该字段没对应值，则取出字符串0
        return s;
    }
}
