package br.edu.ifrs.resinga.MeuMercado.layout.controllers;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrs.resinga.MeuMercado.layout.controllers.dto.DadosCadastroProduto;
import br.edu.ifrs.resinga.MeuMercado.model.Produto;
import br.edu.ifrs.resinga.MeuMercado.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/")
    public String home(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Usuário ou senha inválidos.");
        }
        return "login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Usuário ou senha inválidos.");
        }
        return "login";
    }

    @PostMapping("/login") 
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password){                     
        if (username.equals("admin") && password.equals("root")) {
            return "redirect:/lista_produtos";
        }
        if (username.equals("usuario") && password.equals("usuario")) {
            return "redirect:/lista_produtos_usuario";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/formulario")
    public String formulario(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "cadastrado", required = false) String mensagem, 
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMessageCadastro", "O código já existe");
        } 
        if (mensagem != null) {
            model.addAttribute("mensagemCadastro", "Produto cadastrado com sucesso!");
        }
        return "formulario";
    }

    @PostMapping("/formulario")
    public String cadastrarProduto(DadosCadastroProduto dados, Model model) {
        boolean codigoExistente = repository.existsByCodigo(dados.codigo());
        if (codigoExistente) {
            return "redirect:/formulario?error";
        } else {
            var produto = new Produto(dados);
            repository.save(produto);
            return "redirect:/formulario?cadastrado";
        }
    }

    @GetMapping("/lista_produtos")
    public ModelAndView lista(){
        ModelAndView mv = new ModelAndView("lista_produtos");
        List<Produto> listaProdutos = repository.findAll();
        mv.addObject("produtos", listaProdutos);
        return mv;
    }

    @GetMapping("/buscarNome")
    public ModelAndView buscarProdutoPorTipo(@RequestParam String nome) {
        ModelAndView mv = new ModelAndView("lista_produtos");
        Stream<Produto> produtoFind = repository.findAll().stream().filter(produto -> nome.equals(produto.getNome())); //pega o codigo do produto e compara com os dados do banco
        mv.addObject("produtos", produtoFind);
        return mv;
    }

    @GetMapping("/buscarTipo")
    public ModelAndView buscarProdutoPorNome(@RequestParam String tipo) {
        ModelAndView mv = new ModelAndView("lista_produtos");
        Stream<Produto> produtoFind = repository.findAll().stream().filter(produto -> tipo.equals(produto.getTipo())); //pega o codigo do produto e compara com os dados do banco
        mv.addObject("produtos", produtoFind);
        return mv;
    }

    @GetMapping("/edit/{codigo}")
    public ModelAndView editar(@PathVariable("codigo") String codigo, Model model){
        ModelAndView mv = new ModelAndView("editarProduto");
        boolean bloquearEdicao = true;
        Produto produtoFind = repository.findAll().stream().filter(produto -> codigo.equals(produto.getCodigo())).findFirst().get(); //pega o codigo do produto e compara com os dados do banco
        model.addAttribute("bloquearEdicao", bloquearEdicao);
        mv.addObject("produtos", produtoFind); //cria o objeto produtos e envia os dados para ser pego no front
        return mv;
    }

    @PostMapping("/salvarEdicao")
    @Transactional
    public String salvarEdicao(DadosCadastroProduto dados) {
        Produto produto = repository.findByCodigo(dados.codigo()); // busca o produto no banco pelo código
        produto.setValor(dados.valor());// atualiza os dados do produto com os dados do formulário
        produto.setEstoque(dados.estoque());
        repository.save(produto); // salva as alterações no banco

        return "redirect:/lista_produtos"; // redireciona para a lista de produtos
    }

    @GetMapping("/delet/{codigo}")
    @Transactional
    public String deletar(@PathVariable("codigo") String codigo){
        repository.deleteByCodigo(codigo);
        return "redirect:/lista_produtos";
    }

// ------------------------------------------------------------------------

    @GetMapping("/lista_produtos_usuario")
    public ModelAndView listaUsuario(){
        ModelAndView mv = new ModelAndView("lista_produtos_usuario");
        List<Produto> listaProdutos = repository.findAll();
        mv.addObject("produtos", listaProdutos);
        return mv;
    }

    @GetMapping("/buscarNome_usuario")
    public ModelAndView buscarProdutoPorTipoUsuario(@RequestParam String nome) {
        ModelAndView mv = new ModelAndView("lista_produtos_usuario");
        Stream<Produto> produtoFind = repository.findAll().stream().filter(produto -> nome.equals(produto.getNome())); //pega o codigo do produto e compara com os dados do banco
        mv.addObject("produtos", produtoFind);
        return mv;
    }

    @GetMapping("/buscarTipo_usuario")
    public ModelAndView buscarProdutoPorNomeUsuario(@RequestParam String tipo) {
        ModelAndView mv = new ModelAndView("lista_produtos_usuario");
        Stream<Produto> produtoFind = repository.findAll().stream().filter(produto -> tipo.equals(produto.getTipo())); //pega o codigo do produto e compara com os dados do banco
        mv.addObject("produtos", produtoFind);
        return mv;
    }

}