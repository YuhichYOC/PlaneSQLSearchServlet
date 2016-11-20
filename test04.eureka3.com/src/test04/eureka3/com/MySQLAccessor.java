package test04.eureka3.com; 

import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.sql.*;

import org.apache.commons.logging.*;

public class MySQLAccessor {
	
	// ログ出力クラス
	public static Log log = LogFactory.getLog(MySQLAccessor.class);
	
	// データベース接続ユーザーID
	private String UserId = "";
	public void setUserId(String value) {
		this.UserId = value;
	}
	
	// データベース接続パスワード
	private String Password = "";
	public void setPassword(String value) {
		this.Password = value;
	}
	
	// データベース名
	private String DataSource = "";
	public void setDataSource(String value) {
		this.DataSource = value;
	}
	
	// 接続文字列
	private String connectionString = "";
	public void createConnectionString() {
		this.connectionString = "jdbc:mysql://localhost:3306/" + this.DataSource;
	}
	
	// コネクション・コネクションオープンメソッド
	private Connection myConnection = null;
	public void createConnection() throws SQLException {
		try {
			this.myConnection = DriverManager.getConnection(this.connectionString, this.UserId, this.Password);
		} catch(SQLException e) {
			log.error("Error Occured :", e);
		}
	}
	
	// コンストラクタ
	public MySQLAccessor() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
						
		} catch(ClassNotFoundException e) {
			
			log.error("Error Occured : ", e);
			
		}
		
	}
	
	// GC時メソッド・コネクション廃棄
	private void destruction() throws Throwable {
		
		if(this.myConnection != null) {
			
			this.myConnection.close();
			
		}
		
	}
	
	// GC時
	@Override
	protected void finalize() throws Throwable {
		
		try {
			
			super.finalize();
			
		} finally {
			
			destruction();
			
		}
		
	}
	
	// クエリ文字列・外部ファイルによる設定
	private String QueryString = "";
	public void setQueryString(String path, String encoding) {
		
		BufferedReader in = null;
		
		try {
			
			in = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));
			
			String oneLine = "";
			
			while((oneLine = in.readLine()) != null) {
				this.QueryString += oneLine;
			}
			
		} catch(Exception e) {
			
			log.error("Error Occured : ", e);
			
		} finally {
			
			try {
				
				in.close();
				
			} catch(IOException e) {
				
				log.error("Error Occured : ", e);
				
			}
			
		}
		
	}
	
	// SELECT文発行・結果セットの取得
	public ResultSet executeSelectQuery() {
		
		ResultSet retSet = null;
		
		try {
			
			PreparedStatement ps = null;
			
			ps = this.myConnection.prepareStatement(this.QueryString);
			
			retSet = ps.executeQuery();
			
		} catch(SQLException e) {
			
			log.error("Error Occured : ", e);
			
		}
		return retSet;
		
	}
	
	// test04.eureka3.com用・テストメソッド・PREF_CODEによる検索
	public ResultSet executeSelectTest04(String strCodeFrom, String strCodeTo) {
		
		ResultSet retSet = null;
		
		try {
			
			PreparedStatement ps = null;
			
			StringBuilder sqlQuery = new StringBuilder("");
			sqlQuery.append("SELECT                ");
			sqlQuery.append("       PREF_CODE      ");
			sqlQuery.append("     , PREF_NAME      ");
			sqlQuery.append("FROM                  ");
			sqlQuery.append("       test           ");
			sqlQuery.append("WHERE                 ");
			sqlQuery.append("       PREF_CODE >= ? ");
			sqlQuery.append("   AND                ");
			sqlQuery.append("       PREF_CODE <= ? ");
			
			ps = this.myConnection.prepareStatement(sqlQuery.toString());
			ps.setString(1, strCodeFrom);
			ps.setString(2, strCodeTo);
			
			retSet = ps.executeQuery();
			
		} catch(SQLException e) {
			
			log.error("Error Occured : ", e);
			
		}
		return retSet;
		
	}
	
}
