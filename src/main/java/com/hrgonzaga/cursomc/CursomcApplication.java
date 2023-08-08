package com.hrgonzaga.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hrgonzaga.cursomc.domain.Categoria;
import com.hrgonzaga.cursomc.domain.Produto;
import com.hrgonzaga.cursomc.repository.CategoriaRepository;
import com.hrgonzaga.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRep ;
	@Autowired
	private ProdutoRepository proRep ;

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
		
		categoria1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		categoria2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(categoria1));
		p2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		p3.getCategorias().addAll(Arrays.asList(categoria1));
		
		catRep.saveAll(Arrays.asList(categoria1,categoria2));
		proRep.saveAllAndFlush(Arrays.asList(p1,p2,p3));
	}

	

}
