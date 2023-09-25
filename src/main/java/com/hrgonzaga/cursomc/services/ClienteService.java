package com.hrgonzaga.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrgonzaga.cursomc.domain.Cidade;
import com.hrgonzaga.cursomc.domain.Cliente;
import com.hrgonzaga.cursomc.domain.Endereco;
import com.hrgonzaga.cursomc.domain.enums.TipoCliente;
import com.hrgonzaga.cursomc.dto.ClienteDTO;
import com.hrgonzaga.cursomc.dto.ClienteNewDTO;
import com.hrgonzaga.cursomc.repository.ClienteRepository;
import com.hrgonzaga.cursomc.repository.EnderecoRepository;
import com.hrgonzaga.cursomc.services.exception.DataIntegrityException;
import com.hrgonzaga.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cliente find (Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar um cliente associada a pedidos");
		}
	}

	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO dto) {
		return new Cliente(dto.getId(),dto.getNome(),dto.getEmail(),null,null,null);
	}
	
	
	public Cliente fromDto(ClienteNewDTO dto) {
		Cliente cli = new Cliente(null,dto.getNome(),dto.getEmail(),dto.getCpfOuCnpj(),TipoCliente.toEnum(dto.getTipo()),pe.encode(dto.getSenha()));
		Cidade cid = new Cidade(dto.getCidadeId(),null,null);
		Endereco end = new Endereco(null,dto.getLogradouro(),dto.getNumero(),dto.getComplemento(), dto.getBairro(), dto.getCep(),
				cid,cli); 
		cli.getEnderecos().add(end);
		cli.getTelefones().add(dto.getTelefone1());
		if (dto.getTelefone2()!=null) {
			cli.getTelefones().add(dto.getTelefone2());
		}
		if (dto.getTelefone3()!=null) {
			cli.getTelefones().add(dto.getTelefone3());
		}
		 return cli;
	}

}
