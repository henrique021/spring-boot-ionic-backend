package com.hrgonzaga.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.hrgonzaga.cursomc.domain.enums.Perfil;
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


@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
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
	
	
	public void instantiateTestDatabase() throws ParseException {
	
	Categoria categoria1 = new Categoria(null,"Informática");
	Categoria categoria2 = new Categoria(null,"Escritório");
	Categoria categoria3 = new Categoria(null, "Cama mesa e banho");
	Categoria categoria4 = new Categoria(null, "Eletrônicos");
	Categoria categoria5 = new Categoria(null, "Jardinagem");
	Categoria categoria6 = new Categoria(null, "Decoração");
	Categoria categoria7 = new Categoria(null, "Perfumaria");
	
	
	Produto p1 = new Produto(null,"Computador",2000.00);
	Produto p2 = new Produto(null,"Impressora",800.00);
	Produto p3 = new Produto(null,"Mouse",80.00);
	Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
	Produto p5 = new Produto(null, "Toalha", 50.00);
	Produto p6 = new Produto(null, "Colcha", 200.00);
	Produto p7 = new Produto(null, "TV true color", 1200.00);
	Produto p8 = new Produto(null, "Roçadeira", 800.00);
	Produto p9 = new Produto(null, "Abajour", 100.00);
	Produto p10 = new Produto(null, "Pendente", 180.00);
	Produto p11 = new Produto(null, "Shampoo", 90.00);
	
	Estado est1 = new Estado(null,"Minas Gerais");
	Estado est2 = new Estado(null,"São Paulo");
	
	Cidade c1 = new Cidade(null,"Uberlandia",est1);
	Cidade c2 = new Cidade(null,"São Paulo",est2);
	Cidade c3 = new Cidade(null,"Campinas",est1);
	
	Cliente cli1 = new Cliente(null,"Maria Silvia","hrgonnzaga@gmail.com","36378912377",TipoCliente.PESSOAFISICA,pe.encode("1324"));
	cli1.getTelefones().addAll(Arrays.asList("27633323","93838323"));
	
	Cliente cli2 = new Cliente(null,"Ana Abdomen","henrique.emailcurso@gmail.com","39800230017",TipoCliente.PESSOAFISICA,pe.encode("1324"));
	cli2.getTelefones().addAll(Arrays.asList("235461236","512315164"));
	cli2.addPerfil(Perfil.ADMIN);
	
	Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 203","Jardim","3822834",c1,cli1);
	Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",c2,cli1);
	Endereco e3 = new Endereco(null,"Avenida Floriano","2106",null,"Centro","25845662",c2,cli2);
	

	
	categoria1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	categoria2.getProdutos().addAll(Arrays.asList(p2));
	categoria2.getProdutos().addAll(Arrays.asList(p2, p4));
	categoria3.getProdutos().addAll(Arrays.asList(p5, p6));
	categoria4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
	categoria5.getProdutos().addAll(Arrays.asList(p8));
	categoria6.getProdutos().addAll(Arrays.asList(p9, p10));
	categoria7.getProdutos().addAll(Arrays.asList(p11));
	
	p1.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
	p2.getCategorias().addAll(Arrays.asList(categoria1, categoria2, categoria4));
	p3.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
	p4.getCategorias().addAll(Arrays.asList(categoria2));
	p5.getCategorias().addAll(Arrays.asList(categoria3));
	p6.getCategorias().addAll(Arrays.asList(categoria3));
	p7.getCategorias().addAll(Arrays.asList(categoria4));
	p8.getCategorias().addAll(Arrays.asList(categoria5));
	p9.getCategorias().addAll(Arrays.asList(categoria6));
	p10.getCategorias().addAll(Arrays.asList(categoria6));
	p11.getCategorias().addAll(Arrays.asList(categoria7));
	
	
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
	proRep.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
	estRep.saveAll(Arrays.asList(est1,est2));
	cidRep.saveAll(Arrays.asList(c1,c2,c3));
	cliRep.saveAll(Arrays.asList(cli1,cli2));
	endRep.saveAll(Arrays.asList(e1,e2,e3));
	pedRep.saveAll(Arrays.asList(ped1,ped2));
	pagRep.saveAll(Arrays.asList(pagto1,pagto2));
	itemPedRep.saveAll(Arrays.asList(ip1,ip2,ip3));
	
	
	}
	

}
