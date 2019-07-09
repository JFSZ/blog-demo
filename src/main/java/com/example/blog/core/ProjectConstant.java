package com.example.blog.core;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 项目常量
 */
public final class ProjectConstant {

    //生成代码所在的基础包名称，可根据自己公司的项目修改
    // （注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）
    public static final String BASE_PACKAGE = "com.example.blog";

    //生成的Model所在包
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";

    //生成的Mapper所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";

    //生成的Service所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";

    //生成的ServiceImpl所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";

    //生成的Controller所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".web";

    //Mapper插件基础接口的完全限定名
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";


    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public static final String TOKEN_PREFIX = "token_";


    /**
     * Redis设置失效时间30分钟
     */
    public interface RedisCacheExtime {
        int REDIS_SESSION_EXTIME = 60 * 30;
    }


    /**
     * 选中状态CHECKED
     * 未选中状态UN_CHECKED
     */
    public interface Cart {
        int CHECKED = 1;
        int UN_CHECKED = 0;

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    /**
     * 发送文本邮件ACTIVE_USER_MAIL
     * 发送Html邮件UPDATE_USER_MAIL
     */
    public interface Email {
        String ACTIVE_USER_MAIL = "ACTIVE_USER_MAIL";
        String UPDATE_USER_MAIL = "UPDATE_USER_MAIL";
    }

    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }

    /**
     * 轻量级比枚举轻
     * ROLE_CUSTOMER普通用户
     * ROLE_ADMIN管理员
     */
    public interface Role {
        int ROLE_CUSTOMER = 0;
        int ROLE_ADMIN = 1;
    }

    /**
     * 状态枚举
     */
    public enum ProductStatusEnum {
        //ON_SAL是否在线
        ON_SAL(1, "在线");
        private String value;
        private int code;

        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }


    /**
     * 状态的枚举
     */
    public enum OrderStatusEnum {
        CANCELED(0, "已发送");

        OrderStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code) {
            for (OrderStatusEnum orderStatusEnum : values()) {
                if (orderStatusEnum.getCode() == code) {
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

    public interface REDIS_LOCK {
        // 关闭分布式锁
        String CLOSE_TASK_LOCK = "CLOSE_TASK_LOCK";
    }

    public interface FACE_BAIDU {
        String GROUP_ID = "Test";
    }
}
