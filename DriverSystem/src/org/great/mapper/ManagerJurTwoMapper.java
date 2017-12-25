package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.ManagerJur;
import org.great.bean.ManagerJurTwo;

/** 
* @author ���ߣ���ΰ�� 
* @version ����ʱ�䣺2017��8��14�� ����10:13:43 
* ��˵�� 
*/
@Mapper
public interface ManagerJurTwoMapper extends BaseMapper {

	//ͨ������Ȩ��id
	public ManagerJurTwo findByJurTwoId(@Param("manager_jur_two_id")String manager_jur_two_id);
	//ͨ�����Ʋ��Ҷ���Ȩ��
	public ManagerJurTwo findByJurTwoName(@Param("manager_jur_two_name")String name);

	
	
}
