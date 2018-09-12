package com.jeecg.crrc.plan.entity;

import java.math.BigDecimal;

import java.util.ArrayList;

import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.soap.Text;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
;

/**   
 * @Title: Entity
 * @Description: 项目管理
 * @author onlineGenerator
 * @date 2018-08-13 22:30:56
 * @version V1.0   
 *
 */
@Entity
@Table(name = "plan", schema = "")
@SuppressWarnings("serial")
public class PlanEntity implements java.io.Serializable {
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
	/**流程状态*/

	private java.lang.String bpmStatus;
	/**名称*/
    @Excel(name="名称",width=15)
		
	private java.lang.String pname;
	/**父级ID*/
    @Excel(name="父级ID",width=15)

	private java.lang.String number;
	/**项目经理*/
    @Excel(name="项目经理",width=15)
		
	private java.lang.String username;
	/**开始时间*/
    @Excel(name="开始时间",width=15,format = "yyyy-MM-dd")
		
	private Date dateby;
	/**结束时间*/
    @Excel(name="结束时间",width=15,format = "yyyy-MM-dd")
		
	private Date dateend;
	/**天数*/
    @Excel(name="天数",width=15)
		
	private java.lang.String date;
	/**级别*/
    @Excel(name="级别",width=15)
		
	private java.lang.String level;
	/**备注*/
    @Excel(name="备注",width=15)
		
	private java.lang.String remarks;
	/**状态*/
    @Excel(name="状态",width=15)
		
	private java.lang.String statese;
	/**进度*/
    @Excel(name="进度",width=15)
		
	private java.lang.String process;

	private PlanEntity planEntity;

	private List<PlanEntity> planEntitys = new ArrayList<PlanEntity>();



	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUMBER")
	public PlanEntity getplanEntity(){
		return this.planEntity;
	}


	public void setplanEntity(PlanEntity planEntity){
		this.planEntity = planEntity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "planEntity")
	public List<PlanEntity> getPlanEntitys() {
		return planEntitys;
	}

	public void setPlanEntitys(List<PlanEntity> tSplans) {
		planEntitys = tSplans;
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
	@Column(name ="PNAME",nullable=true,length=50)
	public java.lang.String getPname(){
		return this.pname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setPname(java.lang.String pname){
		this.pname = pname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  父级ID
	 */
	@Column(name ="number",nullable=true,length=50)
	public java.lang.String getNumber(){
		return this.number;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父级ID
	 */
	public void setNumber(java.lang.String number){
		this.number = number;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目经理
	 */
	@Column(name ="USERNAME",nullable=true,length=32)
	public java.lang.String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目经理
	 */
	public void setUsername(java.lang.String username){
		this.username = username;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	@Column(name ="DATEBY",nullable=true,length=32)
	public java.util.Date getDateby(){
		return this.dateby;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setDateby(Date dateby){
		this.dateby = dateby;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="DATEEND",nullable=true,length=32)
	public java.util.Date getDateend(){
		return this.dateend;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setDateend(Date dateend){
		this.dateend = dateend;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  天数
	 */
	@Column(name ="DATE",nullable=true,length=32)
	public java.lang.String getDate(){
		return this.date;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  天数
	 */
	public void setDate(java.lang.String date){
		this.date = date;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  级别
	 */
	@Column(name ="LEVEL",nullable=true,length=32)
	public java.lang.String getLevel(){
		return this.level;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  级别
	 */
	public void setLevel(java.lang.String level){
		this.level = level;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARKS",nullable=true,length=32)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATESE",nullable=true,length=32)
	public java.lang.String getStatese(){
		return this.statese;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatese(java.lang.String statese){
		this.statese = statese;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  进度
	 */
	@Column(name ="PROCESS",nullable=true,length=32)
	public java.lang.String getProcess(){
		return this.process;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  进度
	 */
	public void setProcess(java.lang.String process){
		this.process = process;
	}

	@Override
	public String toString() {
		return "PlanEntity{" +
				"id='" + id + '\'' +
				", createName='" + createName + '\'' +
				", createBy='" + createBy + '\'' +
				", createDate=" + createDate +
				", updateName='" + updateName + '\'' +
				", updateBy='" + updateBy + '\'' +
				", updateDate=" + updateDate +
				", bpmStatus='" + bpmStatus + '\'' +
				", pname='" + pname + '\'' +
				", number='" + number + '\'' +
				", username='" + username + '\'' +
				", dateby=" + dateby +
				", dateend=" + dateend +
				", date='" + date + '\'' +
				", level='" + level + '\'' +
				", remarks='" + remarks + '\'' +
				", state='" + statese + '\'' +
				", process='" + process + '\'' +
				", planEntity=" + planEntity +
				", planEntitys=" + planEntitys +
				'}';
	}
}
