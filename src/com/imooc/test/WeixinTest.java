package com.imooc.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.http.ParseException;

import com.imooc.po.AccessToken;
import com.imooc.util.WeixinUtil;

import net.sf.json.JSONObject;

//票据XS_oybnm7eLQdd9KDc_SkkHA5FJSx5ed_h2Vvg1jyzHc_2ThqImkYL9UHW0sK0ejaFGY4GYsdmH4l_vd2sqRHEPWU9T3bqo8AN_6lpguB9Au2HCEzm62yfwONYM8mnF-CQXbAJATHO
//mediaId cPId0AsfVG5UuA85xA0XM2GymH0LkObGs8U7rZvoosbrjdzEMRHv_PUUujgTV9PG
//musicId -EmoTBM9164JXAcZLxvrTR8baFwpCdxeyz2HJxLzLcxGEclPaldByQn-LsM1W-e_
public class WeixinTest {
	public static void main(String[] args) throws ParseException, IOException {
		try{
		AccessToken token=WeixinUtil.getAccessToken();
		
		System.out.println("票据："+token.getToken());
		System.out.println("有限时间："+token.getExpiresIn());
		

//		String path="D:/gege.jpg";
//		String mediaId=WeixinUtil.upload(path, token.getToken(), "image");
//		System.out.println(mediaId);	
//		String path="D:/fen.jpg";
//		String mediaId=WeixinUtil.upload(path, token.getToken(), "thumb");
//		System.out.println(mediaId);	
		String menu=JSONObject.fromObject(WeixinUtil.initMenu()).toString();
		int result=WeixinUtil.createMenu(token.getToken(), menu);
		if(result==0){
			System.out.println("菜单创建成功");
		}else{
			System.out.println("错误码："+result);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
