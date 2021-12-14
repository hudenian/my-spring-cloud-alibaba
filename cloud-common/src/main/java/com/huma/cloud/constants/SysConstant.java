package com.huma.cloud.constants;

/**
 * @author hudenian
 * @date 2021/12/14
 */
public class SysConstant {

    /**
     * 启动工作流前是否需要整体保存：0-否，1-是
     */
    public static final String STR_0 = "0";
    public static final String STR_1 = "1";

    /**
     * 请求头token key值
     */
    public static final String HEADER_TOKEN_KEY = "Access-Token";

    /**
     * 请求头 国际化 language key值
     */
    public static final String HEADER_LANGUAGE_KEY = "Accept-Language";

    /**
     * redis数据库 key值 用户前缀
     */
    public static final String REDIS_USER_PREFIX_KEY = "User:";

    /**
     * redis数据库 用户NONCE前缀 Nonce:{address}:{nonce}
     */
    public static final String REDIS_USER_NONCE_KEY = "Nonce:{}:{}";

    /**
     * redis数据库 用户项目权限 Nonce:{usrId}:{projectId} value:role
     */
    public static final String REDIS_USER_PROJECT_ROLE_KEY = "ROLE:USER-{}:PROJECT-{}";

    /**
     * redis数据库 key值 Token前缀
     */
    public static final String REDIS_TOKEN_PREFIX_KEY = "Token:";

    /**
     * redis数据库 key值
     */
    public static final String REDIS_SYNC_USER_METADATA_PREFIX_KEY = "sync_user_metadata";

    /**
     * 用户非互踢模式时，token已登录用的用户数前缀
     */
    public static final String REDIS_TOKEN_BIND_PREFIX_KEY = "token-bind:";

    /**
     * 系统默认日期格式
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy/MM/dd";

    /**
     * 系统默认时间格式
     */
    public static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时区默认东八区北京时间
     */
    public static final String DEFAULT_TIMEZONE = "GMT+8";

    public static final String CLASSPATH = "classpath:";

    /**
     * 国际化中文
     */
    public static final String ZH_CN = "zh";
    /**
     * 国际化英文
     */
    public static final String EN_US = "en";


}
