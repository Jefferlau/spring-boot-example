package com.jusfoun.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jusfoun.dao.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Service 基类
 * Created by liutiyang on 2017/5/18.
 */
public class BaseService<M extends BaseMapper<T, E>, T, E> {
    private static Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    protected M mapper;

    public int countByExample(E example){
        return mapper.countByExample(example);
    }

    public int deleteByExample(E example){
        return mapper.deleteByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        if (logger.isTraceEnabled()) {
            logger.trace(mapper.getClass().getName() + " delete record by id: " + id);
        }
        return mapper.deleteByPrimaryKey(id);
    }

    public int insert(T record) {
        return mapper.insert(record);
    }

    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    public List<T> selectByExample(E example) {
        return mapper.selectByExample(example);
    }

    public T selectByPrimaryKey(String id) {
        if (logger.isTraceEnabled()) {
            logger.trace(mapper.getClass().getName() + " select record by id: " + id);
        }
        return mapper.selectByPrimaryKey(id);
    }

    public int updateByExampleSelective(T record, E example) {
        return mapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(T record, E example) {
        return mapper.updateByExample(record, example);
    }

    public int updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    /**
     * 按条件查询一个对象
     * @param example
     * @return
     */
    public T selectOneByExample(E example) {
        T t = null;
        List<T> ts = mapper.selectByExample(example);
        if (ts != null && ts.size() > 0) {
            t = ts.get(0);
        }
        return t;
    }

    /**
     * 分页查询
     * @param example
     * @param count
     * @param pageSize
     * @return
     */
    public PageInfo<T> selectPageByExample(E example, int count, int pageSize) {
        PageHelper.startPage(count, pageSize);
        return new PageInfo<>(mapper.selectByExample(example));
    }

    /**
     * 批量插入
     * @param collection
     * @return
     */
    public int batchInsertSelective(Collection<T> collection) {
        return mapper.batchInsertSelectiveByProvider(collection);
    }

    /**
     * 批量更新
     * @param collection
     * @return
     */
    public int batchUpdateSelective(Collection<T> collection) {
        return mapper.batchUpdateSelectiveByProvider(collection);
    }

}
