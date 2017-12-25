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
  //通过驾校管理员id删除
  	public void deleteByManagerId(@Param("school_manager_id") String managerId);
  	//通过驾校管理员id查找
  	public List<TransNotice> findByManagerId(@Param("school_manager_id")String managerId);

  	//查找所有公告的消息
  	public List<TransNotice> findAllNotice(Map<String, String> map);
  	//得到公告的所有条数
  	public String  getAllNotice();
  	//删除某条公告
  	public void deleteNoticeById(String transNoticeid);
  	//根据公告的id查找到某条公告  以及运管人员
  	public NoticeAndTransuser findById(String transNoticeid);
  	//根据公告的主题找到某条公告
  	public TransNotice oneNotice(String transNoticetheme);
  	//增加一条公告
  	public int addNoticeByBean(TransNotice transNotice);
  	//搜索公告
  	public  List<TransNotice> searchNotice(Map<String, String> map);
  	//搜索条件下的公告数量
  	public String getSearchNum(@Param("trans_notice_theme") String trans_notice_theme,
  			@Param("trans_notice_time") String trans_notice_time,@Param("trans_notice_province") String trans_notice_province,
  			@Param("trans_notice_city") String trans_notice_city,@Param("trans_notice_target") String trans_notice_target);
  	
  	//返回插入当前公告的id
  	public String noticeId();
  	//通过时间查找
  	public TransNotice findByTime(@Param("trans_notice_time")String trans_notice_time);
}
