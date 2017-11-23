package textmessage;

public abstract class ReqBaseMsg {
	/*
	 * 
	 */
	//此处为微信中添加的账号，并非个人微信号
	//成员ID(消息接收者，多个接收者用"|"分隔，最多支持1000个)
	//特殊情况：\指定@all,则向关注该企业应用的所有成员发送
	protected  String touser;
	//部门ID列表，多个接收者用"|"分隔，
	protected  String toparty;
	//标签ID，多个接收者用"|"分隔，
	protected  String totag;
	//消息类型：text,image,voice,video,news,mapnews;
	protected  String msgtype;
	//企业号应用ID
	protected  String agentid;
	//定义抽象方法，所有子类必须要实现
	
}
