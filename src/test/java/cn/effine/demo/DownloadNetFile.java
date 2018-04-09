/**
 * @author effine
 * @Date 2015年4月10日  上午11:37:01
 * @email iballader#gmail.com
 */

package cn.effine.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadNetFile {
    public static boolean saveUrlAs(String fileUrl, String savePath)/* fileUrl网络资源地址 */ {

        try {
            /* 将网络资源地址传给,即赋值给url */
            URL url = new URL(fileUrl);

            /* 此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流 */
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            DataInputStream in = new DataInputStream(
                    connection.getInputStream());

            /* 此处也可用BufferedInputStream与BufferedOutputStream 需要保存的路径 */
            DataOutputStream out = new DataOutputStream(new FileOutputStream(
                    savePath));

            /* 将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址 */
            byte[] buffer = new byte[4096];
            int count = 0;
            while ((count = in.read(buffer)) > 0)/* 将输入流以字节的形式读取并写入buffer中 */ {
                out.write(buffer, 0, count);
            }
            out.close();/* 后面三行为关闭输入输出流以及网络资源的固定格式 */
            in.close();
            connection.disconnect();
            return true;/* 网络资源截取并存储本地成功返回true */

        } catch (Exception e) {
            System.out.println(e + fileUrl + savePath);
            return false;
        }
    }

    public static void main(String[] args) {
        DownloadNetFile pic = new DownloadNetFile();/* 创建实例 */

        // 需要下载的URL
//		String photoUrl = "http://qzapp.qlogo.cn/qzapp/101204466/3DA16778FFCECCE82FAB047164E5FAEC/100";
        String photoUrl = "http://qlogo3.store.qq.com/qzone/851474818/851474818/100";

        // 截取最后/后的字符串
        String fileName = photoUrl.substring(photoUrl.lastIndexOf("/"));

        // 图片保存路径
        String filePath = "d:";

        /* 调用函数，并且进行传参 */
        @SuppressWarnings("static-access")
        boolean flag = pic.saveUrlAs(photoUrl, filePath + fileName);

        System.out.println("Run ok!\n Get URL file " + flag);
        System.out.println(filePath);
        System.out.println(fileName);
    }
}
