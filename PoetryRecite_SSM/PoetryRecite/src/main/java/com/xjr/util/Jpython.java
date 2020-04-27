package com.xjr.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jpython {

	public String getPoetry() throws IOException, InterruptedException{

		String poetry = "";
		String[] arguments = new String[] {
				"\"D:/Download/envs/tensorflow/python.exe\" \"c:\\Users\\Raffello\\Desktop\\peotry_generate-master\\peotry_generate-master\\eval.py\"" };
		try {
			Process process = Runtime.getRuntime().exec(arguments);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
			String line = null;
			while ((line = in.readLine()) != null) {
//				System.out.println(line);
				poetry += line;
			}
			in.close();
			int re = process.waitFor();
//            System.out.println(re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return poetry;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Jpython jpython = new Jpython();
		String poetry = jpython.getPoetry();
		System.out.println(poetry);
		
	}

}
