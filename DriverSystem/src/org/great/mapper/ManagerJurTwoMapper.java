package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.ManagerJur;
import org.great.bean.ManagerJurTwo;

/** 
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月14日 上午10:13:43 
* 类说明 
*/
@Mapper
public interface ManagerJurTwoMapper extends BaseMapper {

	//通过二级权限id
	public ManagerJurTwo findByJurTwoId(@Param("manager_jur_two_id")String manager_jur_two_id);
	//通过名称查找二级权限
	public ManagerJurTwo findByJurTwoName(@Param("manager_jur_two_name")String name);

	
	
}
