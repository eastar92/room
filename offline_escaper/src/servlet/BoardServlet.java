package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;

public class BoardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resq) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");

		String name1 = req.getParameter("title");
		String pwd1 = req.getParameter("content");
		String writer = req.getParameter("writer");
		System.out.println(name1 + "," + pwd1 + "," + writer);
		String command = req.getParameter("command");
		if (command == null) {
			return;
		}
		BoardService bs = new BoardService();

		if (command.equals("SIGNIN")) {
			HashMap hm = new HashMap();
			hm.put("title", name1);
			hm.put("content", pwd1);
			hm.put("writer", writer);

			if (bs.insertUser(hm)) {
				doProcess(resq, "저장 잘 됨");
			} else {
				doProcess(resq, "다시 입력해");
			}

		} else if (command.equals("DELETE")) {
			String user_num = req.getParameter("writer");
			System.out.println("삭제할 번호 : " + writer);
			if (bs.deleteUser(writer)) {
				doProcess(resq, "삭제됨");
			} else {
				doProcess(resq, "삭제 안됨");
			}

		} else if (command.equals("UPDATE")) {

			String user_num = req.getParameter("user_num");
			System.out.println("업데이트 할 번호 : " + user_num);

			if (bs.updateUser(user_num)) {
				doProcess(resq, "업데이트 됨");
			} else {
				doProcess(resq, "업데이트 안됨");
			}
		}
	}

	public void dePost(HttpServletRequest req, HttpServletResponse reqs) throws IOException {

	}

	public void doProcess(HttpServletResponse resq, String writeStr) throws IOException {
		resq.setContentType("text/html; charset = UTF-8");
		PrintWriter out = resq.getWriter();
		out.print(writeStr);

	}
}