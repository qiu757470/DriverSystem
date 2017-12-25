package org.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.ManagerJur;
import org.great.bean.ManagerJurTwo;

/** 
* @author 作者：陈伟鹏 
* @version 创建时间：2017年8月14日 上午10:13:56 
* 类说明 
*/
@Mapper
public interface ManagerJurMapper extends BaseMapper {
	
	//通过管理员id查找
	public List<ManagerJur> findByManagerId(@Param("manager_id") String manager_id);
	//通过管理员id删除
	public void deleteByManagerId(@Param("manager_id") String manager_id);
	//通过二级权限查找
	public ManagerJur findByJurTwoId(@Param("manager_jur_two_id") String manager_jur_two_id);
	//通过bean插入一条
	public void insertByBean(ManagerJur managerJur);
	//通过参数插入一条
	public void insertManagerJur(@Param("school_manager_id")String school_manager_id, @Param("manager_jur_two_id")String manager_jur_two_id);
}
