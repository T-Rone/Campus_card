package com.dragon.controller;

import java.util.List;

import com.dragon.entity.Course;
import com.dragon.entity.Grade;
import com.dragon.entity.Student;
import com.dragon.service.StuService;
import com.dragon.util.tag.PageModel;
import com.dragon.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*  */
@Controller
/*     */ public class GradeController {

	/*     */
	@Autowired
	/*  */
	@Qualifier("stuService")
	/*     */ private StuService stuService;

	/*     */
	@RequestMapping({"/view/selectGrade.do"})
	public String selectGrade(Model model, Integer pageIndex, @ModelAttribute Grade grade, String usid, String cid) {
		genericAssociation(usid, cid, grade);
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex.intValue());
		}

		List<Student> students = this.stuService.findAllStudent();
		List<Course> courses = this.stuService.findAllCourse();

		List<Grade> grades = this.stuService.selectAllGrade(grade, pageModel);

		model.addAttribute("students", students);
		model.addAttribute("courses", courses);

		model.addAttribute("grades", grades);
		model.addAttribute("pageModel", pageModel);

		return "view/selectGrade";
	}

	@RequestMapping({"/view/removeGrade.do"})
	// @RequestParam("id") ����url��id��ֵ ����ֵ �� �����  ids �� ������ Ĭ�ϱ����в���
	public ModelAndView removeGrade(@RequestParam("id") String ids, ModelAndView mv) {

		String[] idArray = ids.split(",");
		for (String idd : idArray) {

			this.stuService.removeGradeById(Integer.parseInt(idd));
		}
		mv.setViewName("redirect:/view/selectGrade.do");
		return mv;
		/*     */
	}

	/*     */
	/*     */
	/*     */
	/*     */
	@RequestMapping({"/view/insertGrade.do"})
	/*     */ public ModelAndView addGrade(String flag, String usid, String cid, @ModelAttribute Grade grade, ModelAndView mv) {
		/*  87 */
		if (flag.equals("1")) {
			/*
			 * ͨ�� ���flag ��־ �ж��Ƿ���Ҫ���  left.jsp �� ��dept/showAddDept.jsp ������ @RequestMapping({"/dept/addDept"})
			 *   */
			List<Student> students = this.stuService.findAllStudent();
			List<Course> courses = this.stuService.findAllCourse();

			mv.addObject("courses", courses);
			mv.addObject("students", students);
			/*  89 */
			mv.setViewName("view/insertGrade");
			/*     */
		} else {
			/*     */
			genericAssociation(usid, cid, grade);
			/*  92 */
			this.stuService.insertGrade(grade); //����������
			/*     */
			/*  94 */
			mv.setViewName("redirect:/view/selectGrade.do");//��ͨ�������� ���� ��ѯҳ��
			/*     */
		}
		/*     */
		/*  97 */
		return mv;
		/*     */
	}

	/*     */
	/*     */
	@RequestMapping({"/view/updateGrade.do"})
	public ModelAndView updateGrade(String flag, @ModelAttribute Grade grade, ModelAndView mv, String usid, String cid) {
		if (flag.equals("1")) {

			Grade target = this.stuService.findGradeById(grade.getId()); //�ĳ�1 ���ԶԵ�  Ī�� ҳ��grade���� shutdown?
			List<Student> students = this.stuService.findAllStudent();
			List<Course> courses = this.stuService.findAllCourse();

			mv.addObject("courses", courses);
			mv.addObject("students", students);

			mv.addObject("grade", target);
			mv.setViewName("view/updateGrade");
		} else {

			genericAssociation(usid, cid, grade);
			this.stuService.modifyGrade(grade);

			mv.setViewName("redirect:/view/selectGrade.do");
		}

		return mv;
	}

	private void genericAssociation(String usid, String cid, Grade grade) {
		/* 174 */
		if (usid != null) {
			Student student = new Student();
			student.setUsid(usid);
			grade.setStudent(student);
			/*     */
		}
		/* 179 */
		if (cid != null) {
			/* 180 */
			Course course = new Course();
			course.setCsid(cid);
			grade.setCourse(course);
			/*     */
		}
	}
}

