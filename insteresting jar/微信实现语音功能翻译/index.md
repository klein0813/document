###　语音功能说明
> 项目前端使用了了[微信JSSDK](https://work.weixin.qq.com/api/doc#10029)中的录音接口，结束录音后调用Jssdk的上传uploadVoice接口并且把返回的mediaId返回到后台api。在后端通过GET方式访问微信高清素材接口并下载音频，并且通过libspeex把高清素材音频(.speex文件)转化成(.wav文件)。最后调用[迅飞听写](https://www.xfyun.cn/)翻译音频文件
