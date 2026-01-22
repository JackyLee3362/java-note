package edu.note;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2026-01-14 10:51
 */
@Slf4j
@Component
public class FlowExecutor {

    @Autowired(required = false)
    private List<Flow> flowList;

    public List<Flow> getFlowList() {
        return flowList;
    }

}
