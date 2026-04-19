package edu.note.spring.bean.anno;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2026-04-19 14:45
 */
// 延迟加载（第一次使用bean对象时，才会创建bean对象并交给ioc容器管理）
@Lazy
@Service
@Slf4j
public class LazyService {

    @PostConstruct
    public void init() {
        log.info("LazyService 延迟初始化...");
    }

}
