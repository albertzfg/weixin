package weixin;

import java.security.Policy.Parameters;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.sql.Update;

import com.hsp.domain.Areaconfig;
import com.hsp.domain.DsAlsAlarmsTab;
import com.hsp.domain.Employee;
import com.hsp.domain.NewAlarms;

import UtilTools.WxUtil;
import textmessage.WxTextMessage;
public class test2 {

	public static void main(String[] args) {

	
      
      /*
       * 1.每30秒循环执行一次
       * 2.第一次只
       */
      
//      TimerTask task=new TimerTask() {
//		
//		@Override
//		public void run() {
			// TODO Auto-generated method stub
			 SessionFactory sessionFactory;
		     Session session = null;
		     Transaction tx;
		     //创建配置对象
		     Configuration cfg=new Configuration().configure();
		     //创建会话工厂对象
		      sessionFactory=cfg.buildSessionFactory();
		     //创建会话对象
			try {
			 	   session=sessionFactory.openSession();
			        //开启事物
			        tx=session.beginTransaction();
			        String hql="from NewAlarms ns where ns.localDtsGoneOff is null ";
			        Query query=session.createQuery(hql);
			        List<NewAlarms> list=query.list();
			        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			      //  tx.commit();
			        
			        if (list.size()==0){
			        	//没有未解决的故障则什么都不做
			            
			        }
			        else{
			        	//有未解决的故障则进行刷新提醒
			        	//1.该故障在故障记录中是否解决，解决则更新gone_off_timestamp ,未解决则再次发送提醒
			        	//2.首次全发，以后根据时间差推送，不同时间差+故障区域选择不同的发送组
			        	/*发送次数<=2 ,只发送给维修工和模块长		10分钟以内
			        	 * <2发送次数<=3，发送给维修工，模块长，技术员    超过10分钟
			        	 * 计算次数如果次数>=4，表明超过15分钟，抄送K3,工长  超过15分钟
			        	 */
			        	
			        		
			        for (NewAlarms alarms :list) {
			        	//System.out.println(alarms.getId());
			        	String hql2="from DsAlsAlarmsTab as ds  where ds.id="+alarms.getId();
			        	
			        	//Object []parameters={alarms.getId()};
//			        	for (int j = 0; j < parameters.length; j++) {
//			    			System.out.println(parameters[j]);
//			    		}
			    		//注入？值
			    		//System.out.println("统一查询");
			        	query =session.createQuery(hql2);

//			    		if(parameters!=null && parameters.length>0)
//			    		{
//			    			for (int j = 0; j < parameters.length; j++) {
//			    				query.setParameter(j,parameters[j]);
//			    			}
//			    		}
//			        	System.out.println(hql2);

			    		List<DsAlsAlarmsTab> list2=query.list();
			        	
			        	
			    		//故障记录在历史记录中查到了
			        	if (list2.size()!=0) {
			        		//如果未被消除 
			        		//1.故障通知次数+1
			        		//2.如果发送次数=0，则直接查看区域发送，
			        		//2.如果发送次数<>0，则查看时间，查看区域，发送故障通知
			        		if(list2.get(0).getLocalDtsGoneOff()==null)
			        		{
								String Receiver = "";

			        			System.out.println("故障没有解决");
			        			
			        			hql="Update NewAlarms ns set ns.sendCounter=ns.sendCounter+1 where ns.id="+list2.get(0).getId();
			                	query =session.createQuery(hql);
			                	query.executeUpdate();
			                int time_differ=(int) (((new Date().getTime())-(alarms).getLocalDtsNewOn().getTime())/(1000*60));
			                  String context="故障文本："+alarms.getAreakey()+" "+alarms.getAlarmtext()+"    "
			            		+ "故障时间：" + ((new Date().getTime())-(alarms).getLocalDtsNewOn().getTime())/(1000*60)+"分钟"+"   第"+alarms.getSendCounter()+"次推送\r\n";
			               	
			                  String corpId="wwa17b784b007b2a30" ;
			                  String corpsecret="xPKp5hZbiAg7BAYNsM9Cibzhe4vy2LH4S94tzGPBY-o";
			                 
			                	  if (time_differ<10&&alarms.getSendCounter()==0) {
										

			                		  
			                		  Receiver="ZhuFengGuang";
										
										
									}
									else if (time_differ>10&&time_differ<=15&&alarms.getSendCounter()==1) {
										// Receiver="ZhuFengGuang|ZhouJie";
										 Receiver="ZhuFengGuang";


									} 
									else if (time_differ>15&&time_differ<=20&&alarms.getSendCounter()==2) {
										 //Receiver="ZhuFengGuang|ZhouJie|ZhangFen";
										 Receiver="ZhuFengGuang";


									}else {
										//Receiver=null;
										 //Receiver="ZhuFengGuang|ZhouJie|ZhangFen";
										
										hql="from Areaconfig  acfg where acfg.groupkey='"+alarms.getIonode()+"'";
										//System.out.println(hql);
										query =session.createQuery(hql);
					                	List<Areaconfig> list3=query.list();
					                	
										hql="from Employee  emp where emp.section='"+list3.get(0).getSection() +"' and emp.onDuty=true ";
					                	query =session.createQuery(hql);
					                	List<Employee> list4=query.list();
					                	for (Employee emp :list4) 
					                	{
					                		Receiver=Receiver+""+emp.getEmpName()+"|";
					                	}
					                	System.out.println(list3.get(0).getSection().length());
					                	System.out.println("WAX       ".equals(list3.get(0).getSection()));
					                	if("WAX".equals(list3.get(0).getSection()))
					                	{
					                		Receiver+="ZhuFengGuang";
					                	}
										//Receiver="ZhangFen|ZhuFengGuang";
				                		System.out.println(Receiver);



									}
							
			               
			                  
			                  WxTextMessage tWxTextMessage=new WxTextMessage("ZhuFengGuang", null, null, "text", "1000003", context, "0");
			        	
			        	WxUtil util=new WxUtil();
			        	System.out.println("txtJSON      "+tWxTextMessage.toJsonStr());
			        	System.out.println("发送消息"+util.sendReqMsg( tWxTextMessage.toJsonStr(),corpId,corpsecret));
			            
			                	

			        			
			        		}
			        		//如果消除了，则
			        		//1.将该记录的LocalDtsGoneOff更新到故障记录中，
			        		//2.发送通知，告知故障解决
			        		else {
			        			System.out.println("故障解决了");
			        			hql="Update NewAlarms ns set ns.localDtsGoneOff='"+list2.get(0).getLocalDtsGoneOff()+"'"+" where ns.id="+alarms.getId();
			                	query =session.createQuery(hql);
			                	query.executeUpdate();
			                	 String context="该故障已解决："+alarms.getAreakey()+" "+alarms.getAlarmtext()+"    "
			                     		+ "故障时间：" + ((new Date().getTime())-(alarms).getLocalDtsNewOn().getTime())/(1000*60*60)+"小时"+"   第"+alarms.getSendCounter()+"次推送\r\n";
			                        	
			                           String corpId="wwa17b784b007b2a30" ;
			                           String corpsecret="xPKp5hZbiAg7BAYNsM9Cibzhe4vy2LH4S94tzGPBY-o";
			                 
			                           WxTextMessage tWxTextMessage=new WxTextMessage("ZhuFengGuang", null, null, "text", "1000003", context, "0");
			                 	
			                 	WxUtil util=new WxUtil();
			                 	System.out.println("txtJSON      "+tWxTextMessage.toJsonStr());
			                 	System.out.println("发送消息"+util.sendReqMsg( tWxTextMessage.toJsonStr(),corpId,corpsecret));
			                     
			                         	
			        			
								
							}
							
						}
			        	//如果该记录没有找到，则删除该记录
			        	else {
							
			        		System.out.println("故障记录未找到");
			    			System.out.println(alarms.getId());

			    			hql="Delete NewAlarms ns where ns.id="+alarms.getId();
			            	query =session.createQuery(hql);
			            	query.executeUpdate();
						}
			        	
			        	
			        	

			    
			    
			    
			    
			    
					}
			        }
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			    finally {
					if (session !=null) {
						if (session.isOpen()) {
							session.close();
						}
					}
				}
			     
		}
	//};
	
	
	
//	
//	Timer timer =new Timer();
//	long delay=0;
//	long intevalPeriod = 600*1000;
//	timer.scheduleAtFixedRate(task, delay, intevalPeriod);
    
    

 

	}

   
       



//}
