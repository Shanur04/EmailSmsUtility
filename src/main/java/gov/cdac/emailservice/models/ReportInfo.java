package gov.cdac.emailservice.models;

public class ReportInfo {
	private String reportName;
	private String reportPath;
	
	public ReportInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public ReportInfo(String reportName, String reportPath) {
		super();
		this.reportName = reportName;
		this.reportPath = reportPath;
	}


	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
}
