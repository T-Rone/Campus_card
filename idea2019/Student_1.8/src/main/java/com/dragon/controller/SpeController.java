/*     */
package com.dragon.controller;
/*    ${} ��ռλ�ַ��滻    */

import java.util.List;

import com.dragon.entity.Department;
import com.dragon.util.tag.PageModel;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.beans.factory.annotation.Qualifier;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.ModelAttribute;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dragon.entity.Department;
import com.dragon.service.StuService;

@Controller
/*     */ public class SpeController {

    /*     */
    @Autowired
    /*  */
    @Qualifier("stuService")
    /*     */ private StuService stuService;
    /*     */

    @RequestMapping("/view/selectDepartment.do")
    public String selectSpe(Model model, Integer pageIndex, @ModelAttribute Department department) {
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex.intValue());
        }

        List<Department> departments = this.stuService.selectAllDepartment(department, pageModel);
        model.addAttribute("departments", departments);
        model.addAttribute("pageModel", pageModel);
        return "view/selectDepartment";
    }

    @RequestMapping({"/view/removeDepartment.do"})
    public ModelAndView removeSpe(@RequestParam("id") String ids, ModelAndView mv) {

        String[] idArray = ids.split(",");
        for (String idd : idArray) {

            this.stuService.removeDepartById(Integer.parseInt(idd));
        }
        mv.setViewName("redirect:/view/selectDepartment.do");
        return mv;
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    @RequestMapping({"/view/insertDepartment.do"})
    /*     */ public ModelAndView addSpe(String flag, @ModelAttribute Department department, ModelAndView mv) {
        /*  87 */
        if (flag.equals("1")) {
            /*
             * ͨ�� ���flag ��־ �ж��Ƿ���Ҫ���  left.jsp �� ��dept/showAddDept.jsp ������ @RequestMapping({"/dept/addDept"})
             *   */
            /*  89 */
            mv.setViewName("view/insertDepartment");
            /*     */
        } else {
            /*     */
            /*  92 */
            this.stuService.insertDepartment(department); //����������
            /*     */
            /*  94 */
            mv.setViewName("redirect:/view/selectDepartment.do");//��ͨ�������� ���� ��ѯҳ��
            /*     */
        }
        /*     */
        /*  97 */
        return mv;
        /*     */
    }

    /*     */
    /*     */
    @RequestMapping({"/view/updateDepartment.do"})
    public ModelAndView updateSpe(String flag, @ModelAttribute Department department, ModelAndView mv) {
        if (flag.equals("1")) {

            Department target = this.stuService.findDepartById(department.getId()); //�ĳ�1 ���ԶԵ�  Ī�� ҳ��department���� shutdown?

            mv.addObject("department", target);
            mv.setViewName("view/updateDepartment");
        } else {

            this.stuService.modifyDepart(department);

            mv.setViewName("redirect:/view/selectDepartment.do");
        }

        return mv;
    }
}

