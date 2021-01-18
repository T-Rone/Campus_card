package com.dragon.entity;

import java.io.Serializable;

public class Department implements Serializable {
	private Integer id;
	private String depart_name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", depart_name=" + depart_name + "]";
	}


}
