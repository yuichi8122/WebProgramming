package controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserUpdate
 */
@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		System.out.println(id);

		UserDao userDao = new UserDao();
		User idCatch = userDao.findById(id);

		request.setAttribute("user", idCatch);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String name = request.getParameter("name");
		String birthdate = request.getParameter("birthdate");


		if(name.equals("")||birthdate.equals("")){
			request.setAttribute("errMsg", "入力された内容は正しくありません");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
			return;
		}

		if (!(password.equals(confirmPassword))) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
			return;

		}

		//暗号化記述
		//ハッシュを生成したい元の文字列
		String source = password;
		//ハッシュ生成前にバイト配列に置き換える際のCharset
		Charset charset = StandardCharsets.UTF_8;
		//ハッシュアルゴリズム
		String algorithm = "MD5";

		//ハッシュ生成処理
		byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {

		}
		String resultafter = DatatypeConverter.printHexBinary(bytes);


		String id = request.getParameter("id");

		System.out.println(id);

		UserDao userDao = new UserDao();

		userDao.updateData(resultafter, name, birthdate,id);


		response.sendRedirect("UserListServlet");
	}

}
