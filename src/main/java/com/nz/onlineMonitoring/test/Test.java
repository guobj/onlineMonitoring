package com.nz.onlineMonitoring.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {

	private static final Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) throws IOException {
//		try{
//			System.out.println("start");
////			String[] py = new String[]{"python","/weather.py"};
//			URL url = Test.class.getClassLoader().getResource("weather.py");
////			URL url = MyTest.class.getClassLoader().getResource("conf.properties");
//			File file = new File(url.getFile());
//			String path = Test.class.getClassLoader().getResource("weather.py").getPath();
//			System.out.println(path);
//			String[] pyArr = new String[]{"python","E:\\Git_Repository\\onlineMonitoring\\target\\classes\\weather.py"};
//			Process proc = Runtime.getRuntime().exec(pyArr);
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					proc.getInputStream()));
//			String line;
//			while ((line = in.readLine()) != null) {
//				System.out.println(line);
//			}
//			in.close();
//			proc.waitFor();
//			System.out.println("end");
//		} catch (Exception e){
//			e.printStackTrace();
//		}
		python("E:\\Git_Repository\\onlineMonitoring\\target\\classes\\weather.py");
		System.out.println("asdasd");
	}
	public static String python(String pythonPath) {
		File file = new File(pythonPath);
		if (!file.exists()){
			return "python脚本不存在！";
		}

		String[] command = Arrays.copyOf(new String[]{"python", pythonPath}, + 2);

		List<Object> res = new ArrayList<Object>();
		try {
			Process process = Runtime.getRuntime().exec(command, null, null);
			process.waitFor();

			Scanner scanner = new Scanner(process.getInputStream());
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				res.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "success";
	}

}

//class ClearThread extends Thread {
//	Process process;
//	boolean end;
//	List res;
//
//	public ClearThread(Process process) {
//		this.process = process;
//		end = false;
//		res = new ArrayList();
//	}
//
//	@Override
//	public void run() {
//		if (process == null) {
//			return;
//		}
//
//		Scanner scanner = new Scanner(process.getInputStream());
//		while (process != null && !end) {
//			while (scanner.hasNextLine()) {
//				String line = scanner.nextLine();
//				res.add(line);
//			}
//		}
//	}
//
//	public void setEnd(boolean end) {
//		this.end = end;
//	}
//
//	public List getRes() {
//		return res;
//	}
//}