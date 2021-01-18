package com.dragon.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import com.dragon.entity.Grade;

public interface GradeMapper {
	@SelectProvider(type = com.dragon.dao.provider.GradeDynaSqlProvider.class, method = "insertGrade")
	void insertGrade(Grade paramGrade);

	//	@Select({"select *from student left join cj on student.usid=cj.usid  left join kc on cj.cid=kc.cid group by student.usid,kc.cname,student.name order by sum(score)desc"})
	//	List<Grade> selectAllGrade();

	@SelectProvider(type = com.dragon.dao.provider.GradeDynaSqlProvider.class, method = "selectWhitParam")
	@Results({@Result(id = true, column = "id", property = "id"), @Result(column = "score", property = "score"), @Result(column = "usid", property = "student",
			one = @One(select = "com.dragon.dao.StuMapper.selectByUsid", fetchType = FetchType.EAGER)), @Result(column = "cid", property = "course", one = @One(select = "com.dragon.dao.CourseMapper.selectByCid", fetchType = FetchType.EAGER))})
	List<Grade> selectByPage(Map<String, Object> paramMap);

	@SelectProvider(type = com.dragon.dao.provider.GradeDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> paramMap);

	@Select({"select * from cj where id = #{id}"})
	@Results({@Result(id = true, column = "id", property = "id"), @Result(column = "score", property = "score"), @Result(column = "usid", property = "student", one = @One(select = "com.dragon.dao.StuMapper.selectByUsid", fetchType = FetchType.EAGER)), @Result(column = "cid", property = "course", one = @One(select = "com.dragon.dao.CourseMapper.selectByCid", fetchType = FetchType.EAGER))})
	Grade selectById(int paramInt);


	@SelectProvider(type = com.dragon.dao.provider.GradeDynaSqlProvider.class, method = "updateGrade")
	void update(Grade paramGrade);

	@Delete({" delete from cj where id = #{id} "})
	void deleteById(Integer paramInteger);
}
