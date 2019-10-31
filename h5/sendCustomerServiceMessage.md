            try {
                Yii::$app->weConnect->sendCustomerServiceMessage($openId, $channelId, $postData);                
            } catch (Exception $e) {
                LogUtil::warn('Failed to send text message', self::LOG_CATEGORY, ['postData' => $postData]);
            }
推送消息带链接：
postData
define('KEEP_REWARD_MSG', "嗨~美妈@{NICKNAME}，你已成功领取「12分钟・重塑少女腰腹」课程兑换码，兑换码为{COURSECODE}，使用攻略：\n1，复制课程码\n2，回到Keep APP\n3，打开【个人页面】中的【我的钱包】\n4，点击优惠券，输入兑换码\n\n<a href='http://www.qq.com' data-miniprogram-appid='wxb16cf50784ae5981' data-miniprogram-path='pages/keep/index?source=keep-reward-msg'>点击还可查看详情。</a>");

