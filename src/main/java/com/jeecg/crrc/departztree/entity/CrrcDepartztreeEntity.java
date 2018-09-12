package com.jeecg.crrc.departztree.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.soap.Text;
import java.sql.Blob;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 组织机构总表
 * @author onlineGenerator
 * @date 2018-09-01 16:53:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "crrc_departztree", schema = "")
@SuppressWarnings("serial")
public class CrrcDepartztreeEntity implements java.io.Serializable {
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
	/**名称*/
    @Excel(name="名称",width=15)
		
	private java.lang.String deName;
	/**父级*/
    @Excel(name="父级",width=15)
		
	private java.lang.String deNumber;
	/**类型*/
    @Excel(name="类型",width=15)
		
	private java.lang.String deType;
	/**预留1*/
    @Excel(name="预留1",width=15)
		
	private java.lang.String deNotel1;
	/**预留2*/
    @Excel(name="预留2",width=15)
		
	private java.lang.String deNotel2;
	/**预留3*/
    @Excel(name="预留3",width=15)
		
	private java.lang.String deNotel3;
	/**预留4*/
    @Excel(name="预留4",width=15)
		
	private java.lang.String deNotel4;

    private CrrcDepartztreeEntity crrcDepartztree;

    private List<CrrcDepartztreeEntity> crrcDepartztrees =new ArrayList<CrrcDepartztreeEntity>();


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DE_NUMBER")
	public CrrcDepartztreeEntity getcrrcDepartztree(){
		return this.crrcDepartztree;
	}

	public void setcrrcDepartztree(CrrcDepartztreeEntity crrcDepartztree){
		this.crrcDepartztree = crrcDepartztree;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "crrcDepartztree")
	public List<CrrcDepartztreeEntity> getcrrcDepartztrees() {
		return crrcDepartztrees;
	}

	public void setcrrcDepartztrees(List<CrrcDepartztreeEntity> crrcDepartztrees) {
		crrcDepartztrees = crrcDepartztrees;
	}
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
	 *@return: java.lang.String  名称
	 */
	@Column(name ="DE_NAME",nullable=true,length=50)
	public java.lang.String getDeName(){
		return this.deName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setDeName(java.lang.String deName){
		this.deName = deName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  父级
	 */
	@Column(name ="de_number",nullable=true,length=50)
	public java.lang.String getDeNumber(){
		return this.deNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父级
	 */
	public void setDeNumber(java.lang.String deNumber){
		this.deNumber = deNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */
	@Column(name ="DE_TYPE",nullable=true,length=50)
	public java.lang.String getDeType(){
		return this.deType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setDeType(java.lang.String deType){
		this.deType = deType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留1
	 */
	@Column(name ="DE_NOTEL1",nullable=true,length=32)
	public java.lang.String getDeNotel1(){
		return this.deNotel1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留1
	 */
	public void setDeNotel1(java.lang.String deNotel1){
		this.deNotel1 = deNotel1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留2
	 */
	@Column(name ="DE_NOTEL2",nullable=true,length=32)
	public java.lang.String getDeNotel2(){
		return this.deNotel2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留2
	 */
	public void setDeNotel2(java.lang.String deNotel2){
		this.deNotel2 = deNotel2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留3
	 */
	@Column(name ="DE_NOTEL3",nullable=true,length=32)
	public java.lang.String getDeNotel3(){
		return this.deNotel3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留3
	 */
	public void setDeNotel3(java.lang.String deNotel3){
		this.deNotel3 = deNotel3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留4
	 */
	@Column(name ="DE_NOTEL4",nullable=true,length=32)
	public java.lang.String getDeNotel4(){
		return this.deNotel4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留4
	 */
	public void setDeNotel4(java.lang.String deNotel4){
		this.deNotel4 = deNotel4;
	}
}
