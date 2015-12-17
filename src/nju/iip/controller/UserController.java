package nju.iip.controller;

import nju.iip.dao.AdminUserDao;
import nju.iip.dto.AdminUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private AdminUserDao userDao;
	
	/**
	 * 管理员登录
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(AdminUser user) {
		logger.info("login called");
		if(userDao.validate(user)) {
			return "index.jsp";
		}
		else {
			return "login-1.jsp";
		}
		
	}

}
