package br.com.rft.peculium.dtos;

public class MailTO {
	private String toName;
	private String to;
	private String replyTo;
	private String from;
	private String subject;
	private String text;
	private String activeCode;
	private String templateName;
	private String contentType;

	public MailTO() {
		contentType = "text/html";
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getTo() {
		return to;
	}

	public String getFrom() {
		return from;
	}

	public String getSubject() {
		return subject;
	}

	public String getText() {
		return text;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	@Override
	public String toString() {
		return "MailTO [toName=" + toName + ", to=" + to + ", replyTo=" + replyTo + ", from=" + from + ", subject="
				+ subject + ", text=" + text + ", activeCode=" + activeCode + ", templateName=" + templateName
				+ ", contentType=" + contentType + "]";
	}

}
