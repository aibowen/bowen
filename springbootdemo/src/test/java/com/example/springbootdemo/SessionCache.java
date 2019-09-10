package com.example.springbootdemo;

import com.example.hibernate.DueDiligence;
import com.example.hibernate.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-08-20 11:19
 */
@Slf4j
public class SessionCache {

//    private Log log = LogFactory.getLog(getClass());

    @Test
    public void firstLevelCache() {
        Session session = HibernateUtil.getSesstion();
        session.beginTransaction();

        Query query = session.createQuery("from DueDiligence");
        query.list();

        DueDiligence dueDiligence = (DueDiligence) session.load(DueDiligence.class, 1049L);
        log.info(String.format("客户ID：%s", dueDiligence.getPartyId()));

        session.close();
        log.info("关闭session，开启二级缓存");
        session = HibernateUtil.getSesstion();

        DueDiligence dueDiligence1 = (DueDiligence) session.load(DueDiligence.class, 1048L);
        log.info(String.format("客户ID：%s", dueDiligence1.getPartyId()));
    }
}
