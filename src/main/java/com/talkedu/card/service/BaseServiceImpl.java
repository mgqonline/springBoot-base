package com.talkedu.card.service;

import com.github.pagehelper.PageHelper;
import com.talkedu.card.common.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 通用服务类
 *
 * @see BaseMapper
 * 因为集成了Mybatis-PageHelper,当pageNum!= null && pageSize!= null会自动分页.@link https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md
 * Created by liaowu on 2016/12/23.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected BaseMapper<T> mapper; //泛型注入
    /*===========================基础接口CRUD BaseMapper<T> Start=============================*/

    public List<T> select(T entity) {
        return mapper.select(entity);
    }

    public T selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    public List<T> selectAll() {
        return mapper.selectAll();
    }

    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    public int selectCount(T entity) {
        return mapper.selectCount(entity);
    }

    public int insert(T entity) {
        return mapper.insert(entity);
    }

    public int insertSelective(T entity) {
        return mapper.insertSelective(entity);
    }

    public int updateByPrimaryKey(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    public int updateByPrimaryKeySelective(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public int delete(T entity) {
        return mapper.delete(entity);
    }

    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }
    /*===========================基础接口CRUD BaseMapper<T> End=============================*/

    /*===========================Example接口 ExampleMapper<T> Start=============================*/
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    public int updateByExample(@Param("record") T record, @Param("example") Object example) {
        return mapper.updateByExample(record, example);
    }

    public int updateByExampleSelective(@Param("record") T record, @Param("example") Object example) {
        return mapper.updateByExampleSelective(record, example);
    }

    public int deleteByExample(Object example) {
        return mapper.deleteByExample(example);
    }
    /*===========================Example接口 ExampleMapper<T> End=============================*/

    /*===========================Condition接口 ConditionMapper<T> Start=============================*/

    public List<T> selectByCondition(Object condition) {
        return mapper.selectByCondition(condition);
    }

    public int selectCountByCondition(Object condition) {
        return mapper.selectCountByCondition(condition);
    }

    public int updateByCondition(@Param("record") T record, @Param("example") Object condition) {
        return mapper.updateByCondition(record, condition);
    }

    public int updateByConditionSelective(@Param("record") T record, @Param("example") Object condition) {
        return mapper.updateByConditionSelective(record, condition);
    }

    public int deleteByCondition(Object condition) {
        return mapper.deleteByCondition(condition);
    }
    /*===========================Condition接口 ConditionMapper<T> End=============================*/

    /*===========================RowBounds接口 RowBoundsMapper<T> Start=============================*/
    public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
        return mapper.selectByRowBounds(record, rowBounds);
    }

    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }
    /*===========================RowBounds接口 RowBoundsMapper<T> End=============================*/

    /*===========================自定义接口 MyMapper<T> Start=============================*/
    public List<T> selectPage(int pageNum, int pageSize, T entity) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.select(entity);
    }

    public List<T> selectPageByExample(int pageNum, int pageSize, Object example) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.selectByExample(example);
    }

    public List<T> selectPageByExampleAndRowBounds(int pageNum, int pageSize, Object example, RowBounds rowBounds) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }
    /*===========================自定义接口 MyMapper<T> End=============================*/

    /*===========================Ids接口 IdsMapper<T> Start=============================*/
    public List<T> selectByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    public int deleteByIds(String ids) {
        return mapper.deleteByIds(ids);
    }
    /*===========================Ids接口 IdsMapper<T> End=============================*/


    @Override
    public int deleteByPrimaryKeys(String ids) {
        if (ids != null) {
            ids = ids.replace("-", ",");
        }
        return deleteByIds(ids);
    }
}
