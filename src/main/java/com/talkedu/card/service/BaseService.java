package com.talkedu.card.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 通用服务类
 * 因为集成了Mybatis-PageHelper,当pageNum!= null && pageSize!= null会自动分页.</br>
 *
 * @link https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md</br>
 * Created by liaowu on 8/28/17
 */
public interface BaseService<T> {



    /*===========================基础接口CRUD BaseMapper<T> Start=============================*/

    /**
     * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
     *
     * @param entity
     * @return
     */
    List<T> select(T entity);

    /**
     * 根据主键进行查询,必须保证结果唯一
     * 单个字段做主键时,可以直接写主键的值
     * 联合主键时,key可以是实体类,也可以是Map
     *
     * @param key
     * @return
     */
    T selectByPrimaryKey(Object key);

    /**
     * 查询全部结果，select(null)方法能达到同样的效果
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    T selectOne(T entity);

    /**
     * 统计数量
     *
     * @param entity
     * @return
     */
    int selectCount(T entity);

    /**
     * 插入一条数据
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 插入一条数据
     * 只插入不为null的字段,不会影响有默认值的字段
     *
     * @param entity
     * @return
     */
    int insertSelective(T entity);

    /**
     * 根据主键进行更新
     * 最多只会更新一条数据
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKey(T entity);

    /**
     * 根据主键进行更新
     * 只会更新不是null的数据
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKeySelective(T entity);

    /**
     * 根据实体类中字段不为null的条件进行删除,条件全部使用=号and条件
     *
     * @param entity
     * @return
     */
    int delete(T entity);

    /**
     * 通过主键进行删除
     * 最多只会删除一条数据
     * 单个字段做主键时,可以直接写主键的值
     * 联合主键时,key可以是实体类,也可以是Map
     *
     * @param key
     * @return
     */
    int deleteByPrimaryKey(Object key);
    /*===========================基础接口CRUD BaseMapper<T> End=============================*/

    /*===========================Example接口 ExampleMapper<T> Start=============================*/

    /**
     * 单表查询
     *
     * @param example 可以使用Criteria查询
     *                <br>Example example = new Example(User.class);
     *                <br>Example.Criteria criteria = example.createCriteria();
     *                <br>criteria.andEqualTo("name", "admin");
     * @return
     */
    List<T> selectByExample(Object example);

    /**
     * 根据Example条件进行查询总数
     *
     * @param example 可以使用Criteria查询
     *                <br>Example example = new Example(User.class);
     *                <br>Example.Criteria criteria = example.createCriteria();
     *                <br>criteria.andEqualTo("name", "admin");
     * @return
     */
    int selectCountByExample(Object example);

    /**
     * 根据Example条件更新实体record包含的全部属性，null值会被更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") T record, @Param("example") Object example);

    /**
     * 根据Example条件更新实体record包含的不是null的属性值
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") T record, @Param("example") Object example);

    /**
     * 通过Exmaple条件进行删除
     *
     * @param example
     * @return
     */
    int deleteByExample(Object example);
    /*===========================Example接口 ExampleMapper<T> End=============================*/

    /*===========================Condition接口 ConditionMapper<T> Start=============================*/

    /**
     * 根据Condition条件进行查询
     *
     * @param condition
     * @return
     */
    List<T> selectByCondition(Object condition);

    /**
     * 根据Condition条件进行查询总数
     *
     * @param condition
     * @return
     */
    int selectCountByCondition(Object condition);

    /**
     * 根据Condition条件更新实体record包含的全部属性，null值会被更新
     *
     * @param record
     * @param condition
     * @return
     */
    int updateByCondition(@Param("record") T record, @Param("example") Object condition);

    /**
     * 根据Condition条件更新实体record包含的不是null的属性值
     *
     * @param record
     * @param condition
     * @return
     */
    int updateByConditionSelective(@Param("record") T record, @Param("example") Object condition);

    /**
     * 根据Condition条件删除数据
     *
     * @param condition
     * @return
     */
    int deleteByCondition(Object condition);
    /*===========================Condition接口 ConditionMapper<T> End=============================*/

    /*===========================RowBounds接口 RowBoundsMapper<T> Start=============================*/

    /**
     * 根据实体属性和RowBounds进行分页查询
     *
     * @param record
     * @param rowBounds
     * @return
     */
    List<T> selectByRowBounds(T record, RowBounds rowBounds);

    /**
     * 单表查询
     *
     * @param example   可以使用Criteria查询
     *                  <br>Example example = new Example(User.class);
     *                  <br>Example.Criteria criteria = example.createCriteria();
     *                  <br>criteria.andEqualTo("name", "admin");
     * @param rowBounds
     * @return
     */
    List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds);
    /*===========================RowBounds接口 RowBoundsMapper<T> End=============================*/

    /*===========================自定义接口 MyMapper<T> Start=============================*/

    /**
     * 单表分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<T> selectPage(int pageNum, int pageSize, T entity);

    /**
     * 单表分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param example  可以使用Criteria查询
     *                 <br>Example example = new Example(User.class);
     *                 <br>Example.Criteria criteria = example.createCriteria();
     *                 <br>criteria.andEqualTo("name", "admin");
     * @return
     */
    List<T> selectPageByExample(int pageNum, int pageSize, Object example);

    /**
     * 单表分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param example   可以使用Criteria查询
     *                  <br>Example example = new Example(User.class);
     *                  <br>Example.Criteria criteria = example.createCriteria();
     *                  <br>criteria.andEqualTo("name", "admin");
     * @param rowBounds
     * @return
     */
    List<T> selectPageByExampleAndRowBounds(int pageNum, int pageSize, Object example, RowBounds rowBounds);
    /*===========================自定义接口 MyMapper<T> End=============================*/

    /*===========================Ids接口 IdsMapper<T> Start=============================*/

    /**
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
     * ids 如 "1,2,3" 这种形式的字符串，这个方法要求实体类中有且只有一个带有@Id注解的字段，否则会抛出异常。
     *
     * @param ids
     * @return
     */
    List<T> selectByIds(String ids);

    /**
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     * ids 如 "1,2,3" 这种形式的字符串，这个方法要求实体类中有且只有一个带有@Id注解的字段，否则会抛出异常。
     *
     * @param ids
     * @return
     */
    int deleteByIds(String ids);
    /*===========================Ids接口 IdsMapper<T> End=============================*/


    /**
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     * ids 如 "1-2-3" 这种形式的字符串，这个方法要求实体类中有且只有一个带有@Id注解的字段，否则会抛出异常。
     *
     * @param ids
     * @return
     */
    int deleteByPrimaryKeys(String ids);


}
