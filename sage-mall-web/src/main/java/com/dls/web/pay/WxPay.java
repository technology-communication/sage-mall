package com.dls.web.pay;

import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

/**
 * @author 张金行
 * @Title: WxPay
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/03/12 16:51
 */
public class WxPay {
    public static void main(String[] args) {
        //微信支付配置
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId("wx9e08ab39c822eebc");          //公众号Id
        wxPayConfig.setAppSecret("3197761432b0d063f5e4acbec7e29bee");
       // wxPayConfig.setKeyPath();
/*        wxPayConfig.setMiniAppId("xxxxx");      //小程序Id
        wxPayConfig.setAppAppId("xxxxx");       //移动AppId*/
//支付商户资料
        wxPayConfig.setMchId("1536597581");
   /*     wxPayConfig.setMchKey("xxxxxxx");*/
        wxPayConfig.setNotifyUrl("http://zjmlkj.cn");

/*
//支付宝配置
        AliPayConfig aliPayConfig = new AliPayConfig();
        aliPayConfig.setAppId("xxxxxx");
        aliPayConfig.setPrivateKey("xxxxxx");
        aliPayConfig.setAliPayPublicKey("xxxxxx");
        aliPayConfig.setReturnUrl("http://xxxxx");
        aliPayConfig.setNotifyUrl("http://xxxxx");
*/

//支付类, 所有方法都在这个类里
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig);
/*        bestPayService.setAliPayConfig(aliPayConfig);*/

        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MP);
        payRequest.setOrderId("123456");
        payRequest.setOrderName("微信公众账号支付订单");
        payRequest.setOrderAmount(0.01);
        payRequest.setOpenid("openid_123456");
        bestPayService.pay(payRequest);

        //bestPayService.asyncNotify("");
    }
}
