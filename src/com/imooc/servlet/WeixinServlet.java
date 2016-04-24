package com.imooc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.imooc.po.TextMessage;
import com.imooc.util.CheckUtil;
import com.imooc.util.MessageUtil;
import com.imooc.util.WeixinUtil;

public class WeixinServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取四个参数
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		PrintWriter pw = resp.getWriter();

		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			pw.print(echostr);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> map;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = resp.getWriter();
		try {
			map = MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");


			String message = null;
			if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
				if ("1".equals(content)) {
					message = MessageUtil.initNewsMessage(toUserName, fromUserName);
				}
				if ("2".equals(content)) {
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.secondtMenu());
				}
				if ("3".equals(content)) {
					message = MessageUtil.initImageMessage(toUserName, fromUserName);
				}
				if ("4".equals(content)) {
					message = MessageUtil.initMusicMessage(toUserName, fromUserName);
				}
				if ("5".equals(content)) {
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.fiveMenu());
				}
				if ("?".equals(content) || "？".equals(content)) {
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if(content.startsWith("翻译")){
					String word = content.replaceAll("^翻译", "").trim();
					if("".equals(word)){
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.fiveMenu());
					}else{
						message = MessageUtil.initText(toUserName, fromUserName, WeixinUtil.translateFull(word));
					}
				}
	
			} else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
				String eventType = map.get("Event");
				if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
					message = MessageUtil.initNewsMessage(toUserName, fromUserName);
				}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
					String url=map.get("EventKey");
					message=MessageUtil.initText(toUserName, fromUserName, url);
				}else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
					String key=map.get("EventKey");
					message=MessageUtil.initText(toUserName, fromUserName, key);
				}                          
			}else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				String label=map.get("Label");
				message=MessageUtil.initText(toUserName, fromUserName, label);
				
			}
			System.out.println(message);
			pw.print(message);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			pw.close();
		}


	}

}
