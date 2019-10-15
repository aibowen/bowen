package com.hand.prod.common.service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hand.prod.common.exception.MyException;
import com.hand.prod.common.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-10-12 21:19
 */
public class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
    private static final Logger log = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    protected DataSource dataSource;

    public BaseService() {
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean updateById(T entity) {
        return this.checkUpdate(super.updateById(entity));
    }

    boolean checkUpdate(boolean res) {
        if (!res) {
            throw new MyException("SYS_VERSION_NUMBER_CHANGED");
        } else {
            return res;
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean updateBatchById(List<T> entityList) {
        try {
            return this.checkUpdate(super.updateBatchById(entityList));
        } catch (RuntimeException e) {
            throw (RuntimeException)ExceptionUtil.sqlExceptionTrans(this.dataSource, e);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean insertBatch(List<T> entityList) {
        try {
            return this.insertBatch(entityList);
        } catch (RuntimeException e) {
            throw (RuntimeException)ExceptionUtil.sqlExceptionTrans(this.dataSource, e);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean insertBatch(List<T> entityList, int batchSize) {
        try {
            return this.insertBatch(entityList, batchSize);
        } catch (RuntimeException e) {
            throw (RuntimeException)ExceptionUtil.sqlExceptionTrans(this.dataSource, e);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean insertOrUpdateBatch(List<T> entityList) {
        try {
            return this.insertOrUpdateBatch(entityList);
        } catch (RuntimeException e) {
            throw (RuntimeException)ExceptionUtil.sqlExceptionTrans(this.dataSource, e);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean insertOrUpdateBatch(List<T> entityList, int batchSize) {
        try {
            return this.insertOrUpdateBatch(entityList, batchSize);
        } catch (RuntimeException e) {
            throw (RuntimeException)ExceptionUtil.sqlExceptionTrans(this.dataSource, e);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean deleteById(Serializable id) {
        try {
            return super.deleteById(id);
        } catch (RuntimeException e) {
            throw (RuntimeException)ExceptionUtil.sqlExceptionTrans(this.dataSource, e);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        try {
            return super.deleteBatchIds(idList);
        } catch (RuntimeException e) {
            throw (RuntimeException)ExceptionUtil.sqlExceptionTrans(this.dataSource, e);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean delete(Wrapper<T> wrapper) {
        try {
            return super.delete(wrapper);
        } catch (RuntimeException e) {
            throw (RuntimeException)ExceptionUtil.sqlExceptionTrans(this.dataSource, e);
        }
    }
}
