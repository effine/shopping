package cn.effine.todo.oauth.json;

	import java.security.MessageDigest;
	import java.io.*;

	public class MD5Tool {
	    //0��ASCII��
	    private static final int ASCII_0=48;
	    //9��ASCII��
	    private static final int ASCII_9=57;
	    //A��ASCII��
	    private static final int ASCII_A=65;
	    //F��ASCII��
	    private static final int ASCII_F=70;
	    //a��ASCII��
	    private static final int ASCII_a=97;
	    //f��ASCII��
	    private static final int ASCII_f=102;
	    
	    
	    //�ɱ�ʾ16�������ֵ��ַ�
	    private static final char hexChar[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	                                            'A', 'B', 'C', 'D', 'E', 'F' };
	    
	    private static final String HASH_MD5 = "MD5";
	    
	    
	    /**
	     * ��ȡ�ֽ�����MD5��
	     * @param bs
	     * @return
	     */
	    public final static String encoding(byte[] bs) {
	        
	        String encodingStr = null;
	        try {
	            MessageDigest mdTemp = MessageDigest.getInstance(HASH_MD5);
	            mdTemp.update(bs);
	            
	            return toHexString( mdTemp.digest() );
	            
	        } catch (Exception e) {
	           e.printStackTrace();
	        }

	        return encodingStr;
	    }
	    
	    /**
	     * ��ȡ�ַ���MD5��
	     * @param text
	     * @return
	     */
	    public final static String encoding(String text) {
	        if( text==null ){
	            return null;
	        }
	        return encoding( text.getBytes() );
	    }
	    
	    
	    public final static String encodeTwice(String text) {
	        if( text==null ){
	            return null;
	        }
	        String md5Once = encoding( text.getBytes() );
	        return encoding(md5Once.getBytes());
	    }
	    
	    /**
	     * ��ȡ�ļ�����MD5��
	     * @param filePath
	     * @return
	     */
	    public final static String encodingFile(String filePath){
	         InputStream fis=null;
	         try{
	             fis = new FileInputStream(filePath);
	             return encoding(fis);
	         }catch( Exception ee){
	             return null;
	         }
	         finally{
	             if(fis!=null ){
	                 try{ fis.close(); }catch( Exception ex){}
	             }
	         }
	    }
	    
	    /**
	     * ��ȡ������MD5��
	     * @param fis
	     * @return
	     * @throws Exception
	     */
	    public final static String encoding(InputStream fis) throws Exception{
	         byte[] buffer = new byte[1024];  
	         MessageDigest md5 = MessageDigest.getInstance(HASH_MD5);  
	         int numRead = 0;  
	         while ((numRead = fis.read(buffer)) > 0) {  
	             md5.update(buffer, 0, numRead);  
	         }
	         return toHexString(md5.digest());
	    }
	    
	    /**
	     * ת��Ϊ��16�����ַ���ʾ��MD5
	     * @param b
	     * @return
	     */
	    public static String toHexString(byte[] b) {  
	         StringBuilder sb = new StringBuilder(b.length * 2);  
	         for (int i = 0; i < b.length; i++) {  
	             sb.append(hexChar[(b[i] & 0xf0) >>> 4]);  
	             sb.append(hexChar[b[i] & 0x0f]);  
	         }  
	         return sb.toString();  
	    }
	    
	    /**
	     * �����Ƿ��ǺϷ���MD5��
	     * @param md5Str
	     * @return
	     */
	    public static boolean validate(String md5Str){
	        if(md5Str==null || md5Str.length()!=32 ){
	            return false;
	        }
	        byte[] by = md5Str.getBytes();
	        for( int i=0;i<by.length;i++ ){
	            int asciiValue = (int)by[i];
	            if(    asciiValue<ASCII_0
	                    || ( asciiValue>ASCII_9 && asciiValue<ASCII_A)
	                    || ( asciiValue>ASCII_F && asciiValue<ASCII_a)
	                    || asciiValue>ASCII_f ){
	                return false;
	            }
	        }
	        return true;
	    }

	    
	    
	    public static void main(String[] args) {
			System.out.println(encoding("13483724051"));
		}
}
