package com.dragon.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Student implements Serializable {
	private Integer id;
	private String usid;
	private String name;
	private String college;
	private String major;
	private String classes;
	private Integer sex;
	private String home;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	private Date schoolday;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Date getSchoolday() {
		return schoolday;
	}

	public void setSchoolday(Date schoolday) {
		this.schoolday = schoolday;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", usid=" + usid + ", name=" + name
				+ ", college=" + college + ", major=" + major + ", classes="
				+ classes + ", sex=" + sex + ", home=" + home + ", birth="
				+ birth + ", schoolday=" + schoolday + "]";
	}


}
