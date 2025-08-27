package com.sankuai.meituan.ccp.marketing.platform.domain.model;

import java.util.Map;
import lombok.Data;

/**
 * 事件
 * @author yueyali
 * @version 1.0
 * @created 2018/7/2 下午3:37
 **/
@Data
public class MarketingEvent {

    /**
     * 事件ID
     * 事件的唯一ID，用于消费方幂等处理
     * 主键
     */
    private Long id;

    /**
     * 用户Id
     * 可空
     */
    private Long userId;

    /**
     * 用户体系类型，必填
     * 可参考ccp-framework中TUserType定义
     * 可空
     */
    private Integer userType;

    /**
     * 客户号
     * 来自客户中心，做金融业务之前没有客户号。
     * 可空
     */
    private String customerNo;

    /**
     * 时间戳(毫秒)	必填
     * 不可空
     */
    private Long timestamp;

    /**
     * 产品ID	必填
     * 事件中心定义，金融内部的与ccp-framework定义映射

     例如：生活费、联名卡、零花积分
     不可空
     */
    private String productId;

    /**
     * 事件类型	必填
     * 统一定义的枚举
     * 不可空
     * @see com.sankuai.meituan.ccp.marketing.platform.enums.EventTypeEnum
     */
    private Integer type;

    /**
     * 目标
     * 产品的一个实例

     例如：银行卡号、零花账户、生活费账户等
     可空
     */
    private String target;

    /**
     * 业务序列号	必填
     * 业务流水序列号，做事件中心与业务操作的幂等
     * 不可空
     */
    private String serialNum;

    /**
     * 退达标源编号
     * 如果有值则下游根据此值进行退达标
     */
    private String originalSerialNum;

    /**
     * 来源(例如：营销等)
     * 可空
     */
    private String utmSource;

    /**
     * 来源上下文(例如：营销活动id)
     * 可空
     */
    private String utmContent;
    /**
     * 请求跟踪
     * 用于打通生产者和消费者请求链
     * 可空
     */
    private String traceId;

    /**
     * 事件上下文
     * 用于存放不同事件的上下文数据
     */
    private Map<String, String> context;

    /**
     * 是否发送到MQ	必填
     */
    private Integer isEmitted;

    /**
     * 业务来源
     * 比如：买单支付事件中的支付订单号
     */
    private String bizSource;
    /**
     * 事件来源
     */
    private Integer eventFrom;
    /**
     * 绕开幂等
     */
    private Boolean passIdempotent;
}
