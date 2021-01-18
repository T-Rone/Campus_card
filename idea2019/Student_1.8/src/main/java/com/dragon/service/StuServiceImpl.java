package com.dragon.service;


import com.dragon.dao.*;
import com.dragon.util.tag.PageModel;
import com.dragon.entity.*;
import com.dragon.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service("stuService")
public class StuServiceImpl implements StuService {

	@Autowired
	private SpeMapper speMapper;

	@Autowired
	private StuMapper stuMapper;

	@Autowired
	private CourseMapper courseMapper;

	@Autowired
	private GradeMapper gradeMapper;

	@Autowired
	private UserMapper userMapper;

	@Override
	public void insertDepartment(Department department) {
		// TODO Auto-generated method stub

		this.speMapper.insertDepartment(department);
	}

	@Override
	public List<Department> selectAllDepartment(Department department, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		/* 148 */
		params.put("department", department);
		/* 149 */
		int recordCount = this.speMapper.count(params).intValue();
		/* 150 */
		pageModel.setRecordCount(recordCount);
		/*     */
		/* 152 */
		if (recordCount > 0)
			/*     */ {
			/* 154 */
			params.put("pageModel", pageModel);
			/*     */
		}
		/*     */
		return this.speMapper.selectByPage(params);
	}

	@Override
	public Department findDepartById(Integer id) {
		// TODO Auto-generated method stub
		return this.speMapper.selectById(id.intValue());
	}

	@Override
	public void modifyDepart(Department department) {
		// TODO Auto-generated method stub
		this.speMapper.update(department);
	}

	@Override
	public void removeDepartById(int id) {
		// TODO Auto-generated method stub
		this.speMapper.deleteById(id);
	}


	@Override
	public void insertStudent(Student student) {
		// TODO Auto-generated method stub
		this.stuMapper.insertStudent(student);
	}

	@Override
	public List<Student> selectAllStu(Student student, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		/* 148 */
		params.put("student", student);
		/* 149 */
		int recordCount = this.stuMapper.count(params).intValue();
		/* 150 */
		pageModel.setRecordCount(recordCount);
		/*     */
		/* 152 */
		if (recordCount > 0)
			/*     */ {
			/* 154 */
			params.put("pageModel", pageModel);
			/*     */
		}
		/*     */
		return this.stuMapper.selectByPage(params);
	}

	@Override
	public Student findStuById(Integer paramInteger) {
		// TODO Auto-generated method stub
		return this.stuMapper.selectById(paramInteger.intValue());
	}

	@Override
	public void modifyStu(Student paramStudent) {
		// TODO Auto-generated method stub
		this.stuMapper.update(paramStudent);
	}

	@Override
	public void removeStuById(int parseInt) {
		// TODO Auto-generated method stub
		this.stuMapper.deleteById(parseInt);
	}


	@Override
	public void insertCourse(Course course) {
		// TODO Auto-generated method stub
		this.courseMapper.insertCourse(course);
	}

	@Override
	public List<Course> selectAllCourse(Course course, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		/* 148 */
		params.put("course", course);
		/* 149 */
		int recordCount = this.courseMapper.count(params).intValue();
		/* 150 */
		pageModel.setRecordCount(recordCount);
		/*     */
		/* 152 */
		if (recordCount > 0)
			/*     */ {
			/* 154 */
			params.put("pageModel", pageModel);
			/*     */
		}
		/*     */
		return this.courseMapper.selectByPage(params);
	}

	@Override
	public Course findCourseById(Integer id) {
		// TODO Auto-generated method stub
		return this.courseMapper.selectById(id.intValue());
	}

	@Override
	public void modifyCourse(Course course) {
		// TODO Auto-generated method stub
		this.courseMapper.update(course);
	}

	@Override
	public void removeCourseById(int id) {
		// TODO Auto-generated method stub
		this.courseMapper.deleteById(id);
	}


	@Override
	public void insertGrade(Grade grade) {
		// TODO Auto-generated method stub
		this.gradeMapper.insertGrade(grade);
	}

	@Override
	public List<Grade> selectAllGrade(Grade grade, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		/* 148 */
		params.put("grade", grade);
		/* 149 */
		int recordCount = this.courseMapper.count(params).intValue();
		/* 150 */
		pageModel.setRecordCount(recordCount);
		/*     */
		/* 152 */
		if (recordCount > 0)
			/*     */ {
			/* 154 */
			params.put("pageModel", pageModel);
			/*     */
		}
		/*     */
		return this.gradeMapper.selectByPage(params);
	}

	@Override
	public Grade findGradeById(Integer id) {
		// TODO Auto-generated method stub
		return this.gradeMapper.selectById(id.intValue());
	}

	@Override
	public void modifyGrade(Grade grade) {
		// TODO Auto-generated method stub
		this.gradeMapper.update(grade);
	}

	@Override
	public void removeGradeById(int id) {
		// TODO Auto-generated method stub
		this.gradeMapper.deleteById(id);
	}

	@Override
	public List<Student> findAllStudent() {
		// TODO Auto-generated method stub
		return this.stuMapper.selectAllStudent();
	}

	@Override
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		return this.courseMapper.selectAllCourse();
	}

	@Override
	@Transactional(readOnly = true)
	public User login(String name, String password) {
		// TODO Auto-generated method stub
		return this.userMapper.selectByNameAndPassword(name, password);
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return this.userMapper.selectById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findUser(User user, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		/*  79 */
		params.put("user", user);
		/*  80 */
		int recordCount = this.userMapper.count(params).intValue();
		/*  81 */
		pageModel.setRecordCount(recordCount);
		/*  82 */
		if (recordCount > 0)
			/*     */ {
			/*  84 */
			params.put("pageModel", pageModel);
			/*     */
		}
		/*  86 */
		return this.userMapper.selectByPage(params);
		/*     */
	}

	@Override
	public void removeUserById(Integer id) {
		// TODO Auto-generated method stub
		this.userMapper.deleteById(id);
	}

	@Override
	public void modifyUser(User user) {
		// TODO Auto-generated method stub
		this.userMapper.update(user);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		this.userMapper.save(user);
	}


}
