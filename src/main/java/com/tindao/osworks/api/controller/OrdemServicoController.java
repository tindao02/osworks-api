package com.tindao.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tindao.osworks.api.model.OrdemServicoRepresentationInput;
import com.tindao.osworks.api.model.OrdemServicoRepresentationModel;
import com.tindao.osworks.domain.model.OrdemServico;
import com.tindao.osworks.domain.repository.OrdemServicoRepository;
import com.tindao.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController 
{
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoRepresentationModel criar(@Valid @RequestBody OrdemServicoRepresentationInput ordemServicoRepresentationInput)
	{
		OrdemServico ordemServico = toEntity(ordemServicoRepresentationInput);
		return toRepresentationModel(gestaoOrdemServicoService.criar(ordemServico));
	}
	
	@GetMapping
	public List<OrdemServicoRepresentationModel> listar()
	{
		return toCollectionRepresentationModel(ordemServicoRepository.findAll());
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoRepresentationModel> buscar(@PathVariable Long ordemServicoId)
	{
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		
		if(ordemServico.isPresent())
		{
			OrdemServicoRepresentationModel ordemServicoRepresentationModel = toRepresentationModel(ordemServico.get());
			return ResponseEntity.ok(ordemServicoRepresentationModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{ordemServicoId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long ordemServicoId)
	{
		gestaoOrdemServicoService.finalizar(ordemServicoId);
	}
	
	private OrdemServicoRepresentationModel toRepresentationModel(OrdemServico ordemServico)
	{
		return modelMapper.map(ordemServico, OrdemServicoRepresentationModel.class);
	}
	
	private List<OrdemServicoRepresentationModel> toCollectionRepresentationModel(List<OrdemServico> ordensServico)
	{
		return ordensServico.stream()
				.map(ordemServico -> toRepresentationModel(ordemServico))
				.collect(Collectors.toList());
	}
	
	private OrdemServico toEntity(OrdemServicoRepresentationInput ordemServicoRepresentationInput)
	{
		return modelMapper.map(ordemServicoRepresentationInput, OrdemServico.class);
	}
	
}
