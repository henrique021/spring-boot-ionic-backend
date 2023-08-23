package com.hrgonzaga.cursomc;

import java.text.SimpleDateFormat;
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
import com.hrgonzaga.cursomc.domain.ItemPedido;
import com.hrgonzaga.cursomc.domain.Pagamento;
import com.hrgonzaga.cursomc.domain.PagamentoComBoleto;
import com.hrgonzaga.cursomc.domain.PagamentoComCartao;
import com.hrgonzaga.cursomc.domain.Pedido;
import com.hrgonzaga.cursomc.domain.Produto;
import com.hrgonzaga.cursomc.domain.enums.TipoCliente;
import com.hrgonzaga.cursomc.enums.EstadoPagamento;
import com.hrgonzaga.cursomc.repository.CategoriaRepository;
import com.hrgonzaga.cursomc.repository.CidadeRepository;
import com.hrgonzaga.cursomc.repository.ClienteRepository;
import com.hrgonzaga.cursomc.repository.EnderecoRepository;
import com.hrgonzaga.cursomc.repository.EstadoRepository;
import com.hrgonzaga.cursomc.repository.ItemPedidoRepository;
import com.hrgonzaga.cursomc.repository.PagamentoRepository;
import com.hrgonzaga.cursomc.repository.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedRep ;
	@Autowired
	private PagamentoRepository pagRep ;
	@Autowired
	private ItemPedidoRepository itemPedRep ;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Override
	public void run(String...args ) throws Exception{
		
		Categoria categoria1 = new Categoria(null,"Informática");
		Categoria categoria2 = new Categoria(null,"Escritório");
		Categoria categoria3 = new Categoria(null,"Cama Mesa e Banho");
		Categoria categoria4 = new Categoria(null,"Jogos");
		Categoria categoria5 = new Categoria(null,"Cozinha");
		Categoria categoria6 = new Categoria(null,"Banheiro");
		Categoria categoria7 = new Categoria(null,"Alimenticios");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est1);
		
		Cliente cli1 = new Cliente(null,"Maria Silvia","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27633323","93838323"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 203","Jardim","3822834",c1,cli1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",c2,cli1);
		

		
		categoria1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		categoria2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(categoria1));
		p2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		p3.getCategorias().addAll(Arrays.asList(categoria1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");
		
		
		Pedido ped1 = new Pedido(0,sdf.parse("30/09/2017 10:17"),cli1,e1);
		PagamentoComCartao pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		
		Pedido ped2 = new Pedido(0,sdf.parse("10/10/2017 19:35"),cli1,e2);
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,
							sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped2.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		
		
		catRep.saveAll(Arrays.asList(categoria1,categoria2,categoria3,
				categoria4,categoria5,categoria6,categoria7));
		proRep.saveAll(Arrays.asList(p1,p2,p3));
		estRep.saveAll(Arrays.asList(est1,est2));
		cidRep.saveAll(Arrays.asList(c1,c2,c3));
		cliRep.saveAll(Arrays.asList(cli1));
		endRep.saveAll(Arrays.asList(e1,e2));
		pedRep.saveAll(Arrays.asList(ped1,ped2));
		pagRep.saveAll(Arrays.asList(pagto1,pagto2));
		itemPedRep.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
		
	}

	

}
