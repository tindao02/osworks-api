package com.tindao.osworks.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.constraints.NotBlank;
import javax.validation.Valid;
import javax.validation.groups.Default;

import com.tindao.osworks.domain.ValidationGroups;
import com.tindao.osworks.domain.enuns.StatusOrdemServico;
import com.tindao.osworks.api.model.Comentario;
import com.tindao.osworks.domain.exception.NegocioException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Entity
@Data
public class OrdemServico 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@ConvertGroup(from = Default .class, to = ValidationGroups.ClienteId.class)
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusOrdemServico status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataAbertura;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	
	@OneToMany(mappedBy = "ordemServico")
	private List<Comentario> comentarios = new ArrayList<>();
	
	public void finalizar()
	{
		if(!StatusOrdemServico.ABERTA.equals(getStatus()))
		{
			throw new NegocioException("Ordem de serviço não pode ser finalizada.");
		}
		
		setStatus(StatusOrdemServico.FINALIZADA);
		setDataFinalizacao(OffsetDateTime.now());
	}
	
}
