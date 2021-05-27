package com.example.transactiontest.mapper

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserMapper {

    @Insert("insert into aa(id, name) values (#{id}, #{name})")
    fun insert(id: Int, name: String)

}