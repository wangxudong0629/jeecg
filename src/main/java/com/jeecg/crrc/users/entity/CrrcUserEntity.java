package com.jeecg.crrc.users.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 人员表
 * @author onlineGenerator
 * @date 2018-09-01 16:46:00
 * @version V1.0   
 *
 */
@Entity
@Table(name = "crrc_user", schema = "")
@SuppressWarnings("serial")
public class CrrcUserEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**用户名*/
	@Excel(name="用户名",width=15)
	private java.lang.String uLoginname;
	/**密码*/
	@Excel(name="密码",width=15)
	private java.lang.String uPassword;
	/**姓名*/
	@Excel(name="姓名",width=15)
	private java.lang.String uName;
	/**年龄*/
	@Excel(name="年龄",width=15)
	private java.lang.String uAge;
	/**性别*/
	@Excel(name="性别",width=15)
	private java.lang.String uSex;
	/**电话*/
	@Excel(name="电话",width=15)
	private java.lang.String uIphone;
	/**邮件*/
	@Excel(name="邮件",width=15)
	private java.lang.String uEmail;
	/**部门*/
	@Excel(name="部门",width=15)
	private java.lang.String uDepartid;
	/**职位*/
	@Excel(name="职位",width=15)
	private java.lang.String uPositionid;
	/**身份证*/
	@Excel(name="身份证",width=15)
	private java.lang.String uIdetitycord;
	/**工号*/
	@Excel(name="工号",width=15)
	private java.lang.String uPnumber;
	/**地址*/
	@Excel(name="地址",width=15)
	private java.lang.String uAddress;
	/**入职时间*/
	@Excel(name="入职时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date uHiredate;
	/**离职时间*/
	@Excel(name="离职时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date uLeavedate;
	/**人员等级*/
	@Excel(name="人员等级",width=15)
	private java.lang.String uLevel;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String uRemarks;
	/**删除*/
	@Excel(name="删除",width=15)
	private java.lang.String dFlag;
	/**权限*/
	@Excel(name="权限",width=15)
	private java.lang.String uAuthority;
	/**预留1*/
	@Excel(name="部门名",width=15)
	private java.lang.String uDepartname;
	/**预留2*/
	@Excel(name="预留2",width=15)
	private java.lang.String uNotel2;
	/**预留3*/
	@Excel(name="预留3",width=15)
	private java.lang.String uNotel3;
	/**预留4*/
	@Excel(name="预留4",width=15)
	private java.lang.String uNotel4;
	/**预留5*/
	@Excel(name="预留5",width=15)
	private java.lang.String uNotel5;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */

	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户名
	 */

	@Column(name ="U_LOGINNAME",nullable=true,length=50)
	public java.lang.String getULoginname(){
		return this.uLoginname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户名
	 */
	public void setULoginname(java.lang.String uLoginname){
		this.uLoginname = uLoginname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  密码
	 */

	@Column(name ="U_PASSWORD",nullable=true,length=50)
	public java.lang.String getUPassword(){
		return this.uPassword;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  密码
	 */
	public void setUPassword(java.lang.String uPassword){
		this.uPassword = uPassword;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */

	@Column(name ="U_NAME",nullable=true,length=50)
	public java.lang.String getUName(){
		return this.uName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setUName(java.lang.String uName){
		this.uName = uName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年龄
	 */

	@Column(name ="U_AGE",nullable=true,length=50)
	public java.lang.String getUAge(){
		return this.uAge;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年龄
	 */
	public void setUAge(java.lang.String uAge){
		this.uAge = uAge;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */

	@Column(name ="U_SEX",nullable=true,length=50)
	public java.lang.String getUSex(){
		return this.uSex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setUSex(java.lang.String uSex){
		this.uSex = uSex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */

	@Column(name ="U_IPHONE",nullable=true,length=50)
	public java.lang.String getUIphone(){
		return this.uIphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setUIphone(java.lang.String uIphone){
		this.uIphone = uIphone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮件
	 */

	@Column(name ="U_EMAIL",nullable=true,length=50)
	public java.lang.String getUEmail(){
		return this.uEmail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮件
	 */
	public void setUEmail(java.lang.String uEmail){
		this.uEmail = uEmail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门
	 */

	@Column(name ="U_DEPARTID",nullable=true,length=50)
	public java.lang.String getUDepartid(){
		return this.uDepartid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门
	 */
	public void setUDepartid(java.lang.String uDepartid){
		this.uDepartid = uDepartid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职位
	 */

	@Column(name ="U_POSITIONID",nullable=true,length=50)
	public java.lang.String getUPositionid(){
		return this.uPositionid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职位
	 */
	public void setUPositionid(java.lang.String uPositionid){
		this.uPositionid = uPositionid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证
	 */

	@Column(name ="U_IDETITYCORD",nullable=true,length=50)
	public java.lang.String getUIdetitycord(){
		return this.uIdetitycord;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证
	 */
	public void setUIdetitycord(java.lang.String uIdetitycord){
		this.uIdetitycord = uIdetitycord;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工号
	 */

	@Column(name ="U_PNUMBER",nullable=true,length=50)
	public java.lang.String getUPnumber(){
		return this.uPnumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工号
	 */
	public void setUPnumber(java.lang.String uPnumber){
		this.uPnumber = uPnumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */

	@Column(name ="U_ADDRESS",nullable=true,length=500)
	public java.lang.String getUAddress(){
		return this.uAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setUAddress(java.lang.String uAddress){
		this.uAddress = uAddress;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入职时间
	 */

	@Column(name ="U_HIREDATE",nullable=true,length=32)
	public java.util.Date getUHiredate(){
		return this.uHiredate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入职时间
	 */
	public void setUHiredate(java.util.Date uHiredate){
		this.uHiredate = uHiredate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  离职时间
	 */

	@Column(name ="U_LEAVEDATE",nullable=true,length=32)
	public java.util.Date getULeavedate(){
		return this.uLeavedate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  离职时间
	 */
	public void setULeavedate(java.util.Date uLeavedate){
		this.uLeavedate = uLeavedate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人员等级
	 */

	@Column(name ="U_LEVEL",nullable=true,length=50)
	public java.lang.String getULevel(){
		return this.uLevel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员等级
	 */
	public void setULevel(java.lang.String uLevel){
		this.uLevel = uLevel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="U_REMARKS",nullable=true,length=500)
	public java.lang.String getURemarks(){
		return this.uRemarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setURemarks(java.lang.String uRemarks){
		this.uRemarks = uRemarks;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  删除
	 */

	@Column(name ="D_FLAG",nullable=true,length=32)
	public java.lang.String getDFlag(){
		return this.dFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  删除
	 */
	public void setDFlag(java.lang.String dFlag){
		this.dFlag = dFlag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  权限
	 */

	@Column(name ="U_AUTHORITY",nullable=true,length=32)
	public java.lang.String getUAuthority(){
		return this.uAuthority;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  权限
	 */
	public void setUAuthority(java.lang.String uAuthority){
		this.uAuthority = uAuthority;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门名
	 */

	@Column(name ="U_DEPARTNAME",nullable=true,length=32)
	public java.lang.String getUDepartname(){
		return this.uDepartname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门名
	 */
	public void setUDepartname(java.lang.String uDepartname){
		this.uDepartname = uDepartname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留2
	 */

	@Column(name ="U_NOTEL2",nullable=true,length=32)
	public java.lang.String getUNotel2(){
		return this.uNotel2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留2
	 */
	public void setUNotel2(java.lang.String uNotel2){
		this.uNotel2 = uNotel2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留3
	 */

	@Column(name ="U_NOTEL3",nullable=true,length=32)
	public java.lang.String getUNotel3(){
		return this.uNotel3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留3
	 */
	public void setUNotel3(java.lang.String uNotel3){
		this.uNotel3 = uNotel3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留4
	 */

	@Column(name ="U_NOTEL4",nullable=true,length=32)
	public java.lang.String getUNotel4(){
		return this.uNotel4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留4
	 */
	public void setUNotel4(java.lang.String uNotel4){
		this.uNotel4 = uNotel4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留5
	 */

	@Column(name ="U_NOTEL5",nullable=true,length=32)
	public java.lang.String getUNotel5(){
		return this.uNotel5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留5
	 */
	public void setUNotel5(java.lang.String uNotel5){
		this.uNotel5 = uNotel5;
	}
}