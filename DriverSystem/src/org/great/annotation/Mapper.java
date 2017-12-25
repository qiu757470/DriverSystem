package org.great.annotation;

import org.springframework.stereotype.Repository;
/** 
* @author 作者：陈伟鹏
* @version 创建时间：2017年8月10日
* 类说明 ：自定义注解，用户Spring容器Mapper包中过滤其他注解
*/
@Repository
public @interface Mapper {
//MyAnnotation相当于是注解器
}
