package com.dragon.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * string 类型 （路径）图片也就能实现 但上传下载就不好搞了
 * private String image;
 * <td>
 * <img src="../${user.image}" alt="${user.image}" name="image" width="10%">
 * </td>
 * 所以多加一个属性 绑定关系
 */
public class User {
	private Integer id;
	private String name;
	private String password;
	private String image;
	private MultipartFile multipartFile;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", image='" + image + '\'' +
				", multipartFile=" + multipartFile +
				'}';
	}
}
