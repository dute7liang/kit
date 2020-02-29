package com.duteliang.test.repository.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TUSER")
public class TUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("ID")
    private String id;

    @TableField("CREATE_DATE_TIME")
    private Date createDateTime;

    @TableField("MODIFY_DATE_TIME")
    private Date modifyDateTime;

    @TableField("NAME")
    private String name;

    @TableField("PHONE")
    private String phone;

    @TableField("ORGANIZATION_ID")
    private String organizationId;

    @TableField("DEPARTMENT_ID")
    private String departmentId;

    @TableField("DEPARTMENT_CODE")
    private String departmentCode;

    @TableField("PWD")
    private String pwd;

    @TableField("OPERATE_ORG")
    private String operateOrg;

    @TableField("EMAIL")
    private String email;

    @TableField("STATUS")
    private String status;

    @TableField("ACTIVITI_SYNC")
    private String activitiSync;

    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;

    @TableField("POSITION")
    private String position;

    @TableField("USER_NAME")
    private String userName;

    @TableField("WORK_NO")
    private String workNo;

    @TableField("SEX")
    private String sex;

    @TableField("CARD_ID")
    private String cardId;

    @TableField("BIRTHDAY")
    private String birthday;

    @TableField("AGE")
    private String age;

    @TableField("WEIXIN_USER_ID")
    private String weixinUserId;

    @TableField("RANK")
    private String rank;

    @TableField("LOCK_FLAG")
    private String lockFlag;

    @TableField("BAD_PWD_COUNT")
    private BigDecimal badPwdCount;

    @TableField("CREATE_BY")
    private String createBy;

    @TableField("SYS_ORG_CODE")
    private String sysOrgCode;

    /**
     * OA用户标识
     */
    @TableField("EXTERNAL_FLAG")
    private String externalFlag;

    @TableField("TELEPHONE")
    private String telephone;

    /**
     * 域用户标识
     */
    @TableField("DOMAIN_FLAG")
    private String domainFlag;

    /**
     * 域用户所在域名称
     */
    @TableField("DOMAIN_NAME")
    private String domainName;

    /**
     * 传真机
     */
    @TableField("FAX")
    private String fax;

    /**
     * 存放外部用户的登录密码
     */
    @TableField("PWD2")
    private String pwd2;

    /**
     * 用户类型：0-内部用户，1-外部用户
     */
    @TableField("USER_TYPE")
    private String userType;

    /**
     * 操作员编号
     */
    @TableField("OPERATOR_NUMBER")
    private String operatorNumber;

    /**
     * 是否展开菜单栏 0-不展开，1-展开
     */
    @TableField("UNFOLD_DEF")
    private BigDecimal unfoldDef;

    /**
     * 主题风格
     */
    @TableField("THEME_STYLE")
    private BigDecimal themeStyle;

    /**
     * AI 机构管理
     */
    @TableField("AI_ORGANIZATION_ID")
    private String aiOrganizationId;

    /**
     * 密码最后修改时间
     */
    @TableField("MODIFYPWD_TIME")
    private Date modifypwdTime;

    /**
     * 密码是否已重置
     */
    @TableField("PWD_IS_RESET")
    private BigDecimal pwdIsReset;

    /**
     * 是否第一次登入
     */
    @TableField("IS_FIRST_LOGIN")
    private BigDecimal isFirstLogin;

    @TableField("LIABILITY_COMPANY")
    private String liabilityCompany;


}
