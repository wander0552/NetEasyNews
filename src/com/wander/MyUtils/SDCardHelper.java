package com.wander.MyUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

public class SDCardHelper {

	// 判断SD卡是否被挂载
	public static boolean isSDCardMounted() {
		// return Environment.getExternalStorageState().equals("mounted");
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	// 获取SD卡的根目录
	public static String getSDCardBaseDir() {
		if (isSDCardMounted()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return null;
	}

	// 获取SD卡的完整空间大小，返回MB
	public static long getSDCardSize() {
		if (isSDCardMounted()) {
			StatFs fs = new StatFs(getSDCardBaseDir());
			long count = fs.getBlockCountLong();
			long size = fs.getBlockSizeLong();
			return count * size / 1024 / 1024;
		}
		return 0;
	}

	// 获取SD卡的剩余空间大小
	public static long getSDCardFreeSize() {
		if (isSDCardMounted()) {
			StatFs fs = new StatFs(getSDCardBaseDir());
			long count = fs.getFreeBlocksLong();
			long size = fs.getBlockSizeLong();
			return count * size / 1024 / 1024;
		}
		return 0;
	}

	// 获取SD卡的可用空间大小
	public static long getSDCardAvailableSize() {
		if (isSDCardMounted()) {
			StatFs fs = new StatFs(getSDCardBaseDir());
			long  count = fs.getAvailableBlocksLong();
			long  size = fs.getBlockSizeLong();
			return count * size / 1024 / 1024;
		}
		return 0;
	}

	// 往SD卡的公有目录下保存文件
	public static boolean saveFileToSDCardPublicDir(byte[] data, String type,
			String fileName) {
		BufferedOutputStream bos = null;
		if (isSDCardMounted()) {
			File file = Environment.getExternalStoragePublicDirectory(type);
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(
						file, fileName)));
				bos.write(data);
				bos.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	// 往SD卡的自定义目录下保存文件
	public static boolean saveFileToSDCardCustomDir(byte[] data, String dir,
			String fileName) {
		BufferedOutputStream bos = null;
		if (isSDCardMounted()) {
			File file = new File(getSDCardBaseDir() + File.separator + dir);
			if (!file.exists()) {
				file.mkdirs();// 递归创建自定义目录
			}
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(
						file, fileName)));
				bos.write(data);
				bos.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	// 往SD卡的私有Files目录下保存文件
	public static boolean saveFileToSDCardPrivateFilesDir(byte[] data,
			String type, String fileName, Context context) {
		BufferedOutputStream bos = null;
		if (isSDCardMounted()) {
			File file = context.getExternalFilesDir(type);
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(
						file, fileName)));
				bos.write(data);
				bos.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	// 往SD卡的私有Cache目录下保存文件
	public static boolean saveFileToSDCardPrivateCacheDir(byte[] data,
			String fileName, Context context) {
		BufferedOutputStream bos = null;
		if (isSDCardMounted()) {
			File file = context.getExternalCacheDir();
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(
						file, fileName)));
				bos.write(data);
				bos.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	// 从SD卡获取文件
	public static byte[] loadFileFromSDCard(String fileDir) {
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			bis = new BufferedInputStream(
					new FileInputStream(new File(fileDir)));
			byte[] buffer = new byte[8 * 1024];
			int c = 0;
			while ((c = bis.read(buffer)) != -1) {
				baos.write(buffer, 0, c);
				baos.flush();
			}
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 获取SD卡公有目录的路径
	public static String getSDCardPublicDir(String type) {
		return Environment.getExternalStoragePublicDirectory(type).toString();
	}

	// 获取SD卡私有Cache目录的路径
	public static String getSDCardPrivateCacheDir(Context context) {
		return context.getExternalCacheDir().getAbsolutePath();
	}

	// 获取SD卡私有Files目录的路径
	public static String getSDCardPrivateFilesDir(Context context, String type) {
		return context.getExternalFilesDir(type).getAbsolutePath();
	}

}
