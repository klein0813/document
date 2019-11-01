package com.augmentum.hrrs.parser.fileparser.async;

import java.util.concurrent.Semaphore;

import com.augmentum.hrrs.core.ConfigLoader;
import com.augmentum.hrrs.parser.fileparser.ConvertFile;

public class CallSofficeSemaphore {

    /**
     * 信号量数值为1，因此每次只能一条线程执行，其他线程进入等待状态
     */
    static private final Semaphore semaphore = new Semaphore(1);

    static private String outputFileRootDir = ConfigLoader.DATA_CONVERT_RESUMES_PATH;

    public static String toConvert(String inputFilePath) throws InterruptedException {
    	System.out.println(Thread.currentThread().getId());
        semaphore.acquire();
    	System.out.println(inputFilePath);
        String outputFilePath = doTask(inputFilePath);
        semaphore.release();
    	System.out.println(Thread.currentThread().getId());
        System.out.println("CallExternalProgramSemaphore.toConvert");
        return outputFilePath;
    }

    private static String doTask(String inputFilePath) {

        return ConvertFile.convertToPDF(outputFileRootDir, inputFilePath);
    }
}
