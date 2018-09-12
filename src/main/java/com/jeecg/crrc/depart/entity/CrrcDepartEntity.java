package com.jeecg.crrc.depart.entity;

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
 * @Description: 公司组织架构
 * @author onlineGenerator
 * @date 2018-09-03 01:35:25
 * @version V1.0   
 *
 */
@Entity
@Table(name = "crrc_depart", schema = "")
@SuppressWarnings("serial")
public class CrrcDepartEntity implements java.io.Serializable {
	/**主键*/

	private java.lang.String id;
	/**流程状态*/

	private java.lang.String bpmStatus;
	/**创建人名称*/

	private java.lang.String createName;
	/**创建人登录名称*/

	private java.lang.String createBy;
	/**更新人名称*/

	private java.lang.String updateName;
	/**更新人登录名称*/

	private java.lang.String updateBy;
	/**所属部门*/

	private java.lang.String sysOrgCode;
	/**所属公司*/

	private java.lang.String sysCompanyCode;
	/**创建日期*/

	private java.util.Date createDate;
	/**更新日期*/

	private java.util.Date updateDate;
	/**名字*/
    @Excel(name="名字",width=15)
		
	private java.lang.String dName;
	/**父级id*/
    @Excel(name="父级id",width=15)
		
	private java.lang.String dNumber;
	/**组织电话*/
    @Excel(name="组织电话",width=15)
		
	private java.lang.String dIphone;
	/**组织邮件*/
    @Excel(name="组织邮件",width=15)
		
	private java.lang.String dEmail;
	/**备注*/
    @Excel(name="备注",width=15)
		
	private java.lang.String dRemarks;
	/**权限*/
    @Excel(name="权限",width=15)
		
	private java.lang.String dAuthority;
	/**预留1*/
    @Excel(name="预留1",width=15)
		
	private java.lang.String dNotel1;
	/**预留2*/
    @Excel(name="预留2",width=15)
		
	private java.lang.String dNotel2;
	/**预留3*/
    @Excel(name="预留3",width=15)
		
	private java.lang.String dNotel3;
	/**预留4*/
    @Excel(name="预留4",width=15)
		
	private java.lang.String dNotel4;
	/**预留5*/
    @Excel(name="预留5",width=15)
		
	private java.lang.String dNotel5;
	
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
	 *@return: java.lang.String  名字
	 */
	@Column(name ="D_NAME",nullable=true,length=50)
	public java.lang.String getDName(){
		return this.dName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名字
	 */
	public void setDName(java.lang.String dName){
		this.dName = dName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  父级id
	 */
	@Column(name ="D_NUMBER",nullable=true,length=32)
	public java.lang.String getDNumber(){
		return this.dNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父级id
	 */
	public void setDNumber(java.lang.String dNumber){
		this.dNumber = dNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  组织电话
	 */
	@Column(name ="D_IPHONE",nullable=true,length=50)
	public java.lang.String getDIphone(){
		return this.dIphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  组织电话
	 */
	public void setDIphone(java.lang.String dIphone){
		this.dIphone = dIphone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  组织邮件
	 */
	@Column(name ="D_EMAIL",nullable=true,length=50)
	public java.lang.String getDEmail(){
		return this.dEmail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  组织邮件
	 */
	public void setDEmail(java.lang.String dEmail){
		this.dEmail = dEmail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="D_REMARKS",nullable=true,length=500)
	public java.lang.String getDRemarks(){
		return this.dRemarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setDRemarks(java.lang.String dRemarks){
		this.dRemarks = dRemarks;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  权限
	 */
	@Column(name ="D_AUTHORITY",nullable=true,length=50)
	public java.lang.String getDAuthority(){
		return this.dAuthority;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  权限
	 */
	public void setDAuthority(java.lang.String dAuthority){
		this.dAuthority = dAuthority;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留1
	 */
	@Column(name ="D_NOTEL1",nullable=true,length=32)
	public java.lang.String getDNotel1(){
		return this.dNotel1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留1
	 */
	public void setDNotel1(java.lang.String dNotel1){
		this.dNotel1 = dNotel1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留2
	 */
	@Column(name ="D_NOTEL2",nullable=true,length=32)
	public java.lang.String getDNotel2(){
		return this.dNotel2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留2
	 */
	public void setDNotel2(java.lang.String dNotel2){
		this.dNotel2 = dNotel2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留3
	 */
	@Column(name ="D_NOTEL3",nullable=true,length=32)
	public java.lang.String getDNotel3(){
		return this.dNotel3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留3
	 */
	public void setDNotel3(java.lang.String dNotel3){
		this.dNotel3 = dNotel3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留4
	 */
	@Column(name ="D_NOTEL4",nullable=true,length=32)
	public java.lang.String getDNotel4(){
		return this.dNotel4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留4
	 */
	public void setDNotel4(java.lang.String dNotel4){
		this.dNotel4 = dNotel4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留5
	 */
	@Column(name ="D_NOTEL5",nullable=true,length=32)
	public java.lang.String getDNotel5(){
		return this.dNotel5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留5
	 */
	public void setDNotel5(java.lang.String dNotel5){
		this.dNotel5 = dNotel5;
	}
}
