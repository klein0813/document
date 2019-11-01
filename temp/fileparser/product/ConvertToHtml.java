package com.augmentum.hrrs.parser.fileparser.product;

import com.augmentum.hrrs.parser.fileparser.utils.ConvertUtil;
import com.augmentum.hrrs.parser.fileparser.utils.TypeConstant;

public class ConvertToHtml implements Convert {

    @Override
    public void convert(String srcPath, String destPath) {
        ConvertUtil.convert(srcPath, destPath, TypeConstant.CONVERT_HTML);
    }

}
