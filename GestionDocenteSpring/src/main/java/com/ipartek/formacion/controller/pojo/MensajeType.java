package com.ipartek.formacion.controller.pojo;

public enum MensajeType {

	//Llama al constructor private de la Enum
	MSG_TYPE_SUCCESS("alert alert-success alert-dismissible"),
    MSG_TYPE_INFO("alert alert-info alert-dismissible"), 
    MSG_TYPE_WARNING("alert alert-warning alert-dismissible"), 
    MSG_TYPE_DANGER("alert alert-danger alert-dismissible");
	
	
	
	private String styles;

	private MensajeType(String styles) {
		this.styles = styles;
	}

	public String getStyles() {
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}
	
	
	
	
	
	
}
