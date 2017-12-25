package org.great.mapper;

import java.util.List;
/**����Dao�ӿ�*/
public interface BaseMapper {

	//��������
	public List<?> findAll();
	//����һ����ͨ��bean
	public void addOneByBean(Object bean);
	//ɾ��һ����ͨ��bean
	public void deleteOneByBean(Object bean);
	//ɾ��һ����ͨ��id
	public void deleteOneById(String id);
	//�޸�һ����ͨ��bean
	public Integer alterOneByBean(Object bean);
}
