package com.dragon.dao.provider;
/*
 *这个 现在人傻了 其实 #{表单/甚至只要标签能写name属性 =name 值 } 获取的就是 name的值
 */

import java.util.Map;

import com.dragon.entity.Grade;
import org.apache.ibatis.jdbc.SQL;

import com.dragon.entity.Grade;

public class GradeDynaSqlProvider {

	public String insertGrade(final Grade grade) {
		return (new SQL() {
			{
				INSERT_INTO("cj");
				if (grade.getCourse() != null && grade.getCourse().getCid() != null && !grade.getCourse().getCid().equals("")) {
					VALUES("cid", "#{course.cid}");
				}

				if (grade.getStudent() != null && grade.getStudent().getUsid() != null && !grade.getStudent().getUsid().equals("")) {
					VALUES("usid", "#{student.usid}");
				}

				if (grade.getScore() != null && !grade.getScore().equals("")) {
					VALUES("score", "#{score}");
				}
			}
		}).toString();
	}

	public String selectWhitParam(final Map<String, Object> params) {
		/* 18 */
		String sql = (new SQL() {
			/*    */ {
				/*    */
				SELECT("*");
				/* 21 */
				FROM("cj");
				/* 22 */
				if (params.get("grade") != null) {
					/* 23 */
					Grade grade = (Grade) params.get("grade");
					/* 45 */
					if (grade.getScore() != null && !grade.getScore().equals("")) {
						/* 46 */
						WHERE("score LIKE CONCAT ('%',#{grade.score},'%') ");
						/*    */
					}
					if (grade.getCourse() != null && !grade.getCourse().getCid().equals("")) {
						WHERE("cid =#{grade.course.cid}");
					}
					if (grade.getStudent() != null && !grade.getStudent().getUsid().equals("")) {
						WHERE("usid=#{grade.student.usid} ");
					}

				}
			}
		}).toString();
		/*    */
		/* 31 */
		if (params.get("pageModel") != null) {
			/* 32 */
			sql = String.valueOf(sql) + " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
			/*    */
		}
		/*    */
		/* 35 */
		return sql;
		/*    */
	}

	/*    */
	/* 39 */
	public String count(final Map<String, Object> params) {
		return (new SQL()
				/*    */ {
			/*    */ {
				/* 41 */
				SELECT("count(*)");
				/* 42 */
				FROM("cj");
				/* 43 */
				if (params.get("grade") != null) {
					/* 44 */
					Grade grade = (Grade) params.get("grade");
					/* 45 */
					if (grade.getCourse() != null && !grade.getCourse().getCid().equals("")) {
						/* 46 */
						WHERE("cid=#{course.cid} ");
						/*    */
					}

					if (grade.getStudent() != null && !grade.getStudent().getUsid().equals("")) {
						/* 46 */
						WHERE("usid =#{student.usid}");
						/*    */
					}

					if (grade.getScore() != null && !grade.getScore().equals("")) {
						WHERE("score LIKE CONCAT ('%',#{grade.score},'%') ");
					}
					/*    */
				}
				/*    */
			}
			/* 50 */
		}).toString();
	}


	public String updateGrade(final Grade grade) {
		return (new SQL() {
			{

				UPDATE("cj");
				if (grade.getScore() != null) {
					SET(" score = #{score} ");
				}
				if (grade.getCourse().getCid() != null && !grade.getCourse().getCid().equals("")) {
					SET(" cid = #{course.cid} ");
				}
				if (grade.getStudent() != null && !grade.getStudent().getUsid().equals("")) {
					SET("usid = #{student.usid} ");
				}

				WHERE(" id= #{id} ");
			}
		}).toString();
	}

}
