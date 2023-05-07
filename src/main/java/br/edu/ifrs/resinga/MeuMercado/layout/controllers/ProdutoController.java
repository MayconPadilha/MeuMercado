package br.edu.ifrs.resinga.MeuMercado.layout.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.edu.ifrs.resinga.MeuMercado.model.Produto;
import br.edu.ifrs.resinga.MeuMercado.repository.ProdutoRepository;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/formulario") //funciona
    public String formulario() {
        return "formulario";
    }

    @PostMapping("/formulario") //funciona
    public String cadastrarProduto(Produto produto) {
        repository.save(produto);
        System.out.println(produto);
        return "redirect:/lista_produtos";
    }

    @GetMapping("/lista_produtos")
    public ModelAndView lista(){
        ModelAndView mv = new ModelAndView("lista_produtos");
        // List<DadosListagemProduto> listaProdutos = repository.findAll().stream().map(DadosListagemProduto::new).toList(); //funciona
        List<Produto> listaProdutos = repository.findAll(); //funciona

        System.out.println(listaProdutos);

        mv.addObject("produtos", listaProdutos);
        return mv;
    }

}