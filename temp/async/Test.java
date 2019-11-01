package com.augmentum.hrrs.parser.fileparser.async;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final List<String> inputFilePaths = new ArrayList<String>();
		inputFilePaths.add("C:/Users/klein.zhou/Desktop/backup/resume/J2/张生辉.docx");
		inputFilePaths.add("C:/Users/klein.zhou/Desktop/backup/resume/J2/蔡泽翔.docx");
		inputFilePaths.add("C:/Users/klein.zhou/Desktop/backup/resume/J2/夏莹莹.docx");
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					String result = CallSofficeSemaphore.toConvert(inputFilePaths.get(0));
					System.out.println(result);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
		System.out.println("er");
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					String result = CallSofficeSemaphore.toConvert(inputFilePaths.get(1));
					System.out.println(result);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					String result = CallSofficeSemaphore.toConvert(inputFilePaths.get(2));
					System.out.println(result);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
	}

}
