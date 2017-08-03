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

import dto.RoomInfo;
import service.RoomService;

public class RoomServlet extends HttpServlet {

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

		String userNum = req.getParameter("num");
		String userId = req.getParameter("id");
		String userPwd = req.getParameter("pwd");
		String userName = req.getParameter("name");

		RoomInfo ri = new RoomInfo();
		if (userNum != null) {
			ri.setUserNum(Integer.parseInt(userNum));
		}
		ri.setUserId(userId);
		ri.setUserPwd(userPwd);
		ri.setUserName(userName);

		String command = req.getParameter("command");
		if (command == null) {
			return;
		}

		RoomService rs = new RoomService();
		if (command.equals("LOGIN")) {
			String result = rs.loginUser(ri);
			doProcess(resq, result);
		} else if (command.equals("SIGNIN")) {

			userId = req.getParameter("userid");
			userPwd = req.getParameter("userpwd");
			userName = req.getParameter("username");
			ri = new RoomInfo();
			ri.setUserId(userId);
			ri.setUserPwd(userPwd);
			ri.setUserName(userName);

			if (rs.insertUser(ri)) {
				doProcess(resq, "저장 잘 됐다");
			} else {
				doProcess(resq, "값 다시 입력");
			}
		} else if (command.equals("DELETE")) {
			boolean isDelete = rs.deleteUser(ri);
			String result = "";
			if (isDelete) {
				result = "삭제됨";
			} else {
				result = "삭제안됨";
			}
			doProcess(resq, result);
		} else if (command.equals("UPDATE")) {
			boolean isUpdate = rs.updateUser(ri);
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
				ri.setUserName("%" + userName + "%");
			}
			List<RoomInfo> userList = rs.selectUser(ri);
			String result = "번호{/}이름{/}아이디{/}나이{+}";
			result += "dis{/}en{/}en{/}en{+}";
			for (RoomInfo ri2 : userList) {
				result += ri2.getUserNum() + "{/}" + ri2.getUserName() + "{/}" + ri2.getUserId() + "{/}";
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
