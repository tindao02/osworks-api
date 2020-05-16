package com.tindao.osworks.api.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ClienteIdInput 
{
	@NotNull
	private Long id;
}
