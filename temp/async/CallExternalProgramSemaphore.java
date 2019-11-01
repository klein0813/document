package com.augmentum.hrrs.parser.fileparser.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.augmentum.hrrs.core.ConfigLoader;
import com.augmentum.hrrs.parser.fileparser.ConvertFile;

public class CallExternalProgramSemaphore {

    /**
     * 信号量数值为1，因此每次只能一条线程执行，其他线程进入等待状态
     */
    static private final Semaphore semaphore = new Semaphore(1);

    static private String outputFileRootDir = ConfigLoader.DATA_CONVERT_RESUMES_PATH;

    public static List<String> toConvert(List<String> inputFilePaths, List<String> specialStrings) throws InterruptedException {
        semaphore.acquire();
        List<String> outputFilePaths = doTask(inputFilePaths, specialStrings);
        semaphore.release();

        System.out.println("CallExternalProgramSemaphore.toConvert");
        return outputFilePaths;
    }

    private static List<String> doTask(List<String> inputFilePaths, List<String> specialStrings) {

        return ConvertFile.convertToPDFs(outputFileRootDir, inputFilePaths, specialStrings);
    }
}
