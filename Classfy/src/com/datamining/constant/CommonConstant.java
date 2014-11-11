package com.datamining.constant;

import java.util.ArrayList;

public class CommonConstant {
    /**
     * 用户需要分析的Excel表数据的完整路径名称
     */
	public  String userFile="";
	/**
	 * 用户的数据表中空值
	 */
	public final String nullAttr="不存在";
     /**
      * 需要分析的数据的所有属性列表
      */
	public ArrayList<String> attrNameList;
	/**
	 * 警告信息，当程序运行错误时的提示信息
	 */
	public String warnMessage="";
	
	public  String getUserFile() {
		return userFile;
	}

	public  void setUserFile(String userFile) {
		this.userFile = userFile;
	}
	
	public void setAttrNameList(ArrayList<String> list){
		this.attrNameList.addAll(list);
	}
	
	public ArrayList<String> getAttrNameList(){
		return this.attrNameList;
	}
	public void setWarnMessage(String message){
		this.warnMessage=message;
	}
	public String getWarnMessage(){
		return warnMessage;
	}
	
}
