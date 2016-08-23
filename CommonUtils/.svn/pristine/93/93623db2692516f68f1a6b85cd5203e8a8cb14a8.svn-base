package com.het.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class FileUtils {  
	private static String SDK_PATH = "/mnt/sdcard/";
    //private static String SDK_PATH = Environment.getExternalStorageDirectory()+"";
  
    public static String getAPKPATH() {  
        return SDK_PATH + "/Het/clife";  
    }  
    
    /**
     * byte[] 2 inputStream
     */
    public static InputStream bytes2inputStream(byte[] data){
    	InputStream inputStream = null;
		try {
			inputStream = new ByteArrayInputStream(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream;
    }
    
    /** 
     * 在SD卡上创建文件 
     * @throws IOException 
     */  
    public static File creatSDFile(String fileName) throws IOException {  
        return creatSDFile("", fileName);
    }  
    
    public static File Uri2File(Uri uri){
    	if (uri.getScheme().compareTo("file")==0){
    		String fileName = uri.toString();
    		fileName = uri.toString().replace("file://", "");
    		File file = new File(fileName);
    		return file;
    	}
    	return null;
    }
	public static String getFileNameFromUrl(String url){
		String tmp = url.substring(url.lastIndexOf("/") + 1);
		String[] arr = tmp.split("=");
		return arr[arr.length-1];
	}

    public static File creatSDFile(String path, String fileName) throws IOException {  
    	  File file = new File(SDK_PATH + path + fileName);  
    	  LogUtils.d("FileUtils", SDK_PATH + path + fileName);
          file.createNewFile();  
          return file;  
    }
    /** 
     * 在SD卡上创建目录 
     * @param dirName 
     */  
    public static File creatSDDir(String dirName) {  
        File dir = new File(SDK_PATH + dirName);  
        dir.mkdirs();  
        return dir;  
    }  
  
    /** 
     * 判断SD卡上的文件夹是否存在 
     */  
    public static boolean isFileExist(String path, String fileName){  
        File file = new File(SDK_PATH + path + fileName);  
        return file.exists();  
    }  
    
    public static boolean isFileExist(String fileName){  
        return isFileExist(SDK_PATH, fileName);
    } 
    
    public static final String file2String(File file) {  
        BufferedReader br;  
        StringBuilder strBlder = new StringBuilder("");  
        try {  
            br = new BufferedReader(new InputStreamReader(new FileInputStream(  
                    file)));  
            String line = "";  
            while (null != (line = br.readLine())) {  
                strBlder.append(line + "\n");  
            }  
            br.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        return strBlder.toString();  
    }  
    
    public static final String fileInputStream2String(InputStream is) {  
        BufferedReader br;  
        StringBuilder strBlder = new StringBuilder("");  
        try {  
            br = new BufferedReader(new InputStreamReader(is));  
            String line = "";  
            while (null != (line = br.readLine())) {  
                strBlder.append(line + "\n");  
            }  
            br.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        return strBlder.toString();  
    }  
      
    /** 
     * 将一个InputStream里面的数据写入到SD卡中 
     */  
    public static File write2SDFromInput(String path,String fileName,InputStream input){  
        File file = null;  
        OutputStream output = null;  
        try{  
            creatSDDir(path);  
            file = creatSDFile(path , fileName);  
            output = new FileOutputStream(file);  
            byte buffer [] = new byte[4 * 1024];  
            while((input.read(buffer)) != -1){  
                output.write(buffer);  
            }  
            output.flush();  
        }  
        catch(Exception e){  
            e.printStackTrace();  
        }  
        finally{  
            try{  
                output.close();  
            }  
            catch(Exception e){  
                e.printStackTrace();  
            }  
        }  
        return file;  
    }  
    
    public static String getFilePathFromUri(Context context, Uri uri,
            String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        Cursor cursor = context.getContentResolver().query(uri, projection,
                selection, selectionArgs, sortOrder);
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(index);
        cursor.close();
        cursor = null;
        return path;
    }
}  
