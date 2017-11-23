package textmessage;

import javax.swing.text.AbstractDocument.Content;

import UtilTools.WxUtil;
import mediamessage.WxMediaMessage;

public class WxTextMessage extends ReqBaseMsg {
	
	public WxTextMessage(){}
	//消息内容
	public String content;
	//表示是否是保密消息，0表示否，1表示是，默认是0
	protected String safe;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSafe() {
		return safe;
	}
	public void setSafe(String safe) {
		this.safe = safe;
	}

	public static void main(String[] args) {
		String context="姓名：朱风光\r\n"
				+"手机号码：15695259662\r\n"
				+"邮箱:471039019@qq.com\r\n"
				+"ְ职位：工程师\r\n"
				+"<a href='http://baidu.com'>百度一下</a>";
		String corpId="wwa17b784b007b2a30";
		String corpsecret="xPKp5hZbiAg7BAYNsM9Cibzhe4vy2LH4S94tzGPBY-o";
	
		WxTextMessage tWxTextMessage=new WxTextMessage("ZhuFengGuang", null, null, "text", "1000003", context, "0");
		
		WxUtil util=new WxUtil();
		System.out.println("txtJSON      "+tWxTextMessage.toJsonStr());
		System.out.println("发送消息"+util.sendReqMsg( tWxTextMessage.toJsonStr(),corpId,corpsecret));
		
//		String media_id=null;
//
//		try {
//			System.out.println("上传图片");
//			System.out.println("图片media_id是：  "+util.send("image","/Users/zhufengguang/Desktop/屏幕快照.png").getString("media_id"));
//			media_id=util.send("image","/Users/zhufengguang/Desktop/屏幕快照.png").getString("media_id");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(media_id);
//		WxMediaMessage wxMediaMessage=new WxMediaMessage("ZhuFengGuang", null, null, "image", "1000003","3Yi5ixRgs_SvSNinjGC839K3JhY5bxrXtzkxAN8jhDF11yNdA_3wtOItWXYOHxm_8", "0");
//		System.out.println("media_JSON     "+wxMediaMessage.mediatoJsonStr("image"));
//		util.sendReqMsg(wxMediaMessage.mediatoJsonStr("image"),corpId,corpsecret)	;	
//		
		
	}
	
	
	//有参构造函数
	public WxTextMessage(String touser,String toparty,String totag,String msgtype,
			String agentid,String content,String safe){
		super();
		super.touser = touser;
		super.toparty = toparty;
		super.totag = totag;
		super.msgtype = msgtype;
		super.agentid = agentid;
		this.content = content;
		this.safe=safe;
	}
	
	//转换成JSON格式
	public String toJsonStr( ){StringBuffer jsonStr= new StringBuffer("{");
	StringBuffer str_tmp= new StringBuffer("");
	if(null!=touser && !"".equals(touser)){
		if(!"".equals(str_tmp.toString())){
			str_tmp.append(",");
		}
		str_tmp.append("\"touser\":  \""+touser+"\"");
		}
	
	if(null!=toparty && !"".equals(toparty)){
		if(!"".equals(str_tmp.toString())){
			str_tmp.append(",");
		}
		str_tmp.append("\"toparty\":  \""+toparty+"\"");
		}
	
	if(null!=totag && !"".equals(totag)){
		if(!"".equals(str_tmp.toString())){
			str_tmp.append(",");
		}
		str_tmp.append("\"totag\":  \""+totag+"\"");
		}
	

	
	
	if(null!=msgtype && !"".equals(msgtype )){
		//去除空格
		msgtype=msgtype.trim();
		//判断是否加逗号
		if(!"".equals(str_tmp.toString())){
			str_tmp.append(",");
		}
		str_tmp.append("\"msgtype\":  \""+msgtype+"\"");
		}
	
	if(null!=agentid && !"".equals(agentid)){
		if(!"".equals(str_tmp.toString())){
			str_tmp.append(",");
		}
		str_tmp.append("\"agentid\":  \""+agentid+"\"");
		}
	
	if(null!=content && !"".equals(content)){
		if(!"".equals(str_tmp.toString())){
			str_tmp.append(",");
		}
		str_tmp.append("  \"text\": {\"content\":  \""+content+"\"} ");
		}
	
	
	if(null!=safe && !"".equals(safe)){
		if(!"".equals(str_tmp.toString())){
			str_tmp.append(",");
		}
		str_tmp.append("\"safe\":  \""+safe+"\"");
		}
	
	jsonStr.append(str_tmp);
	jsonStr.append("}");
	return jsonStr.toString();
	}	

	
	
	
}
