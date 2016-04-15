package chapter4;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodeDecode {
	private static final String ENC = "UTF-8";

	public static void main(String[] args) throws UnsupportedEncodingException {
		String hello = URLEncoder.encode("こんにちは", ENC);
		String japan = URLEncoder.encode("日本", ENC);
		String myBook = URLEncoder.encode("Eclipseで学ぶ初めてのJava", ENC);

		System.out.println(hello);
		System.out.println(japan);
		System.out.println(myBook);

		System.out.println(URLDecoder.decode(hello, ENC));
		System.out.println(URLDecoder.decode(japan, ENC));
		System.out.println(URLDecoder.decode(myBook, ENC));
	}
}
