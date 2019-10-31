      bindWechatShare () {
        CONSTANT.getWechatShareOptions(this).then((options) => {
          let source = this.$route.query['source']
          let message = {
            title: CONSTANT.SHARED.TITLE,
            link: CONSTANT.genKeepCouponShareLink(source),
            imgUrl: CONSTANT.SHARED.IMG_BG,
            desc: CONSTANT.SHARED.DESCRIPTION
          }

          window.util.bindWechatShare(options, message)
        })
      }

    route: {
      data (transition) {
        this.bindWechatShare()
      }
    }

const genKeepCouponShareLink = (source = '') => {
  const accountId = getAccountId()
  let redirectUrl = `${window.location.protocol}//${window.location.host}/${accountId}/h5/danoneaptamil/index.html#!/keep-reward`
  redirectUrl = encodeURIComponent(redirectUrl)
  return `${window.location.protocol}//${window.location.host}/${accountId}/h5/danoneaptamil/index.html#!/keep-register?source=${source}&redirectUrl=${redirectUrl}`
}
