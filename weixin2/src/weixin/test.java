package weixin;
import java.io.IOException;
import java.util.Date;

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

class HelloWorld {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("������");

		HelloWorld helloWorld = new HelloWorld();
		String jsonContext="{"
				+"   \"touser\": \"ZhuFengGuang\"  ,"
				+"\"toparty\": \"\","
				+"\"totag\": \"\","
				+"\"msgtype\": \"text\","
				+"\"agentid\":1000003,"
				+"\"text\" :{"
				+"\"content\": \"ȥ�Է���\""
				+"},"
				+"\"safe\":0"
				+"}";
		System.out.println("\"     "+jsonContext);
		String corpId ="wwa17b784b007b2a30";
		String corpsecret="xPKp5hZbiAg7BAYNsM9Cibzhe4vy2LH4S94tzGPBY-o";
		helloWorld.sendReqMsg(jsonContext,corpId,corpsecret);
	}
	public static String access_token;
	public static Date  access_token_date;
	public static long accessTokenInvalidTime=100L;
	
	/*������Ϣ
	 * @param jsonContext  JSON �ַ�
	 * @param corpId
	 * @param corpsecret
	 * @return
	 */ 
	 public JSONObject sendReqMsg(String jsonContext,String corpId,String corpsecret)
	 {
		 //��Ϣjson��ʽ
		 JSONObject result =null;
		 //���token
		 String token = getTokenFromWx(corpId ,corpsecret);
		 System.out.println("��õ�token��"+token);
		 try {
			CloseableHttpClient httpClient=HttpClients.createDefault();
			HttpPost httpPost=new HttpPost("https://qyapi.weixin.qq.com"+
			"/cgi-bin/message/send?access_token="+token);
			//����json��ʽ������
			StringEntity myEntity=new StringEntity(jsonContext,ContentType.create("text/plain","UTF-8"));
			//������Ҫ���ݵ�����
			httpPost.setEntity(myEntity);
			//create a custom response handler
			
			ResponseHandler<JSONObject> responseHandler=new ResponseHandler<JSONObject>() {
				//�Է��ʽ�����д���
				public JSONObject handleResponse(final HttpResponse response)
				throws ClientProtocolException,IOException{
					int status =response.getStatusLine().getStatusCode();
					if(status >=200 && status < 300){
						HttpEntity entity =response.getEntity();
						if(null!=entity)
						{
							String result =EntityUtils.toString(entity)	;
							//�����ַ�����JSON����
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
			//���ص�JSON ����
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
	 
	 /*��ȡtoken
	  * @param corpId
	  * @param corpserect
	  * @return
	  */
	 public String getTokenFromWx(String corpId,String corpsecrect)
	 {
		 
		 //��ȡ��ʶ
		 String token="";
		 //1.�ж�access_token�Ƿ���ڣ���������ֱ������
		 //2.�ж�ʱ���Ƿ����(>7200s)���������룬����������ֱ�ӷ����Ժ��token
		 //Date().getTime()�������غ�����
		 if(null==access_token|| "".equals(access_token)||new Date().getTime()-access_token_date.getTime()
		>=(accessTokenInvalidTime-7000L)*1000L)
			 
		{
			 System.out.print("��������accesstoken");
			CloseableHttpClient httpClient=HttpClients.createDefault();
			 try {
				//����get��ʽ���token
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
								
								//�����ַ�������JSON����
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
				//���ص�JSON����
				JSONObject responseBody= httpClient.execute(httpget,responseHandler);
				if(null!=responseBody && null!=responseBody.get("access_token"))
				{
					//����ȫ�ֱ���
					token=responseBody.getString("access_token");
					//����token
					//����token��Чʱ��
					accessTokenInvalidTime=Long.valueOf(responseBody.getLong("expires_in")+"");
					//����ȫ�ֱ���
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
			System.out.println("��������һ�ε�accesstoken");
			token=access_token;
		}
		 System.out.println("���accesstokenʱ��=="+access_token_date.getTime());
		 System.out.println("��ǰʱ��=="+new Date().getTime());

		 System.out.println("��ǰʱ��-���tokenʱ��= "+(new Date().getTime()-access_token_date.getTime())
					+"����ʱ��="+(accessTokenInvalidTime-200L)*100L);
		 return token ;
	 }
}
