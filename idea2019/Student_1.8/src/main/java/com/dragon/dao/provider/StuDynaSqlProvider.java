package com.dragon.dao.provider;

import java.util.Map;

import com.dragon.entity.Student;
import org.apache.ibatis.jdbc.SQL;

import com.dragon.entity.Student;;

public class StuDynaSqlProvider {

	public String insertStudent(final Student student) {
		return (new SQL() {
			{
				INSERT_INTO("student");
				if (student.getUsid() != null && !student.getUsid().equals("")) {
					VALUES("usid", "#{usid}");
				}

				if (student.getSex() != null && !student.getSex().equals("")) {
					VALUES("sex", "#{sex}");
				}

				if (student.getSchoolday() != null && !student.getSchoolday().equals("")) {
					VALUES("schoolday", "#{schoolday}");
				}

				if (student.getName() != null && !student.getName().equals("")) {
					VALUES("name", "#{name}");
				}

				if (student.getMajor() != null && !student.getMajor().equals("")) {
					VALUES("major", "#{major}");
				}

				if (student.getHome() != null && !student.getHome().equals("")) {
					VALUES("home", "#{home}");
				}

				if (student.getCollege() != null && !student.getCollege().equals("")) {
					VALUES("college", "#{college}");
				}

				if (student.getClasses() != null && !student.getClasses().equals("")) {
					VALUES("classes", "#{classes}");
				}
				if (student.getBirth() != null && !student.getBirth().equals("")) {
					VALUES("birth", "#{birth}");
				}
			}
		}).toString();
	}

	public String selectWhitParam(final Map<String, Object> params) {
		String sql = (new SQL() {
			{
				SELECT("*");
				FROM("student");
				if (params.get("student") != null) {
					Student student = (Student) params.get("student");
					if (student.getBirth() != null && !student.getBirth().equals("")) {
						WHERE("  birth LIKE CONCAT ('%',#{student.birth},'%') ");
					}

					if (student.getClasses() != null && !student.getClasses().equals("")) {
						WHERE("  classes LIKE CONCAT ('%',#{student.classes},'%') ");
					}

					if (student.getCollege() != null && !student.getCollege().equals("")) {
						WHERE("  college LIKE CONCAT ('%',#{student.college},'%') ");
					}

					if (student.getHome() != null && !student.getHome().equals("")) {
						WHERE("  home LIKE CONCAT ('%',#{student.home},'%') ");
					}

					if (student.getMajor() != null && !student.getMajor().equals("")) {
						WHERE("  major LIKE CONCAT ('%',#{student.major},'%') ");
					}

					if (student.getName() != null && !student.getName().equals("")) {
						WHERE("  name LIKE CONCAT ('%',#{student.name},'%') ");
					}

					if (student.getSchoolday() != null && !student.getSchoolday().equals("")) {
						WHERE("  schoolday LIKE CONCAT ('%',#{student.schoolday},'%') ");
					}

					if (student.getSex() != null && student.getSex().intValue() != 0) {
						WHERE("  sex LIKE CONCAT ('%',#{student.sex},'%') ");
					}

					if (student.getUsid() != null && !student.getUsid().equals("")) {
						WHERE("  usid LIKE CONCAT ('%',#{student.usid},'%') ");
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
				FROM("student");
				/* 43 */
				if (params.get("student") != null) {
					/* 44 */
					Student student = (Student) params.get("student");
					/* 45 */
					if (student.getBirth() != null && !student.getBirth().equals("")) {
						WHERE("  birth LIKE CONCAT ('%',#{student.birth},'%') ");
					}

					if (student.getClasses() != null && !student.getClasses().equals("")) {
						WHERE("  classes LIKE CONCAT ('%',#{student.classes},'%') ");
					}

					if (student.getCollege() != null && !student.getCollege().equals("")) {
						WHERE("  college LIKE CONCAT ('%',#{student.college},'%') ");
					}

					if (student.getHome() != null && !student.getHome().equals("")) {
						WHERE("  home LIKE CONCAT ('%',#{student.home},'%') ");
					}

					if (student.getMajor() != null && !student.getMajor().equals("")) {
						WHERE("  major LIKE CONCAT ('%',#{student.major},'%') ");
					}

					if (student.getName() != null && !student.getName().equals("")) {
						WHERE("  name LIKE CONCAT ('%',#{student.name},'%') ");
					}

					if (student.getSchoolday() != null && !student.getSchoolday().equals("")) {
						WHERE("  schoolday LIKE CONCAT ('%',#{student.schoolday},'%') ");
					}

					if (student.getSex() != null && student.getSex().intValue() != 0) {
						WHERE("  sex LIKE CONCAT ('%',#{student.sex},'%') ");
					}

					if (student.getUsid() != null && !student.getUsid().equals("")) {
						WHERE("  usid LIKE CONCAT ('%',#{student.usid},'%') ");
					}

				}
			}
		}).toString();
	}


	public String updateStudent(final Student student) {
		return (new SQL() {
			{

				UPDATE("student");
				if (student.getBirth() != null) {
					SET(" birth = #{birth} ");
				}

				if (student.getClasses() != null) {
					SET(" classes = #{classes} ");
				}

				if (student.getCollege() != null) {
					SET(" college = #{college} ");
				}

				if (student.getHome() != null) {
					SET(" home = #{home} ");
				}
				if (student.getMajor() != null) {
					SET(" major = #{major} ");
				}

				if (student.getName() != null) {
					SET(" name = #{name} ");
				}

				if (student.getSchoolday() != null) {
					SET(" schoolday = #{schoolday} ");
				}

				if (student.getUsid() != null) {
					SET(" usid = #{usid} ");
				}

				if (student.getSex() != null) {
					SET(" sex = #{sex} ");
				}

				WHERE(" id= #{id} ");
			}
		}).toString();
	}

}
