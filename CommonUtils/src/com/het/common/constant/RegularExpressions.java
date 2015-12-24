/*
 * -----------------------------------------------------------------
 * Copyright (C) 2012-2013, by Het, ShenZhen, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: RegularExpressions.java
 * Author: clark
 * Version: 1.0
 * Create: 2013-9-4
 *
 * Changes (from 2013-9-4)
 * -----------------------------------------------------------------
 * 2013-9-4 : 创建 RegularExpressions.java (clark);
 * -----------------------------------------------------------------
 */

package com.het.common.constant;

/**
 * @ClassName: RegularExpressions
 * @Description: 正则表达式常�?
 * @Author: clark
 * @Create: 2013-9-4
 */

public final class RegularExpressions {

    /**
     * 手机号码的正则表达式.
     */
    public static final String PHONE_FORMAT = "^((17[0-9])|(13[0-9])|(15[0-3,5-9])|(18[0-9])|(145)|(147))\\d{8}$";

    /**
     * 邮箱的正则表达式.
     */
    public static final String EMAIL_FORMAT = "^[0-9a-zA-Z][_.0-9a-zA-Z-]{0,43}@([0-9a-zA-Z][0-9a-zA-Z-]{0,30}[0-9a-zA-Z].){1,4}[a-zA-Z]{2,4}$";

    /**
     * 短信验证码的正则表达�?
     */
    public static final String VERIFY_CODE_FORMAT = "^\\d{4}$";

    /**
     * 合法的密码字符正则表达式.
     */
    public static final String PASSWORD_LEGAL_CHARACTERS = "^[0-9a-zA-Z\\`\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\="
            + "\\+\\[\\]\\{\\}\\\\|\\;\\:\'\"\\,\\.\\<\\>\\/\\?]$";

    /**
     * 私有的构造方�?
     */
    private RegularExpressions() {
    }
}
