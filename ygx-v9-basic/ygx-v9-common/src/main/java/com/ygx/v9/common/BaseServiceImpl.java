package com.ygx.v9.common;

import java.util.List;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    public abstract IBaseDao<T> getBaseDao();

    public int deleteByPrimaryKey(Integer id) {
        return getBaseDao().deleteByPrimaryKey(id);
    }

    public int insert(T t) {
        return getBaseDao().insert(t);
    }

    public int insertSelective(T t) {
        return getBaseDao().insertSelective(t);
    }

    public T selectByPrimaryKey(Integer id) {
        return getBaseDao().selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T t) {
        return getBaseDao().updateByPrimaryKeySelective(t);
    }

    public int updateByPrimaryKey(T t) {
        return getBaseDao().updateByPrimaryKey(t);
    }

    public List<T> list() {
        return getBaseDao().list();
    }
}
