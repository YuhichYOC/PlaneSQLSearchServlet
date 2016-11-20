package test04.eureka3.com;

import java.sql.ResultSet;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.*;

public class SearchResult extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	// ログ出力クラス
	public static Log log = LogFactory.getLog(SearchResult.class);
	
	// 検索キー１
	private String strCodeFrom = "";
	// 検索キー１のセット
	public void setConditionCodeFrom(String value) {
		this.strCodeFrom = value;
	}
	
	// 検索キー２
	private String strCodeTo = "";
	// 検索キー２のセット
	public void setConditionCodeTo(String value) {
		this.strCodeTo = value;
	}
	
	private void writeTableHead() throws IOException {
		
		pageContext.getOut().write("<table class=\"resultTable\">" + "\n");
		
	}
	
	private void writeTRHead() throws IOException {
		
		pageContext.getOut().write("<tr>" + "\n");
		
	}
	
	private void writeOneRow (String prefCode, String prefName) throws IOException {
		
		pageContext.getOut().write("<td class=\"resultTable\">" + prefCode + "</td>" + "\n");
		pageContext.getOut().write("<td class=\"resultTable\">" + prefName + "</td>" + "\n");
		
	}
	
	private void writeTRTail() throws IOException {
		
		pageContext.getOut().write("</tr>" + "\n");
		
	}
	
	private void writeTableTail() throws IOException {
		
		pageContext.getOut().write("</table>" + "\n");
		
	}
	
	private boolean isNullorEmpty(String value) {
		
		if(value == null || value.length() == 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public int doStartTag() throws JspException {
		
		try {
			
			if(isNullorEmpty(this.strCodeFrom) || isNullorEmpty(this.strCodeTo)) {
				
				return SKIP_BODY;
				
			} else {
				
				MySQLAccessor acc = new MySQLAccessor();
				
				acc.setDataSource("test");
				acc.setUserId("test");
				acc.setPassword("password");
				
				acc.createConnectionString();
				acc.createConnection();
				
				ResultSet rs = acc.executeSelectTest04(strCodeFrom, strCodeTo);
				if(rs == null) {
					return SKIP_BODY;
				}
				
				writeTableHead();
				
				while(rs.next()) {
					
					writeTRHead();
					writeOneRow(rs.getString("PREF_CODE"), rs.getString("PREF_NAME"));
					writeTRTail();
					
				}
				
				writeTableTail();
				
			}
			
		} catch(Exception e) {
			
			log.error("Error Occured : ", e);
			
			throw new JspException(e);
			
		}
		
		return SKIP_BODY;
		
	}
	
}
