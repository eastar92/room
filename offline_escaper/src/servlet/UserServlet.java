package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserInfo;
import service.UserService;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resq) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");

		// String name1 = req.getParameter("name");
		// String pwd1 = req.getParameter("pass");
		// String op1 = req.getParameter("op");
		// String id1 = req.getParameter("id");
		// String pw1 = req.getParameter("pwd");
		// System.out.println("input html에서 너님이 던진 값 => " + id1 + "," + pw1);

		Map<String, String[]> reqMap = req.getParameterMap();
		System.out.println(reqMap);
		Iterator<String> it = reqMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
		}

		String userNum = req.getParameter("usernum");
		String userId = req.getParameter("userid");
		String userPwd = req.getParameter("userpwd");
		String userName = req.getParameter("username");
		String address = req.getParameter("address");
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		String age = req.getParameter("age");
		UserInfo ui = new UserInfo();
		if (userNum != null) {
			ui.setUserNum(Integer.parseInt(userNum));
		}
		ui.setUserId(userId);
		ui.setUserPwd(userPwd);
		ui.setUserName(userName);
		ui.setAddress(address);
		ui.setHp1(hp1);
		ui.setHp2(hp2);
		ui.setHp3(hp3);
		if (age != null) {
			ui.setAge(Integer.parseInt(age));
		}

		String command = req.getParameter("command");
		if (command == null) {
			return;
		}

		UserService us = new UserService();
		if (command.equals("LOGIN")) {
			String result = us.loginUser(ui);
			doProcess(resq, result);
		} else if (command.equals("SIGNIN")) {

			userId = req.getParameter("userid");
			userPwd = req.getParameter("userpwd");
			userName = req.getParameter("username");
			address = req.getParameter("address");
			hp1 = req.getParameter("hp1");
			hp2 = req.getParameter("hp2");
			hp3 = req.getParameter("hp3");
			age = req.getParameter("age");
			ui = new UserInfo();
			ui.setUserId(userId);
			ui.setUserPwd(userPwd);
			ui.setUserName(userName);
			ui.setAddress(address);
			ui.setHp1(hp1);
			ui.setHp2(hp2);
			ui.setHp3(hp3);
			ui.setAge(Integer.parseInt(age));

			if (us.insertUser(ui)) {
				doProcess(resq, "저장 잘 됐다");
			} else {
				doProcess(resq, "값 다시 입력");
			}
		} else if (command.equals("DELETE")) {
			boolean isDelete = us.deleteUser(ui);
			String result = "";
			if (isDelete) {
				result = "삭제됨";
			} else {
				result = "삭제안됨";
			}
			doProcess(resq, result);
		} else if (command.equals("UPDATE")) {
			boolean isUpdate = us.updateUser(ui);
			String result = "";
			if (isUpdate) {
				result = "수정됨";
			} else {
				result = "수정안됨";
			}
			doProcess(resq, result);

		} else if (command.equals("SELECT")) {
			System.out.println("이름  :" + userName);
			if (userName != null && !userName.equals("")) {
				ui.setUserName("%" + userName + "%");
			}
			List<UserInfo> userList = us.selectUser(ui);
			String result = "번호{/}이름{/}아이디{/}나이{+}";
			result += "dis{/}en{/}en{/}en{+}";
			for (UserInfo ui2 : userList) {
				result += ui2.getUserNum() + "{/}" + ui2.getUserName() + "{/}" + ui2.getUserId() + "{/}" + ui2.getAge()
						+ "{+}";
			}
			result = result.substring(0, result.length() - 3);
			doProcess(resq, result);
		}
	}

	public void dePost(HttpServletRequest req, HttpServletResponse reqs) throws IOException {
		System.out.println("1");
	}

	public void doProcess(HttpServletResponse resq, String writeStr) throws IOException {
		resq.setContentType("text/html; charset = UTF-8");
		PrintWriter out = resq.getWriter();
		out.print(writeStr);

	}
}
