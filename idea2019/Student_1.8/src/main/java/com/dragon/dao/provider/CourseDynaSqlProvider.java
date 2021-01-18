package com.dragon.dao.provider;

import java.util.Map;

import com.dragon.entity.Course;
import org.apache.ibatis.jdbc.SQL;

public class CourseDynaSqlProvider {

	public String insertCourse(final Course course) {
		return (new SQL() {
			{
				INSERT_INTO("kc");
				if (course.getCid() != null && !course.getCid().equals("")) {
					VALUES("cid", "#{cid}");
				}
				if (course.getCname() != null && !course.getCname().equals("")) {
					VALUES("cname", "#{cname}");
				}
				if (course.getCredit() != null && !course.getCredit().equals("")) {
					VALUES("credit", "#{credit}");
				}
				if (course.getPeriod() != null && !course.getPeriod().equals("")) {
					VALUES("period", "#{period}");
				}
				if (course.getTerm() != null && !course.getTerm().equals("")) {
					VALUES("term", "#{term}");
				}
			}
		}).toString();
	}

	public String selectWhitParam(final Map<String, Object> params) {
		String sql = (new SQL() {
			{
				SELECT("*");
				FROM("kc");
				if (params.get("course") != null) {
					Course course = (Course) params.get("course");
					if (course.getCid() != null && !course.getCid().equals("")) {
						WHERE("cid LIKE CONCAT ('%',#{course.cid},'%') ");
					}
					if (course.getCname() != null && !course.getCname().equals("")) {
						WHERE("cname LIKE CONCAT ('%',#{course.cname},'%') ");
					}

				}
			}
		}).toString();

		if (params.get("pageModel") != null) {
			sql = String.valueOf(sql) + " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}

		return sql;
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
				FROM("kc");
				/* 43 */
				if (params.get("course") != null) {
					/* 44 */
					Course course = (Course) params.get("course");
					/* 45 */
					if (course.getCid() != null && !course.getCid().equals("")) {
						/* 46 */
						WHERE("cid LIKE CONCAT ('%',#{course.cid},'%') ");
						/*    */
					}
					/*    */
				}
				/*    */
			}
			/* 50 */
		}).toString();
	}


	public String updateCourse(final Course course) {
		return (new SQL() {
			{

				UPDATE("kc");
				if (course.getCname() != null) {
					SET(" cname = #{cname} ");
				}
				if (course.getCredit() != null) {
					SET(" credit = #{credit} ");
				}
				if (course.getCid() != null) {
					SET(" cid = #{cid} ");
				}
				if (course.getPeriod() != null) {
					SET(" period = #{period} ");
				}
				if (course.getTerm() != null) {
					SET(" term = #{term} ");
				}

				WHERE(" id= #{id} ");
			}
		}).toString();
	}

}
