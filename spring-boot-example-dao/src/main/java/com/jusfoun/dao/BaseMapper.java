package com.jusfoun.dao;

import com.jusfoun.mybatis.plugins.BatchInsertProvider;
import com.jusfoun.mybatis.plugins.BatchUpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * MyBatis Mapper 基类
 * Created by liutiyang on 2017/5/18.
 */
public interface BaseMapper<T, E> {

    int countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(String id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    @InsertProvider(type = BatchInsertProvider.class, method = "batchInsertSelective")
    @Options(useGeneratedKeys = false, flushCache = Options.FlushCachePolicy.TRUE)
    int batchInsertSelectiveByProvider(@Param("collection") Collection<T> collection);

    @InsertProvider(type = BatchUpdateProvider.class, method = "batchUpdateSelective")
    @Options(useGeneratedKeys = false, flushCache = Options.FlushCachePolicy.TRUE)
    int batchUpdateSelectiveByProvider(@Param("collection") Collection<T> collection);

}
