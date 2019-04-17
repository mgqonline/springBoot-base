package com.talkedu.card.common;


import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 *
 * BaseMapper
 * 所有mapper继承该接口,特别注意该接口不能被扫描到，否则会出错
 *  @link https://github.com/abel533/Mapper/blob/master/wiki/mapper3/5.Mappers.md
 */
public interface BaseMapper<T> extends Mapper<T>, IdsMapper<T>, MySqlMapper<T>,ConditionMapper<T> {
}
