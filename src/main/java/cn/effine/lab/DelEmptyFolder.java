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

	private DelEmptyFolder(){}

	/**
	 * 采用递归思想，级联删除空文件夹
	 * 
	 * @param srcFile 待操作的源文件夹
	 * @return Boolean
	 */
	public static void deleteEmptyForld(String dir) {
		File srcFile = new File(dir);
		if (srcFile.isDirectory()) {
			File[] files = srcFile.listFiles();
			if (0 != files.length) {
				for (File file : files) {
					deleteEmptyForld(file.getAbsolutePath());
				}
			} else {
				srcFile.delete();
				if(dir.equals(srcFile.getParent())){
					return;
				}
				deleteEmptyForld(srcFile.getPath());
			}
		}
	}

	public static void main(String[] args) {
		DelEmptyFolder.deleteEmptyForld("/sources/test/testdelete");
	}
}