package com.dragon.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.dragon.entity.User;
import com.dragon.service.StuService;
import com.dragon.util.tag.PageModel;

@Controller
public class UserController {
	//   	@Autowired
	//   	@Qualifier("stuService")
	//			两者配合注解，表明说需要的类 名字需要与接口实现类一致 @Service("stuService")
	@Resource
	private StuService stuService;

	@RequestMapping({"/login.do"})/*获取参数 */
	public ModelAndView login(@RequestParam(value = "name", required = false) String name,
							  @RequestParam(value = "password", required = false)
									  String password, HttpSession session, @RequestParam("accesscode") String accesscode, ModelAndView mv) {
		String captcha = (String) session.getAttribute("captcha");
		System.out.println("验证码" + captcha + " accesscode:" + accesscode);
		if (!accesscode.equalsIgnoreCase(captcha)) {
			mv.addObject("message", "验证码错误!请重新输入！");
			mv.setViewName("forward:loginForm.jsp");
		} else {
			User user = this.stuService.login(name, password);
			if (user != null) {
				System.out.println("登录user:" + user);
				session.setAttribute("user_session", user);
				/*   session.removeAttribute("user");   */
				mv.setViewName("forward:index.do");
			} else {
				if (stuService.login(name, password) != null) {
					System.out.println("第二次校验user是否存在:" + stuService.login(name, password));
					mv.addObject("message", "登录名或密码错误！请重新输入");
					mv.setViewName("forward:loginForm.jsp");
				}
			}
			return mv;
		}
		return mv;
	}

	@RequestMapping("/index.do")
	public String index() {
		return "forward:index.jsp";
	}

	@RequestMapping("/loginForm.do")
	public String loginForm() {
		return "forward:loginForm.jsp";
	}

	@RequestMapping({"/logout.do"})
	public ModelAndView logout(HttpSession session, ModelAndView mv) {
		//清除session
		//	session.removeAttribute("user");
		session.invalidate();
		mv.setViewName("forward:loginForm.jsp");
		return mv;
	}

	/*@ModelAttribute
	 * 负责绑定请求参数到指定对象
	 * eg 查询 name和password--》user   */
	/*     */
	/*     */
	@RequestMapping({"/view/selectUser.do"})
	/*     */ public String selectUser(Integer pageIndex, @ModelAttribute User user, Model model) {
		/*  69 */
		System.out.println("查询前user = " + user);
		/*  70 */
		PageModel pageModel = new PageModel();
		/*  71 */
		if (pageIndex != null) {
			/*  72 */
			pageModel.setPageIndex(pageIndex.intValue());
			/*     */
		}
		/*     */
		/*  75 */
		List<User> users = this.stuService.findUser(user, pageModel);
		System.out.println("查询后=======");
		users.forEach(System.out::println);
		/*  76 */
		model.addAttribute("users", users);
		/*  77 */
		model.addAttribute("pageModel", pageModel);
		/*  78 */
		return "view/selectUser";
		/*     */
	}


	/*     */
	@RequestMapping({"/view/removeUser.do"})
	/*     */ public ModelAndView removeUser(@RequestParam("id") String ids, ModelAndView mv) {
		String[] idArray = ids.split(",");
		for (String idd : idArray) {
			this.stuService.removeUserById(Integer.valueOf(Integer.parseInt(idd)));
			/*     */
		}
		/*  96 */
		mv.setViewName("redirect:/view/selectUser.do");
		/*     */
		/*  98 */
		return mv;
		/*     */
	}

	/*     */
	/*     */
	/*     */
	@RequestMapping({"/view/updateUser.do"})
	/*     */ public ModelAndView updateUser(String flag, @ModelAttribute User user, HttpSession session, MultipartFile multipartFile, ModelAndView mv) throws IOException {
		/* 113 */
		if (flag.equals("1")) {
			User target = this.stuService.findUserById(user.getId());
			/* 117 */
			mv.addObject("user", target);
			mv.setViewName("view/updateUser");
			/*     */
		} else {
			user.setMultipartFile(multipartFile);
			String originalFilename = multipartFile.getOriginalFilename();
			String contentType = multipartFile.getContentType();
			//生成唯一id 并拼接成文件名
			String uuid = UUID.randomUUID().toString().substring(0, 5);
			//获取文件后缀名
			String extension = FilenameUtils.getExtension(originalFilename);
			String downloadfile = uuid + "." + extension;
			session.setAttribute("filename", downloadfile);
			System.out.println("原始文件名：" + originalFilename + " 类型：" + contentType);
			String realPath = session.getServletContext().getRealPath("/upload");
			System.out.println("realPath" + realPath);
			System.out.println(downloadfile);
			multipartFile.transferTo(new File(realPath + "\\" + downloadfile));
			user.setImage("upload/" + downloadfile);
			stuService.modifyUser(user);
			System.out.println("上传后user：" + user);
			mv.setViewName("redirect:/view/selectUser.do");
			/*     */
		}
		return mv;
		/*     */
	}

	/*     */
	/*     */
	/*     */
	@RequestMapping({"/view/insertUser.do"})
	/*     */ public ModelAndView addUser(String name, @RequestParam(value = "password", required = false) String password,
										  HttpSession session, String flag, @ModelAttribute User user, ModelAndView mv, MultipartFile multipartFile, String accesscode) throws IOException {
		/* 142 */
		if (flag.equals("1")) {
			/* 144 */
			mv.setViewName("view/insertUser");
			/*     */
		} else {
			/*     */        //生成唯一id 并拼接成文件名
			String uuid = UUID.randomUUID().toString().substring(0, 5);
			String downloadfile = uuid + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
			String realPath = session.getServletContext().getRealPath("/upload");
			multipartFile.transferTo(new File(realPath + "\\" + downloadfile));
			user.setImage("upload/" + downloadfile);
			session.setAttribute("filename", downloadfile);
			//							String captcha = (String) session.getAttribute("captcha");
			if (user.getName().equals("") || user.getName().equals("")) {
				mv.setViewName("redirect:/loginForm.do");
			} else {
				this.stuService.addUser(user);
				System.out.println("新成功注册用户：" + user);
				mv.setViewName("forward:/login.do?accesscode=" + accesscode);
			}
			/*     */
		}
		/* 152 */
		return mv;
		/*     */
	}
	/*     */
}

