package com.tindao.osworks.api.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ComentarioInput 
{
	@NotNull
	private String descricao;
}
