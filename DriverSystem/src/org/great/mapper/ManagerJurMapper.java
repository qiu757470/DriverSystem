package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.ManagerJur;
import org.great.bean.ManagerJurTwo;

/** 
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��14�� ����10:13:56 
* ��˵�� 
*/
@Mapper
public interface ManagerJurMapper extends BaseMapper {
	
	//ͨ������Աid����
	public List<ManagerJur> findByManagerId(@Param("manager_id") String manager_id);
	//ͨ������Աidɾ��
	public void deleteByManagerId(@Param("manager_id") String manager_id);
	//ͨ������Ȩ�޲���
	public ManagerJur findByJurTwoId(@Param("manager_jur_two_id") String manager_jur_two_id);
	//ͨ��bean����һ��
	public void insertByBean(ManagerJur managerJur);
	//ͨ����������һ��
	public void insertManagerJur(@Param("school_manager_id")String school_manager_id, @Param("manager_jur_two_id")String manager_jur_two_id);
}
