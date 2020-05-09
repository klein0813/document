### 阿里云图片处理
* 链接：`https://help.aliyun.com/document_detail/55811.html?spm=a2c4g.11186623.6.1279.22af6cd6ThwTDa`
* 缩放</br>
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/resize,h_100`
* 裁剪</br>
* * 内切圆
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/circle,r_100`
* * 裁剪
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/crop,x_100,y_50`
* * 索引切割
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/indexcrop,x_100,i_0`
* * 圆角矩形
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/rounded-corners,r_30`
* 旋转</br>
* * 自适应方向
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/f.jpg?x-oss-process=image/resize,w_100/auto-orient,0`
* * 旋转(顺时针)
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/rotate,90`
* 效果</br>
* * 模糊效果
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/blur,r_3,s_2`
* * 亮度
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/bright,50`
* * 对比度
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/contrast,-50`
* * 锐化
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/sharpen,100`
* 格式转换</br>
* * 格式转换
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/panda.png?x-oss-process=image/format,jpg`
* * 渐进显示
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/panda.png?x-oss-process=image/format,jpg/interlace,1`
* * 质量变换
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/panda.png?x-oss-process=image/resize,w_100,h_100/quality,Q_80`
* 水印
`例：http://image-demo.img-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/resize,w_400/watermark,image_cGFuZGEucG5nP3gtb3NzLXByb2Nlc3M9aW1hZ2UvcmVzaXplLFBfMzA,t_90,g_se,x_10,y_10`
* 获取图片信息
* * 获取主色调
* * 获取信息
`例：http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg?x-oss-process=image/info`

``
