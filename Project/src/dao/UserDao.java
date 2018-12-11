package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDao {

	/**
	 * ログインIDとパスワードに紐づくユーザ情報を返す
	 * @param loginId
	 * @param password
	 * @return
	 */
	public User findByLoginInfo(String loginId, String password) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();

			// 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
			if (!rs.next()) {
				return null;
			}

			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			return new User(loginIdData, nameData);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	/**
	 * 全てのユーザ情報を取得する
	 * @return
	 */
	public List<User> findAll() {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			// TODO: 未実装：管理者以外を取得するようSQLを変更する
			String sql = "SELECT * FROM user where login_id!='admin' ";



			// SELECTを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	//userRegiで使用
	public boolean InsertUser(String loginId, String password, String name, String birthdate) {

		Connection conn = null;

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			String sql = "insert into User (login_Id, password, name, birth_date,update_date,create_date)"
					+ "values(?,?,?,?,now(),now())";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, birthdate);

			int result = pstmt.executeUpdate();

			System.out.println(result);

			pstmt.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return false;

	}

	//UserDeleteにも使用
	//UserDetailに使用
	public User findById(String id) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE id = ? ";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			ResultSet rs = pStmt.executeQuery();

			// 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
			if (!rs.next()) {
				return null;
			}

			int idCatch = rs.getInt("id");
			String password = rs.getString("password");
			String loginIdDate = rs.getString("login_id");
			String nameData = rs.getString("name");
			Date birthDate = rs.getDate("birth_date");
			String createDate = rs.getString("create_date");
			String updateDate = rs.getString("update_date");
			return new User(idCatch, loginIdDate, nameData, birthDate, password, createDate, updateDate);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}


	//userDeleteで使用

	public void deleteData(String id) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			String sql = "DELETE FROM user WHERE id=? " ;

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,id);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}

	}

	//userUpdateで使用
	public void updateData(String password, String name,String birthdate,String id) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			String sql = "UPDATE user SET password=?, name=?, birth_date = ?,update_date=now() where id=? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, password);
			pStmt.setString(2, name);
			pStmt.setString(3, birthdate);
			pStmt.setString(4, id);
			pStmt.executeUpdate();
	}

		catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}

	}

	public List<User> findYou(String loginIdFind,String nameFind,String birthDateAfter,String birthDateBefore) {
		Connection conn = null;
		List<User> userSearch = new ArrayList<User>();

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			// TODO: 未実装：管理者以外を取得するようSQLを変更する
			String sql = "SELECT * FROM user where login_id!='admin' ";

			if(!loginIdFind.isEmpty()) {
				sql += "AND login_id = '"+loginIdFind+"' ";
			}

			if(!nameFind.isEmpty()) {
				sql += "AND name like '%"+nameFind+"%' ";
			}

			if (!birthDateAfter.isEmpty()) {
				sql += "AND birth_date <= '"+birthDateAfter+"' ";
			}

			if (!birthDateBefore.isEmpty()) {
				sql += "AND birth_date >= '"+birthDateBefore+"' ";
			}

			// SELECTを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

				userSearch.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userSearch;
	}
}