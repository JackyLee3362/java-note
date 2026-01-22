package edu.note.impl;

import org.springframework.stereotype.Component;

import edu.note.Flow;

/**
 * @author jackylee
 * @date 2026-01-14 10:50
 */
@Component
public class LogoutFlowImpl implements Flow {

    @Override
    public void process() {
        System.out.println("执行登出流程");
    }

}
