package com.example.blog.core;

/**
 * @author Great artists
 * @date 2018/10/10/010.
 * 作用：
 */
public enum ResponseCode {
    // 成功状态
    SUCCESS(0,"SUCCESS"),
    ERROR(11400001,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
    LOGIN_ERROR(11400003,
			"ERROR"),
	/**************** 陈鹏辉个人资产模块结果码 **************/
	PARAMETER_MISS(11404001, "传参失败"), INFO_MISS(11404002, "获取信息为空"), CREATE_TASK_ERROR(11404003, "生成任务失败"), OPERATE_MISS(11404004, "操作失败");
    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
