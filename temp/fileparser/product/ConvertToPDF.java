package com.augmentum.hrrs.parser.fileparser.product;

import com.augmentum.hrrs.parser.fileparser.utils.ConvertUtil;
import com.augmentum.hrrs.parser.fileparser.utils.TypeConstant;

public class ConvertToPDF implements Convert {

    @Override
    public void convert(String srcPath, String desPath) {
        ConvertUtil.convert(srcPath, desPath, TypeConstant.CONVERT_PDF);
    }

}
