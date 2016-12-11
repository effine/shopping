/**
 * @author effine
 * @date 2014年6月9日  下午3:38:02
 */

package cn.effine.lab;

import java.io.File;

/**
 * 删除空文件夹工具类
 * 
 */
public class DelEmptyFolder {
	public static void main(String[] args) {
		String path = "D:\\effine";
		File file = new File(path);
		if (file.exists()) {
			if (del(file))
				System.out.println("删除成功！");
			else
				System.out.println("路径：" + path + " 及其子路径没有空文件夹！");
		} else {
			System.out.println("文件: " + path + " 不存在!");
		}
	}

	/**
	 * 采用递归思想，级联删除空文件夹
	 * 
	 * @param file
	 * @return Boolean
	 */
	public static boolean del(File file) {
		// TODO 删除子空目录，此时父空目录无法删除
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (0 != files.length) {
				for (File f : files) {
					del(f);
				}
			} else {
				file.delete();
				return true;
			}
		}
		return false;
	}
}