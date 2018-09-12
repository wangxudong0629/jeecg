package com.jeecg.crrc.consumer.entity;

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
 * @Description: 客户表
 * @author onlineGenerator
 * @date 2018-08-15 16:07:48
 * @version V1.0   
 *
 */
@Entity
@Table(name = "consumer", schema = "")
@SuppressWarnings("serial")
public class ConsumerEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**所在公司*/
	@Excel(name="所在公司",width=15)
	private String company;
	/**对接主管*/
	@Excel(name="对接主管",width=15)
	private String manager;
	/**项目id*/
	@Excel(name="项目id",width=15)
	private String planId;
	/**电话*/
	@Excel(name="电话",width=15)
	private String tel;
	/**邮箱*/
	@Excel(name="邮箱",width=15)
	private String email;
	/**微信*/
	@Excel(name="微信",width=15)
	private String wechat;
	/**备注*/
	@Excel(name="备注",width=15)
	private String remark;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所在公司
	 */

	@Column(name ="COMPANY",nullable=true,length=32)
	public String getCompany(){
		return this.company;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所在公司
	 */
	public void setCompany(String company){
		this.company = company;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对接主管
	 */

	@Column(name ="MANAGER",nullable=true,length=32)
	public String getManager(){
		return this.manager;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对接主管
	 */
	public void setManager(String manager){
		this.manager = manager;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目id
	 */

	@Column(name ="PLAN_ID",nullable=true,length=32)
	public String getPlanId(){
		return this.planId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目id
	 */
	public void setPlanId(String planId){
		this.planId = planId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */

	@Column(name ="TEL",nullable=true,length=32)
	public String getTel(){
		return this.tel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setTel(String tel){
		this.tel = tel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮箱
	 */

	@Column(name ="EMAIL",nullable=true,length=32)
	public String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮箱
	 */
	public void setEmail(String email){
		this.email = email;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信
	 */

	@Column(name ="WECHAT",nullable=true,length=32)
	public String getWechat(){
		return this.wechat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信
	 */
	public void setWechat(String wechat){
		this.wechat = wechat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARK",nullable=true,length=32)
	public String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
}
