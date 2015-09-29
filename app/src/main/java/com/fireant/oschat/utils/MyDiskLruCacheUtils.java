package com.fireant.oschat.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * Created by zhangdeyi on 15/7/27.
 */
public class MyDiskLruCacheUtils {

    public static final String CACHE_OBJECT_DIR = "object";
    public static final long MAX_SIZE = 10 * 1024 * 1024;

    private static DiskLruCache getDiskLruCache(Context context, String filePath) throws IOException {
        File file = getDiskCacheDir(context, CACHE_OBJECT_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        int appVersion = SystemUtils.getAppVersionCode(context);

        return DiskLruCache.open(file, appVersion, 1, MAX_SIZE);
    }

    public static void saveObjectCache(Context context, String key, Object object) {
        ObjectOutputStream objectOutputStream = null;
        try {
            DiskLruCache diskLruCache = getDiskLruCache(context, CACHE_OBJECT_DIR);
            DiskLruCache.Editor editor = diskLruCache.edit(hashKeyForDisk(key));
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(object);
                editor.commit();
                diskLruCache.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T extends Object> T getObjectCache(Context context, String key) {

        ObjectInputStream objectInputStream = null;
        try {
            DiskLruCache diskLruCache = getDiskLruCache(context, CACHE_OBJECT_DIR);
            DiskLruCache.Snapshot snapshot = diskLruCache.get(hashKeyForDisk(key));
            if (snapshot != null) {
                InputStream inputStream = snapshot.getInputStream(0);
                objectInputStream = new ObjectInputStream(inputStream);
                Object object = objectInputStream.readObject();
                return (T) object;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void deleteObject(Context context, String key) {
        try {
            DiskLruCache diskLruCache = getDiskLruCache(context, CACHE_OBJECT_DIR);
            diskLruCache.remove(hashKeyForDisk(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageEmulated()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }


        return new File(cachePath + File.separator + uniqueName);
    }

    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
