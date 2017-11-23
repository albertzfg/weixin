package UtilTools;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import net.sf.json.JSONObject;

public class WxUtil {
	
	public static String access_token;
	public static Date  access_token_date;
	public static long accessTokenInvalidTime=100L;
	/*
	 * 1.发送文本消息
	 */
	public JSONObject sendReqMsg(String jsonContext,String corpId,String corpsecret)
	 {
		 //消息JSON格式
		 JSONObject result =null;
		 //获得token
		 String token = getTokenFromWx(corpId ,corpsecret);
		 //System.out.println("得到的TOKEN是    "+token);
		 try {
			CloseableHttpClient httpClient=HttpClients.createDefault();
			HttpPost httpPost=new HttpPost("https://qyapi.weixin.qq.com"+
			"/cgi-bin/message/send?access_token="+token);
			//发送JSON格式的数据
			StringEntity myEntity=new StringEntity(jsonContext,ContentType.create("text/plain","UTF-8"));
			//设置需要传递的数据
			httpPost.setEntity(myEntity);
			//create a custom response handler
			
			ResponseHandler<JSONObject> responseHandler=new ResponseHandler<JSONObject>() {
				//对访问结果进行处理
				public JSONObject handleResponse(final HttpResponse response)
				throws ClientProtocolException,IOException{
					int status =response.getStatusLine().getStatusCode();
					if(status >=200 && status < 300){
						HttpEntity entity =response.getEntity();
						if(null!=entity)
						{
							String result =EntityUtils.toString(entity)	;
							//根据字符串生成JSON对象
							JSONObject resultobj = JSONObject.fromObject(result);
							return resultobj;
						}else
						{
							return null;
						}
					}else{
						throw new ClientProtocolException("Unexpected response sstatus: "+status);
					}
				}
			};
			//返回的JSON对象
			JSONObject responseBody= httpClient.execute(httpPost,responseHandler);
			System.out.print(responseBody.toString());
			result =responseBody;
			httpClient.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
		return result;
		 
	 }
	 
	 /*2.获取token  每种应用都要用到token的获取
	  * @param corpId
	  * @param corpserect
	  * @return
	  */
	 public static String getTokenFromWx(String corpId,String corpsecrect)
	 {
		 
		 //获取的标识
		 String token="";
		 //1.判断access_token是否存在，不存在则直接申请
		 //2.判断时间是否过期，过期(>7200秒)申请，否则不用请求直接返回token
		 //Date().getTime()�������غ�����
		 if(null==access_token|| "".equals(access_token)||new Date().getTime()-access_token_date.getTime()
		>=(accessTokenInvalidTime-7000L)*1000L)
			 
		{
			 System.out.print("获取accesstoken");
			CloseableHttpClient httpClient=HttpClients.createDefault();
			 try {
				//利用get形式获得token
				 HttpGet httpget = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin"
				 		+ "/gettoken?corpid="+corpId+"&corpsecret="+corpsecrect);
				 //create a custom response handler
				 ResponseHandler<JSONObject> responseHandler= new ResponseHandler<JSONObject>() {
					 public JSONObject handleResponse(final HttpResponse response)
							 throws ClientProtocolException{
						int status = response.getStatusLine().getStatusCode();
						if(status >=200 && status <300)
						{
							HttpEntity entity = response.getEntity();
							if(null!=entity)
							{
								String result="";
								
									try {
										result = EntityUtils.toString(entity);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								
								//根据字符串生成JSON对象
								JSONObject resultObj=JSONObject.fromObject(result);
								return resultObj;
							}else{
								return null;
							}
						} else{
							throw new ClientProtocolException("Unexpected responsestatus:  "+status);
						}
					}
				};
				//返回的JSON对象
				JSONObject responseBody= httpClient.execute(httpget,responseHandler);
				if(null!=responseBody && null!=responseBody.get("access_token"))
				{
					//设置全局变量
					token=responseBody.getString("access_token");
					
					//更新token有效时间
					accessTokenInvalidTime=Long.valueOf(responseBody.getLong("expires_in")+"");
					//设置全局变量
					access_token=token;
					access_token_date=new Date();
				}
				httpClient.close();
		}
		 
			
			
		 catch (Exception e) {
			// TODO: handle exception
		}
	}
		else {
			//System.out.println("获取之前的accesstoken");
			token=access_token;
		}
		// System.out.println("获得accesstoken=="+access_token_date.getTime());
		 //System.out.println("当前时间=="+new Date().getTime());

//		 System.out.println("当前时间-获得token时间= "+(new Date().getTime()-access_token_date.getTime())
//					+"����ʱ��="+(accessTokenInvalidTime-200L)*100L);
		 return token ;
	 }

/*
 * 文件上传到微信服务器
 * @param fileType 文件类型是媒体文件类型，分别有图片，语音，视频和普通文件
 * @param filepath文件路径
 * @return JSONObject
 * @throws Exception	 
 */
	 public static JSONObject send(String fileType,String filePath) throws Exception{
		 String result=null;
		 File file=new File(filePath);
		 if (!file.exists() || !file.isFile()) {
			 throw new IOException("文件不存在");
		}
		 String token = getTokenFromWx("wwa17b784b007b2a30","xPKp5hZbiAg7BAYNsM9Cibzhe4vy2LH4S94tzGPBY-o");
		 /*
		  * 第一部分
		  */
		 URL urlObj = new URL("https://qyapi.weixin.qq.com"+
					"/cgi-bin/media/upload?access_token="+token+"&type="+fileType+"");
		 HttpsURLConnection con=(HttpsURLConnection) urlObj.openConnection();
		 con.setRequestMethod("POST");//以POST方式提交表单
		 con.setDoInput(true);
		 con.setDoOutput(true);
		 con.setUseCaches(false);//以POST方式不能使用缓存
		 //设置请求头信息
		 con.setRequestProperty("Connection", "Keep-Alive");
		 con.setRequestProperty("Charset", "UTF-8");
		 //设置边界
		 String BOUNDARY = "-----------"+System.currentTimeMillis();
		 con.setRequestProperty("Content-Type", "multipart/form-data;boundary="+BOUNDARY);
		 //请求正文信息
		 //第一部分
		 StringBuilder sb=new StringBuilder();
		 sb.append("--");
		 sb.append(BOUNDARY);
		 sb.append("\r\n");
		 sb.append("Content-Disposition:form-data;name=\"media\";filename=\""+file.getName()+"\"\r\n");
		 sb.append("Content-Type:application/octet-stream\r\n\r\n");
		 byte[] head=sb.toString().getBytes("UTF-8");
		 //获得输出流
		 OutputStream out=new DataOutputStream(con.getOutputStream());
		 //输出表头
		 out.write(head);
		 //文件正文部分
		 //把文件以流文件的方式推到URL中
		 DataInputStream in=new DataInputStream(new FileInputStream(file));
		 int bytes=0;
		 byte[] bufferOut=new byte[1024];
		 while ((bytes=in.read(bufferOut))!=-1) {
			 out.write(bufferOut,0,bytes);			
		}
		 in.close();
		 //结尾部分
		 byte[] foot = ("\r\n--"+BOUNDARY+"--\r\n").getBytes("UTF-8");//定义最后数据分割线
		 out.write(foot);
		 out.flush();
		 StringBuffer buffer=new StringBuffer();
		 BufferedReader reader = null;
		 try {
			//定义bufferedreader输入流来读取URL响应
			 reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			 String line=null;
			while((line=reader.readLine())!=null)
			{
				buffer.append(line);
			}
			if(result==null)
			{
				result=buffer.toString();
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("发送POST请求出现异常"+e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		}
		 finally {
			if(reader!=null){
				reader.close();
			}
		}
		 JSONObject jsonObj=JSONObject.fromObject(result);
		 return jsonObj;
		 
	 }
/*
 * 3.发送图片消息
 */
public static boolean sendImageMsg(String token,String mediaId)
{
	boolean flag=false;
	
	try {
		//测试mediaId--------
		//首先将图片上传获得mediaId，有效时间为3天
		JSONObject json=send("image","/Users/zhufengguang/Desktop/屏幕快照.png");
		System.out.println(json.toString());
		mediaId=json.getString("media_id");
		//----------------------------
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return flag;
}
}
