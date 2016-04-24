package com.imooc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.imooc.po.Image;
import com.imooc.po.ImageMessage;
import com.imooc.po.Music;
import com.imooc.po.MusicMessage;
import com.imooc.po.News;
import com.imooc.po.NewsMessage;
import com.imooc.po.TextMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	public static final String MESSAGE_TEXT="text";
	public static final String MESSAGE_MUSIC="music";
	public static final String MESSAGE_NEWS="news";
	public static final String MESSAGE_IMAGE="image";
	public static final String MESSAGE_VOICE="voice";
	public static final String MESSAGE_VEDIO="vedio";
	public static final String MESSAGE_LINK="link";
	public static final String MESSAGE_LOCATION="location";
	public static final String MESSAGE_EVENT="event";
	public static final String MESSAGE_SUBSCRIBE="subscribe";
	public static final String MESSAGE_UNSUBSCRIBE="unsubscribe";
	public static final String MESSAGE_CLICK="CLICK";
	public static final String MESSAGE_VIEW="VIEW";
	public static final String MESSAGE_SCANCODE="scancode";
	
	/*
	 * 将xml转换成文本
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String, String> map=new HashMap<String,String>();
		SAXReader reader=new SAXReader();
		
		
		InputStream is=request.getInputStream();
		Document doc=reader.read(is);
		
		Element root=doc.getRootElement();
		
		List<Element> list=root.elements();
		
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		is.close();
		
		return map;
		
		
	}
	/*
	 * 将文本对象转换成xml
	 */
	public static String textMessageToXml(TextMessage textMessage ){
		
		XStream xstream=new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
		
	}
	
	/*
	 * 初始化文本
	 */
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text=new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	}
	
	/**
	 * 主菜单
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb=new StringBuffer();
		sb.append("谢谢你的关注！可以按提示进行操作：\n\n");
		sb.append("1.哥哥介绍。\n");
		sb.append("2.经典文字。\n");
		sb.append("3.经典图片放送。\n");
		sb.append("4.经典歌曲。\n");
		sb.append("5.在线翻译功能。\n");
		sb.append("回复？显示主菜单。");
		return sb.toString();
	}
	/*
	 * 回复1，显示的内容
	 */
	public static String firstMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("张国荣，著名香港巨星！");
		return sb.toString();
		
	}
	public static String secondtMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("人生浮沉，韶华易逝，梦醒了，情逝了，那些触及心底深处的酸楚，悲痛，苦涩，忧伤，无奈，终会像落花一样归于尘土。收拾起昔日的孤独与失落，一个人一如既往的飘零，踏着一缕青烟，随风逐行，轻轻背起行囊，微笑着继续下一站的旅程，寻找属于自己的快乐天堂。 ");
		return sb.toString();
		
	}
	/*
	 * 翻译菜单说明
	 */
	public static String fiveMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("使用指南\n\n");
		sb.append("示例：\n");
		sb.append("翻译我是小明\n");
		sb.append("回复？显示主菜单。");
		return sb.toString();
		
	}
	
	/*
	 * 把图文消息转换成xml格式
	 */
public static String newsMessageToXml(NewsMessage newsMessage){
		
		XStream xstream=new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);
		
	}
/*
 * 把图片消息转换成xml格式
 */
public static String imageMessageToXml(ImageMessage imageMessage){
	
	XStream xstream=new XStream();
	xstream.alias("xml", imageMessage.getClass());
	return xstream.toXML(imageMessage);
	
}

/*
 * 把音乐消息转换成xml格式
 */
public static String musicMessageToXml(MusicMessage musicMessage){
	
	XStream xstream=new XStream();
	xstream.alias("xml", musicMessage.getClass());
	return xstream.toXML(musicMessage);
	
}

/*
 * 图文消息组装
 */
public static String initNewsMessage(String toUserName,String fromUserName){
	String message=null;
	List<News> newsList=new ArrayList<>();
	NewsMessage newsMessage=new NewsMessage();
	
	News news=new News();
	news.setDescription("哥哥简介");
	news.setPicUrl("http://yangbo.duapp.com/weixin1/image/gege.jpg");
	news.setUrl("http://baike.baidu.com/link?url=RmXQSIowmVw_FBx4SGMjDcg4g6lzDHsjHRVetOd0fp-niIQ6AoPOOeoTYbflikYsSN2fL34Xo-R-O3fFolUcY2pu-i4BPrVBuVUxNYT_T-m");
	newsList.add(news);
	
	newsMessage.setFromUserName(toUserName);
	newsMessage.setToUserName(fromUserName);
	newsMessage.setCreateTime(new Date().getTime());
	newsMessage.setMsgType(MESSAGE_NEWS);
	newsMessage.setArticles(newsList);
	newsMessage.setArticleCount(newsList.size());
	
	message=newsMessageToXml(newsMessage);
	
	
	return message;
	
}

/*
 * 组装图片消息
 */
public static String initImageMessage(String toUserName,String fromUserName){
	String message=null;
	Image image=new Image();
	image.setMediaId("RIJvKBpKPKyX0--FSfjYZ3Yf1-c-fjFqj77r093ZhSi3TVmtemyrRGXIRTYnVyob");
	ImageMessage imageMessage=new ImageMessage();
	imageMessage.setFromUserName(toUserName);
	imageMessage.setToUserName(fromUserName);
	imageMessage.setMsgType(MESSAGE_IMAGE);
	imageMessage.setCreateTime(new Date().getTime());
	imageMessage.setImage(image);
	message=imageMessageToXml(imageMessage);
	return message;
	
}

/*
 * 组装音乐消息
 */
public static String initMusicMessage(String toUserName,String fromUserName){
	String message=null;
	Music music=new Music();
	music.setThumbMediaId("ueQJfMI1PgWAB04wKYAfn5QcGjQPore94BirRG3heYfCEFV2-94gdPTs7wMLR0Hw");
	music.setTitle("风继续吹");
	music.setDescription("哥哥经典");
	music.setMusicUrl("http://yangbo.duapp.com/weixin1/resources/fen.mp3");
	music.setHQMusicUrl("http://yangbo.duapp.com/weixin1/resources/fen.mp3");
	
	MusicMessage musicMessage=new MusicMessage();
	musicMessage.setFromUserName(toUserName);
	musicMessage.setToUserName(fromUserName);
	musicMessage.setMsgType(MESSAGE_MUSIC);
	musicMessage.setCreateTime(new Date().getTime());
	musicMessage.setMusic(music);
	message=musicMessageToXml(musicMessage);
	return message;
	
}

 

}
