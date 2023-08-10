package com.hrgonzaga.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hrgonzaga.cursomc.domain.Categoria;
import com.hrgonzaga.cursomc.domain.Cidade;
import com.hrgonzaga.cursomc.domain.Cliente;
import com.hrgonzaga.cursomc.domain.Endereco;
import com.hrgonzaga.cursomc.domain.Estado;
import com.hrgonzaga.cursomc.domain.Produto;
import com.hrgonzaga.cursomc.domain.enums.TipoCliente;
import com.hrgonzaga.cursomc.repository.CategoriaRepository;
import com.hrgonzaga.cursomc.repository.CidadeRepository;
import com.hrgonzaga.cursomc.repository.ClienteRepository;
import com.hrgonzaga.cursomc.repository.EnderecoRepository;
import com.hrgonzaga.cursomc.repository.EstadoRepository;
import com.hrgonzaga.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRep ;
	@Autowired
	private ProdutoRepository proRep ;
	@Autowired
	private EstadoRepository estRep ;
	@Autowired
	private CidadeRepository cidRep ;
	@Autowired
	private ClienteRepository cliRep ;
	@Autowired
	private EnderecoRepository endRep ;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Override
	public void run(String...args ) throws Exception{
		
		Categoria categoria1 = new Categoria(0,"Informática");
		Categoria categoria2 = new Categoria(0,"Escritório");
		
		Produto p1 = new Produto(0,"Computador",2000.00);
		Produto p2 = new Produto(0,"Impressora",800.00);
		Produto p3 = new Produto(0,"Mouse",80.00);
		
		Estado est1 = new Estado(0,"Minas Gerais");
		Estado est2 = new Estado(0,"São Paulo");
		
		Cidade c1 = new Cidade(0,"Uberlandia",est1);
		Cidade c2 = new Cidade(0,"São Paulo",est2);
		Cidade c3 = new Cidade(0,"Campinas",est1);
		
		Cliente cli1 = new Cliente(0,"Maria Silvia","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27633323","93838323"));
		
		Endereco e1 = new Endereco(0,"Rua Flores","300","Apto 203","Jardim","3822834",c1,cli1);
		Endereco e2 = new Endereco(0,"Avenida Matos","105","Sala 800","Centro","38777012",c2,cli1);
		
		
		categoria1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		categoria2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(categoria1));
		p2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		p3.getCategorias().addAll(Arrays.asList(categoria1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		catRep.saveAll(Arrays.asList(categoria1,categoria2));
		proRep.saveAllAndFlush(Arrays.asList(p1,p2,p3));
		estRep.saveAll(Arrays.asList(est1,est2));
		cidRep.saveAll(Arrays.asList(c1,c2,c3));
		cliRep.saveAll(Arrays.asList(cli1));
		endRep.saveAll(Arrays.asList(e1,e2));
		
		
		
		
	}

	

}
