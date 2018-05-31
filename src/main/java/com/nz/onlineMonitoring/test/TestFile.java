package com.nz.onlineMonitoring.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

public class TestFile {
	public static void main(String[] args) throws IOException

	{

//		URL url = TestFile.class.getClassLoader().getResource("weather.py");
//		File f = new File(url.getFile());
////		File file = new File("/mybatis-config.xml");
//		InputStream in = TestFile.class.getClassLoader().getResourceAsStream("weather.py");
////		InputStreamReader in = new InputStreamReader(new FileInputStream(file));
//		byte[] b = new byte[1024];
////		BufferedReader bfReader = new BufferedReader(in);
//		Integer line;
//		while((line = in.read(b)) != -1){
//			String str = new String(b);
//			System.out.println(str);
//		}
//		bfReader.close();

		String[] s1 = new String[]{"1","2","3","4","5","6"};
		String[] s2 = Arrays.copyOf(s1,3);
		String[] s3 = new String[]{"7","8","9","10"};
		System.out.println(Arrays.asList(s2));

		System.arraycopy(s1,0,s3,1,3);
		System.out.println(Arrays.asList(s3));
	}

	public File test(){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("weather.py").getFile());
		return file;
	}
}
