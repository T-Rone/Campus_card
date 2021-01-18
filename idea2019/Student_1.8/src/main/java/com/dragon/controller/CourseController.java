/*     */
package com.dragon.controller;

import java.util.List;

import com.dragon.entity.Course;
import com.dragon.service.StuService;
import com.dragon.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*  #{}预处理 接受参数   */
@Controller
/*     */ public class CourseController {

    /*     */
    @Autowired
    /*  */
    @Qualifier("stuService")
    /*     */ private StuService stuService;

    @RequestMapping({"/view/selectCourse.do"})
    public String selectCourse(Model model, Integer pageIndex, @ModelAttribute Course course) {
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex.intValue());
        }

        List<Course> courses = this.stuService.selectAllCourse(course, pageModel);
        model.addAttribute("courses", courses);
        model.addAttribute("pageModel", pageModel);
        return "view/selectCourse";
    }

    @RequestMapping({"/view/removeCourse.do"})
    // @RequestParam("id") 接受url中id得值 并将值 给 后面得  ids 即 参数绑定 默认必须有参数
    public ModelAndView removeCourse(@RequestParam("id") String ids, ModelAndView mv) {

        String[] idArray = ids.split(",");
        for (String idd : idArray) {

            this.stuService.removeCourseById(Integer.parseInt(idd));
        }
        mv.setViewName("redirect:/view/selectCourse.do");
        return mv;
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    @RequestMapping({"/view/insertCourse.do"})
    /*     */ public ModelAndView addCourse(String flag, @ModelAttribute Course course, ModelAndView mv) {
        /*  87 */
        if (flag.equals("1")) {
            /*
             * 通过 这个flag 标志 判断是否需要添加  left.jsp 中 和dept/showAddDept.jsp 都传给 @RequestMapping({"/dept/addDept"})
             *   */
            /*  89 */
            mv.setViewName("view/insertCourse");
            /*     */
        } else {
            /*     */
            /*  92 */
            this.stuService.insertCourse(course); //这就真添加了
            /*     */
            /*  94 */
            mv.setViewName("redirect:/view/selectCourse.do");//又通过控制器 跳到 查询页面
            /*     */
        }
        /*     */
        /*  97 */
        return mv;
        /*     */
    }

    /*     */
    /*     */
    @RequestMapping({"/view/updateCourse.do"})
    public ModelAndView updateCourse(String flag, @ModelAttribute Course course, ModelAndView mv) {
        if (flag.equals("1")) {

            Course target = this.stuService.findCourseById(course.getId()); //改成1 测试对的  莫非 页面course对象 shutdown?

            mv.addObject("course", target);
            mv.setViewName("view/updateCourse");
        } else {

            this.stuService.modifyCourse(course);

            mv.setViewName("redirect:/view/selectCourse.do");
        }

        return mv;
    }
}

