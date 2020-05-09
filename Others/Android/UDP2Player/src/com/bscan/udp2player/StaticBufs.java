package com.bscan.udp2player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StaticBufs {
    public static final ArrayList<String> lstNames = new ArrayList();
    public static final String[] sCurPlayingPart = new String[1];
    public static final int[] iCntThreads = new int[1];
    public static final int iBufBlockSize = 32*1024;
	public static Vector<String> vecIngAndDone=new Vector<String>();

    public static final HashMap<String ,byte[]> vFileMap = new HashMap<>(); //map，hashmap不是线程安全的
  //Hashtable
//    public static final Map<String, byte[]> vFileMap = new Hashtable<>();
    public static final HashMap<String ,String> header = new HashMap<>();
    public static Lock lock = new ReentrantLock();;
    public static boolean conKey(String sKey) {
    	boolean ret = false;
		lock.lock(); 
		ret = vFileMap.containsKey(sKey);
		lock.unlock();  
		return ret;
    }
    private StaticBufs() {
    }
    static {
//        header.put("Accept-Encoding", "gzip, deflate");
//        header.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//        header.put("Cache-Control", "max-age=0");
        header.put("Connection", "keep-alive");
        header.put("Accept-Encoding", "identity");
//        header.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");
//        header.put("Accept",
//                "image/gif,image/jpeg,image/pjpeg,image/pjpeg, "
//                        + "application/x-shockwave-flash, application/xaml+xml, "
//                        + "application/vnd.ms-xpsdocument, application/x-ms-xbap"
//                        + "application/x-ms-application,application/vnd.ms-excel"
//                        + "application/vnd.ms-powerpoint, application/msword,*/*");
    }
	public static void mapPut(String input, byte[] pppm) {
		lock.lock();  
		vFileMap.put(input, pppm);
		lock.unlock();  
		UDP_Push.pushLog("vFileMap.put("+input);
	}
	public static byte[] mapGet(String sKey) {
		lock.lock();  
		byte[] tmp = vFileMap.get(sKey);
		lock.unlock();  
		return tmp;
	}
	public static void mapRemove(String string) {
		lock.lock();  
		vFileMap.remove(string);
		lock.unlock();  
	}

}