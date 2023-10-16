package gov.cdac.icgPojo;

import javax.persistence.*;

@Entity
@Table(name="sms_mobileno_excelsheet")
public class SmsMobileNoExcelsheet {
	@Id
	@SequenceGenerator(name = "sms_mobileno_excelsheet_fileno_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_mobileno_excelsheet_fileno_seq")
	@Column(name="fileno")
	private long fileNo;
	
	@Column(name="filepath")
	private String path;
	
	@Column(name="smstype")
	private String smsType;
	
	@Column(name="smssentType")
	private int smsSentType;
	
	public SmsMobileNoExcelsheet() {
		super();
	}

	public SmsMobileNoExcelsheet(long fileNo, String path, String smsType, int smsSentType) {
		super();
		this.fileNo = fileNo;
		this.path = path;
		this.smsType = smsType;
		this.smsSentType = smsSentType;
	}
	public SmsMobileNoExcelsheet( String path, String smsType, int smsSentType) {
		super();
		this.path = path;
		this.smsType = smsType;
		this.smsSentType = smsSentType;
	}

	public long getFileNo() {
		return fileNo;
	}

	public void setFileNo(long fileNo) {
		this.fileNo = fileNo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public int getSmsSentType() {
		return smsSentType;
	}

	public void setSmsSentType(int smsSentType) {
		this.smsSentType = smsSentType;
	}

	@Override
	public String toString() {
		return "sms_mobileNo_excelsheet [fileNo=" + fileNo + ", path=" + path + ", smsType=" + smsType
				+ ", smsSentType=" + smsSentType + "]";
	}
}
