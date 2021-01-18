package com.dragon.dao.provider;

import java.util.Map;

import com.dragon.entity.Department;
import org.apache.ibatis.jdbc.SQL;

import com.dragon.entity.Department;

public class SpeDynaSqlProvider {

	public String insertDepartment(final Department Department) {
		return (new SQL() {
			{
				INSERT_INTO("department");
				if (Department.getDepart_name() != null && !Department.getDepart_name().equals("")) {
					VALUES("depart_name", "#{depart_name}");
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
				FROM("department");
				/* 22 */
				if (params.get("department") != null) {
					/* 23 */
					Department department = (Department) params.get("department");
					/* 24 */
					if (department.getDepart_name() != null && !department.getDepart_name().equals("")) {
						/* 25 */
						WHERE("  depart_name LIKE CONCAT ('%',#{department.depart_name},'%') ");
						/*    */
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
				FROM("department");
				/* 43 */
				if (params.get("department") != null) {
					/* 44 */
					Department department = (Department) params.get("department");
					/* 45 */
					if (department.getDepart_name() != null && !department.getDepart_name().equals("")) {
						/* 46 */
						WHERE("  depart_name LIKE CONCAT ('%',#{department.depart_name},'%') ");
						/*    */
					}
					/*    */
				}
				/*    */
			}
			/* 50 */
		}).toString();
	}


	public String updateDepart(final Department department) {
		return (new SQL() {
			{

				UPDATE("department");
				if (department.getDepart_name() != null) {
					SET(" depart_name = #{depart_name} ");
				}
				WHERE(" id= #{id} ");
			}
		}).toString();
	}

}
