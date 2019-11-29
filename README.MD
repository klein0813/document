## 微信小程序

### 组件
* 组件通信：
```
<!-- 在自定义组件中 -->
<button bindtap="onTap">点击这个按钮将触发“myevent”事件</button>
```
```
Component({
  properties: {},
  methods: {
    onTap: function(){
      var myEventDetail = {} // detail对象，提供给事件监听函数
      var myEventOption = {} // 触发事件的选项
      this.triggerEvent('myevent', myEventDetail, myEventOption)
    }
  }
})
```

### 异步方法
* 方式一：() => ().then(() => ())
```
const deleteComment = ({
  commentId
}) => {
  let app = getApp()
  return app.request.delete(app.urls.nikehblCommentDelete + commentId).then((res) => {
    console.log(res)
    if (res && res.code === 200) {
      return true
    } else {
      return false
    }
  })
}
```

* 方式二: new Promise()
