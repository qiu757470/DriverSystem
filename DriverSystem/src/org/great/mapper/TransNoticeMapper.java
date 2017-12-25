package org.great.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.NoticeAndTransuser;
import org.great.bean.Student;
import org.great.bean.TransNotice;

@Mapper
public interface TransNoticeMapper extends BaseMapper {
	public List<Student> getUnreadNotice(@Param("isRead")String isRead);
    public TransNotice selectByClickTransNoticeId(@Param("click_trans_notice_id")String click_trans_notice_id);
    public int upDateIsRead(@Param("isRead")String isRead,@Param("click_trans_notice_id")String click_trans_notice_id);
  //ͨ����У����Աidɾ��
  	public void deleteByManagerId(@Param("school_manager_id") String managerId);
  	//ͨ����У����Աid����
  	public List<TransNotice> findByManagerId(@Param("school_manager_id")String managerId);

  	//�������й������Ϣ
  	public List<TransNotice> findAllNotice(Map<String, String> map);
  	//�õ��������������
  	public String  getAllNotice();
  	//ɾ��ĳ������
  	public void deleteNoticeById(String transNoticeid);
  	//���ݹ����id���ҵ�ĳ������  �Լ��˹���Ա
  	public NoticeAndTransuser findById(String transNoticeid);
  	//���ݹ���������ҵ�ĳ������
  	public TransNotice oneNotice(String transNoticetheme);
  	//����һ������
  	public int addNoticeByBean(TransNotice transNotice);
  	//��������
  	public  List<TransNotice> searchNotice(Map<String, String> map);
  	//���������µĹ�������
  	public String getSearchNum(@Param("trans_notice_theme") String trans_notice_theme,
  			@Param("trans_notice_time") String trans_notice_time,@Param("trans_notice_province") String trans_notice_province,
  			@Param("trans_notice_city") String trans_notice_city,@Param("trans_notice_target") String trans_notice_target);
  	
  	//���ز��뵱ǰ�����id
  	public String noticeId();
  	//ͨ��ʱ�����
  	public TransNotice findByTime(@Param("trans_notice_time")String trans_notice_time);
}
