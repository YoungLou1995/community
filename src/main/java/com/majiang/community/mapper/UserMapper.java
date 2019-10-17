package com.majiang.community.mapper;

import com.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into users (name, account_id, token, gmt_create, gmt_modified) values(#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void inset(User user);

    @Select("select * from users where token = #{token}")
    User findByToken(@Param("token") String token);
}
