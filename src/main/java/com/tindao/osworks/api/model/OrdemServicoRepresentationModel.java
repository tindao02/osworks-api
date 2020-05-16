package com.tindao.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.tindao.osworks.domain.enuns.StatusOrdemServico;

import lombok.Data;

@Data
public class OrdemServicoRepresentationModel 
{
	private Long id;
	private ClienteRepresentationModel cliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	
}
