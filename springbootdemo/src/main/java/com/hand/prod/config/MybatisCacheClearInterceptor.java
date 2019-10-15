package com.hand.prod.config;

import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.session.SqlSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-06-28
 */
@Slf4j
public class MybatisCacheClearInterceptor implements HandlerInterceptor {
//    @Autowired
//    private SqlSession sqlSession;

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response){
//        this.sqlSession.clearCache();
//        log.info("清除缓存");
//        return true;
//    }
}
