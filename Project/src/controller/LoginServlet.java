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
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		//暗号化記述
		//ハッシュを生成したい元の文字列
		String source = password;
		//上記コードによりpaswordがsourceへと代替された。暗号化したい対象の明確化
		//ハッシュ生成前にバイト配列に置き換える際のCharset
		Charset charset = StandardCharsets.UTF_8;
		//ハッシュアルゴリズム
		String algorithm = "MD5";
		//md5という暗号化処理の仕方を行なう、というコード

		//ハッシュ生成処理
		byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			//上記コードによりsourceがbytesへと代替された。対象の暗号処理化その１
		}
		String resultafter = DatatypeConverter.printHexBinary(bytes);
		//上記コードによりbytesがresultafterへと代替された。対象の暗号処理化その２。以降passwordはresultafterになる

		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		UserDao userDao = new UserDao();
		User user = userDao.findByLoginInfo(loginId, resultafter);

		/** テーブルに該当のデータが見つからなかった場合 **/
		if (user == null) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "ログインIDまたはパスワードが異なります");

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		/** テーブルに該当のデータが見つかった場合 **/
		// セッションにユーザの情報をセット
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", user);

		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");

	}

}
