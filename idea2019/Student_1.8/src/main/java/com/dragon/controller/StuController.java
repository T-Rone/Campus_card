package com.dragon.controller;

import java.util.List;

import com.dragon.entity.Student;
import com.dragon.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.dragon.entity.Student;
import com.dragon.service.StuService;

@Controller
/*     */ public class StuController {

    /*     */
    @Autowired
    /*  */
    @Qualifier("stuService")
    /*     */ private StuService stuService;

    /*     */
    @RequestMapping({"/view/selectStudent.do"})
    public String selectStu(Model model, Integer pageIndex, @ModelAttribute Student student) {
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex.intValue());
        }

        List<Student> students = this.stuService.selectAllStu(student, pageModel);
        model.addAttribute("students", students);
        model.addAttribute("pageModel", pageModel);
        return "view/selectStudent";
    }

    @RequestMapping({"/view/removeStudent.do"})
    public ModelAndView removeStu(@RequestParam("id") String ids, ModelAndView mv) {

        String[] idArray = ids.split(",");
        for (String idd : idArray) {

            this.stuService.removeStuById(Integer.parseInt(idd));
        }
        mv.setViewName("redirect:/view/selectStudent.do");
        return mv;
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    @RequestMapping({"/view/insertStudent.do"})
    /*     */ public ModelAndView addStu(@RequestParam("flag") String flag, @ModelAttribute Student student, ModelAndView mv) {
        /*  87 */
        if (flag.equals("1")) {
            /*
             *   */
            /*  89 */
            mv.setViewName("view/insertStudent");
            /*     */
        } else {
            /*     */
            /*  92 */
            this.stuService.insertStudent(student);
            /*     */
            /*  94 */
            mv.setViewName("redirect:/view/selectStudent.do");
            /*     */
        }
        /*  97 */
        return mv;
        /*     */
    }

    /*     */
    /*     */
    @RequestMapping({"/view/updateStudent.do"})
    public ModelAndView updateStu(String flag, @ModelAttribute Student student, ModelAndView mv) {
        if (flag.equals("1")) {

            Student target = this.stuService.findStuById(student.getId());

            mv.addObject("student", target);
            mv.setViewName("view/updateStudent");
        } else {

            this.stuService.modifyStu(student);

            mv.setViewName("redirect:/view/selectStudent.do");
        }

        return mv;
    }
}

