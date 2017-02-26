package com.zlht.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	public static void main(String[] args){
		for(int i=0;i<3;i++){
			System.out.println("main start....");
			ThreadTes  threadTest = new ThreadTes(i);
			threadTest.start();
		}

	}
	static class ThreadTes extends Thread{
		int i;
		public ThreadTes(int i){
			this.i=i;
		}
		@Override
		public void run(){
			System.out.println("thread start...python datax.py m"+i+".json");
			Process pr = null;
			try {
				pr = Runtime.getRuntime().exec("python datax.py m"+i+".json");
				BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					System.out.println(line);
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
