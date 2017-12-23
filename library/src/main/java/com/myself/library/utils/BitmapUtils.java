package com.myself.library.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 图片保存工具类，默认存储路径是sd卡/android/pic/
 * 文件夹不存在会自动创建
 */

public class BitmapUtils {
    public final static String ROOT_PATH = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "android"
            + File.separator + "pic" + File.separator;

    public static String card = "card.jpg";
    public static String cardUpside = "cardUpside.jpg";
    public static String face = "face.jpg";
    public static String bank = "bank.jpg";
    public static String[] paths = {card, cardUpside, face, bank};
    public static String[] paths3 = {card, cardUpside, face};

    /**
     * Bitmap对象保存为图片文件
     *
     * @param bitmap
     * @param fileName
     */
    public static void saveBitmapFile(Bitmap bitmap, String fileName) {
        File mFile = new File(ROOT_PATH);
        if (!mFile.exists()) {
            mFile.mkdirs();
        }
        String file_path = ROOT_PATH + fileName;
        File file = new File(file_path);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 图片按比例大小压缩方法
     *
     * @param srcPath （根据路径获取图片并压缩）
     * @return
     */
    public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;

        float hh = 1080f;// 这里设置高度为
        float ww = 1920f;// 这里设置宽度为
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }


    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 1024) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    /**
     * 图片复制保存（复制完毕后删除老图片）
     *
     * @param path        老图片的地址
     * @param newFileName 新图片的路径
     * @param deleteOld   是否删除老图片 true=是
     */
    public static void saveFileFromPath(String path, String newFileName, boolean deleteOld) {
        File mFile = new File(ROOT_PATH);
        if (!mFile.exists()) {
            mFile.mkdirs();
        }
        String file_path = ROOT_PATH + newFileName;
        File newFile = new File(file_path);//新图片路径
        File oldFile = new File(path);      //老图片路径
        try {
            FileInputStream ins = new FileInputStream(oldFile);
            FileOutputStream out = new FileOutputStream(newFile);
            byte[] b = new byte[1024];
            int n = 0;
            while ((n = ins.read(b)) != -1) {
                out.write(b, 0, n);
            }
            ins.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (deleteOld) {
            oldFile.delete();
        }
    }


    /**
     * 删除临时存储的4张照片
     */
    public static void deleteTempFile() {
        for (int i = 0; i < paths.length; i++) {
            try {
                File mFile = new File(ROOT_PATH + paths[i]);

                mFile.delete();

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    /**
     * 准备上传，生成4个MutiPart
     *
     * @return
     */
    public static List<MultipartBody.Part> doPrepare() {
        List<MultipartBody.Part> mParts = new ArrayList<>();
        for (int i = 0; i < paths.length; i++) {
            File mFile = new File(ROOT_PATH + paths[i]);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), mFile);
            MultipartBody.Part body = MultipartBody.Part.createFormData("files", mFile.getName(), requestFile);
            mParts.add(body);
        }
        return mParts;
    }


    /**
     * 准备上传，生成3个MutiPart
     * 3张
     *
     * @return
     */
    public static List<MultipartBody.Part> doPrepare3() {
        List<MultipartBody.Part> mParts = new ArrayList<>();
        for (int i = 0; i < paths3.length; i++) {
            File mFile = new File(ROOT_PATH + paths3[i]);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), mFile);
            MultipartBody.Part body = MultipartBody.Part.createFormData("files", mFile.getName(), requestFile);
            mParts.add(body);
        }
        return mParts;
    }

    /**
     * 准备上传，生成1个MutiPart
     * 1张
     *
     * @return
     */
    public static List<MultipartBody.Part> doPrepare1() {
        List<MultipartBody.Part> mParts = new ArrayList<>();
        File mFile = new File(ROOT_PATH + bank);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), mFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("files", mFile.getName(), requestFile);
        mParts.add(body);

        return mParts;
    }
}
