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
	 * ��xmlת�����ı�
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
	 * ���ı�����ת����xml
	 */
	public static String textMessageToXml(TextMessage textMessage ){
		
		XStream xstream=new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
		
	}
	
	/*
	 * ��ʼ���ı�
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
	 * ���˵�
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb=new StringBuffer();
		sb.append("лл��Ĺ�ע�����԰���ʾ���в�����\n\n");
		sb.append("1.�����ܡ�\n");
		sb.append("2.�������֡�\n");
		sb.append("3.����ͼƬ���͡�\n");
		sb.append("4.���������\n");
		sb.append("5.���߷��빦�ܡ�\n");
		sb.append("�ظ�����ʾ���˵���");
		return sb.toString();
	}
	/*
	 * �ظ�1����ʾ������
	 */
	public static String firstMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("�Ź��٣�������۾��ǣ�");
		return sb.toString();
		
	}
	public static String secondtMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("�����������ػ����ţ������ˣ������ˣ���Щ�����ĵ�����������ʹ����ɬ�����ˣ����Σ��ջ����仨һ�����ڳ�������ʰ�����յĹ¶���ʧ�䣬һ����һ�������Ʈ�㣬̤��һ�����̣�������У����ᱳ�����ң�΢Ц�ż�����һվ���ọ́�Ѱ�������Լ��Ŀ������á� ");
		return sb.toString();
		
	}
	/*
	 * ����˵�˵��
	 */
	public static String fiveMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("ʹ��ָ��\n\n");
		sb.append("ʾ����\n");
		sb.append("��������С��\n");
		sb.append("�ظ�����ʾ���˵���");
		return sb.toString();
		
	}
	
	/*
	 * ��ͼ����Ϣת����xml��ʽ
	 */
public static String newsMessageToXml(NewsMessage newsMessage){
		
		XStream xstream=new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);
		
	}
/*
 * ��ͼƬ��Ϣת����xml��ʽ
 */
public static String imageMessageToXml(ImageMessage imageMessage){
	
	XStream xstream=new XStream();
	xstream.alias("xml", imageMessage.getClass());
	return xstream.toXML(imageMessage);
	
}

/*
 * ��������Ϣת����xml��ʽ
 */
public static String musicMessageToXml(MusicMessage musicMessage){
	
	XStream xstream=new XStream();
	xstream.alias("xml", musicMessage.getClass());
	return xstream.toXML(musicMessage);
	
}

/*
 * ͼ����Ϣ��װ
 */
public static String initNewsMessage(String toUserName,String fromUserName){
	String message=null;
	List<News> newsList=new ArrayList<>();
	NewsMessage newsMessage=new NewsMessage();
	
	News news=new News();
	news.setDescription("�����");
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
 * ��װͼƬ��Ϣ
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
 * ��װ������Ϣ
 */
public static String initMusicMessage(String toUserName,String fromUserName){
	String message=null;
	Music music=new Music();
	music.setThumbMediaId("ueQJfMI1PgWAB04wKYAfn5QcGjQPore94BirRG3heYfCEFV2-94gdPTs7wMLR0Hw");
	music.setTitle("�������");
	music.setDescription("��羭��");
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
