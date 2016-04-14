package dao.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源表
 */
public class SysFuncResource {

	/** resId */
	private String resId;
	/** 可以作为有一定业务含义的主键 */
	private String url;
	/** name */
	private String name;
	/** 功能形式，按钮，表单，菜单项等如  1 : 菜单 2 ： 按钮  3：表单 */
	private String functionType;
	/** parentId */
	private String parentId;
	/** 显示排序号 */
	private Integer serial;
	/** description */
	private String description;
	/** createTime */
	private java.util.Date createTime;
	/** updateTime */
	private java.util.Date updateTime;

	private boolean checked;

	private List<SysFuncResource> children = new ArrayList<SysFuncResource>();

	public List<SysFuncResource> getChildren() {
		return children;
	}

	public void setChildren(List<SysFuncResource> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getResId(){
		return this.resId;
	}
	public void setResId(String resId){
		this.resId = resId;
	}
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getFunctionType(){
		return this.functionType;
	}
	public void setFunctionType(String functionType){
		this.functionType = functionType;
	}
	public String getParentId(){
		return this.parentId;
	}
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	public Integer getSerial(){
		return this.serial;
	}
	public void setSerial(Integer serial){
		this.serial = serial;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
}