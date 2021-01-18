package com.dragon.dao;

import java.util.List;
import java.util.Map;

import com.dragon.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import com.dragon.entity.Student;

public interface StuMapper {
	@SelectProvider(type = com.dragon.dao.provider.StuDynaSqlProvider.class, method = "insertStudent")
	void insertStudent(Student paramStudent);

	@Select({"select * from  student "})
	List<Student> selectAllStudent();

	@SelectProvider(type = com.dragon.dao.provider.StuDynaSqlProvider.class, method = "selectWhitParam")
	List<Student> selectByPage(Map<String, Object> paramMap);

	@SelectProvider(type = com.dragon.dao.provider.StuDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> paramMap);

	@Select({"select * from student where id = #{id}"})
	Student selectById(int paramInt);

	@Select({"select * from student where usid = #{usid}"})
	Student selectByUsid(int paramInt);


	@SelectProvider(type = com.dragon.dao.provider.StuDynaSqlProvider.class, method = "updateStudent")
	void update(Student paramDept);

	@Delete({" delete from student where id = #{id} "})
	void deleteById(Integer paramInteger);
}
