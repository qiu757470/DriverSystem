package org.great.mapper;



import org.apache.ibatis.annotations.Param;
import org.great.annotation.Mapper;
import org.great.bean.TransUser;

@Mapper
public interface TransUserMapper extends BaseMapper {

	//通过账号和密码查找
		public TransUser findTransUser(@Param("trans_user_account")String trans_user_account, @Param("trans_user_password")String trans_user_password);
		//通过账号和新密码修改
		public void revisePwdByAccount(@Param("trans_user_account")String trans_user_account, @Param("trans_user_password")String newPwd);
		//修改用户的名称、省、市
		public void alterOneByNameAndPlace(@Param("trans_user_account") String trans_user_account, @Param("trans_user_name")String alterName,
				@Param("trans_user_province")String alterProvince, @Param("trans_user_city")String alterCity, @Param("trans_user_address")String alterAddress, 
				@Param("trans_user_sex")String alterSex, @Param("trans_user_identification")String alterID, @Param("trans_user_phone")String alterPhone);

}
  