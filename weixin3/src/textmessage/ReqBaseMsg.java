package textmessage;

public abstract class ReqBaseMsg {
	/*
	 * 
	 */
	//�˴�Ϊ΢������ӵ��˺ţ����Ǹ���΢�ź�
	//��ԱID(��Ϣ�����ߣ������������"|"�ָ������֧��1000��)
	//���������\ָ��@all,�����ע����ҵӦ�õ����г�Ա����
	protected  String touser;
	//����ID�б������������"|"�ָ���
	protected  String toparty;
	//��ǩID�������������"|"�ָ���
	protected  String totag;
	//��Ϣ���ͣ�text,image,voice,video,news,mapnews;
	protected  String msgtype;
	//��ҵ��Ӧ��ID
	protected  String agentid;
	//������󷽷��������������Ҫʵ��
	
}
