package com.hand.prod.common.utils;

import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisExceptionTranslator;

import javax.sql.DataSource;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-10-12 21:33
 */
public class ExceptionUtil {

    public ExceptionUtil() {

    }

    public static Throwable sqlExceptionTrans(DataSource dataSource, RuntimeException ex) {
        return (Throwable)(ex instanceof PersistenceException ? (new MyBatisExceptionTranslator(dataSource, true)).translateExceptionIfPossible(ex) : ex);
    }
}
