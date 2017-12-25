package org.great.mapper;

import java.util.List;
/**基础Dao接口*/
public interface BaseMapper {

	//查找所有
	public List<?> findAll();
	//增加一个，通过bean
	public void addOneByBean(Object bean);
	//删除一个，通过bean
	public void deleteOneByBean(Object bean);
	//删除一个，通过id
	public void deleteOneById(String id);
	//修改一个，通过bean
	public Integer alterOneByBean(Object bean);
}
