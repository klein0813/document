## 汉字unicode
<table>
<thead>
<tr>
<th>字符集</th>
<th>字数</th>
<th>Unicode 编码</th>
</tr>
</thead>
<tbody>
<tr>
<td>基本汉字</td>
<td>20902字</td>
<td>4E00-9FA5</td>
</tr>
<tr>
<td>基本汉字补充</td>
<td>38字</td>
<td>9FA6-9FCB</td>
</tr>
<tr>
<td>扩展A</td>
<td>6582字</td>
<td>3400-4DB5</td>
</tr>
<tr>
<td>扩展B</td>
<td>42711字</td>
<td>20000-2A6D6</td>
</tr>
<tr>
<td>扩展C</td>
<td>4149字</td>
<td>2A700-2B734</td>
</tr>
<tr>
<td>扩展D</td>
<td>222字</td>
<td>2B740-2B81D</td>
</tr>
<tr>
<td>康熙部首</td>
<td>214字</td>
<td>2F00-2FD5</td>
</tr>
<tr>
<td>部首扩展</td>
<td>115字</td>
<td>2E80-2EF3</td>
</tr>
<tr>
<td>兼容汉字</td>
<td>477字</td>
<td>F900-FAD9</td>
</tr>
<tr>
<td>兼容扩展</td>
<td>542字</td>
<td>2F800-2FA1D</td>
</tr>
<tr>
<td>PUA(GBK)部件</td>
<td>81字</td>
<td>E815-E86F</td>
</tr>
<tr>
<td>部件扩展</td>
<td>452字</td>
<td>E400-E5E8</td>
</tr>
<tr>
<td>PUA增补</td>
<td>207字</td>
<td>E600-E6CF</td>
</tr>
<tr>
<td>汉字笔画</td>
<td>36字</td>
<td>31C0-31E3</td>
</tr>
<tr>
<td>汉字结构</td>
<td>12字</td>
<td>2FF0-2FFB</td>
</tr>
<tr>
<td>汉语注音</td>
<td>22字</td>
<td>3105-3120</td>
</tr>
<tr>
<td>注音扩展</td>
<td>22字</td>
<td>31A0-31BA</td>
</tr>
<tr>
<td>〇</td>
<td>1字</td>
<td>3007</td>
</tr>
</tbody>
</table>
</table>

> 链接
1. [上述表格来源](https://blog.csdn.net/qq1131221088/article/details/88711918)<br>
2. [Unicode® Character Name Index](http://www.unicode.org/charts/charindex.html)
3. [CJKRadicals.txt](http://www.unicode.org/Public/12.0.0/ucd/CJKRadicals.txt)

> 乱码判断
* 枚举说明<br>
`Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ： 4E00-9FBF：CJK 统一表意符号`
`Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS ：F900-FAFF：CJK 兼容象形文字`
`Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A ：3400-4DBF：CJK 统一表意符号扩展A`
`Character.UnicodeBlock.GENERAL_PUNCTUATION ：2000-206F：常用标点`
`Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ：3000-303F：CJK 符号和标点` `Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ：FF00-FFEF：半角及全角形式`
* 代码
```
    public static boolean isMessyCode(String string) {
        String after = string.replaceAll("\\s|\\p{P}", "");
        char[] ch = after.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < chLength; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        if (result > 0.3) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
            if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                    || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                    || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
```

> 中文编码问题
* String的getBytes()方法是得到一个系统默认的编码格式的字节数组<br>
在Java中,String的getBytes()方法是得到一个操作系统默认的编码格式的字节数组。<br>
`gbk的长度为2`<br>
`utf8的长度为3`<br>
`ISO8859的长度为1`<br>
<pre>1. ISO8859-1编码的编码表根本就不包含汉字字符<pre>