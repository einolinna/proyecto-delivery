package com.delivery.BuenSabor.Factura.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Email {

	private String to;

	private String subject;

	private String text;

	private String pathToAttachment;

	public Email(String to, String subject, String text, String pathToAttachment) {
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.pathToAttachment = pathToAttachment;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPathToAttachment() {
		return pathToAttachment;
	}

	public void setPathToAttachment(String pathToAttachment) {
		this.pathToAttachment = pathToAttachment;
	}
	
	
}
