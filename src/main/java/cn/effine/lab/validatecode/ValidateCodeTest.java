package cn.effine.lab.validatecode;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

public class ValidateCodeTest {

	private static Logger logger = Logger.getLogger(ValidateCodeTest.class);


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ValidateCode vCode = new ValidateCode(120,40,5,100);
		try {
			String path="D:/t/"+new Date().getTime()+".png";
			System.out.println(vCode.getCode()+" >"+path);
			vCode.write(path);
		} catch (IOException e) {
			logger.error(e);
		}
	}

}
