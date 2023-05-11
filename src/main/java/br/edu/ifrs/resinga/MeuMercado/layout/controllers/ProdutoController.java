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
import br.edu.ifrs.resinga.MeuMercado.model.Produto;
import br.edu.ifrs.resinga.MeuMercado.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/login") //funciona
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Usuário ou senha inválidos.");
        }
        return "login";
    }

    @PostMapping("/login") //funciona
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

    @GetMapping("/formulario") //funciona
    public String formulario(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Usuário ou senha inválidos.");
        }
        return "formulario";
    }

    @PostMapping("/formulario") //funciona
    public String cadastrarProduto(Produto produto) {
        System.out.println(produto);
        return "redirect:/lista_produtos";
    }

    @GetMapping("/lista_produtos") //funciona
    public ModelAndView lista(){
        ModelAndView mv = new ModelAndView("lista_produtos");
        // List<DadosListagemProduto> listaProdutos = repository.findAll().stream().map(DadosListagemProduto::new).toList(); //funciona
        List<Produto> listaProdutos = repository.findAll(); //funciona

        System.out.println(listaProdutos);

        mv.addObject("produtos", listaProdutos);
        return mv;
    }

    @GetMapping("/lista_produtos_usuario") //funciona
    public ModelAndView listaUsuario(){
        ModelAndView mv = new ModelAndView("lista_produtos_usuario");
        List<Produto> listaProdutos = repository.findAll();
        mv.addObject("produtos", listaProdutos);
        return mv;
    }

    @GetMapping("/edit/{codigo}")
    public ModelAndView editar(@PathVariable("codigo") String codigo){
        ModelAndView mv = new ModelAndView("editarProduto");

        Produto produtoFind = repository.findAll().stream().filter(produto -> codigo.equals(produto.getCodigo())).findFirst().get(); //pega o codigo do produto e compara com os dados do banco

        mv.addObject("produtos", produtoFind); //cria o objeto produtos e envia os dados para ser pego no front
        return mv;
    }

    @GetMapping("/buscarNome")
    public ModelAndView buscarProdutoPorTipo(@RequestParam String nome) {
        ModelAndView mv = new ModelAndView("lista_produtos");

        Produto produtoFind = repository.findAll().stream().filter(produto -> nome.equals(produto.getNome())).findFirst().get(); //pega o codigo do produto e compara com os dados do banco

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

    @GetMapping("/delet/{codigo}")
    @Transactional
    public String deletar(@PathVariable("codigo") String codigo){
        System.out.println("estou deletando: "+codigo);
        repository.deleteByCodigo(codigo);
        return "redirect:/lista_produtos";
    }
}