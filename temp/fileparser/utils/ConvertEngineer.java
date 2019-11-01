package com.augmentum.hrrs.parser.fileparser.utils;

import com.augmentum.hrrs.parser.fileparser.product.Convert;

public class ConvertEngineer {

    private String srcPath;
    private String outPutPath;

    public ConvertEngineer(String srcPath, String outPutPath) {
        this.srcPath = srcPath;
        this.outPutPath = outPutPath;
    }

    /**
     * 文件转换处理类
     * 
     * @throws Exception
     *             exception
     */
    public void convert(Convert convert) {
        convert.convert(srcPath, outPutPath);
    }
}
