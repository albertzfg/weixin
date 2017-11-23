package mediamessage;

import textmessage.ReqBaseMsg;

public class WxMediaMessage extends ReqBaseMsg {
	
	public  WxMediaMessage(){};
	
		//媒体ID
		public String media_id;
		//表示是否是保密消息，0表示否，1表示是，默认是0
		protected String safe;
		public String getMedia_id() {
			return media_id;
		}
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
		public String getSafe() {
			return safe;
		}
		public void setSafe(String safe) {
			this.safe = safe;
		}
		
		
		//有参构造函数
		public WxMediaMessage(String touser,String toparty,String totag,String msgtype,
				String agentid,String media_id,String safe){
			super();
			super.touser = touser;
			super.toparty = toparty;
			super.totag = totag;
			super.msgtype = msgtype;
			super.agentid = agentid;
			this.media_id = media_id;
			this.safe=safe;
		}
	
		//转换成JSON格式
		public String mediatoJsonStr(String type){StringBuffer jsonStr= new StringBuffer("{");
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
		

		
		
		if(null!=type && !"".equals(type )){
			//去除空格
			msgtype=msgtype.trim();
			//判断是否加逗号
			if(!"".equals(str_tmp.toString())){
				str_tmp.append(",");
			}
			str_tmp.append("\"msgtype\":  \""+type+"\"");
			}
		
		if(null!=agentid && !"".equals(agentid)){
			if(!"".equals(str_tmp.toString())){
				str_tmp.append(",");
			}
			str_tmp.append("\"agentid\":  \""+agentid+"\"");
			}
		
		if(null!=media_id && !"".equals(media_id)){
			if(!"".equals(str_tmp.toString())){
				str_tmp.append(",");
			}
			str_tmp.append("  \" "+type+"\": {\"media_id\":  \""+media_id+"\"} ");
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
