package br.edu.ifrs.resinga.MeuMercado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.resinga.MeuMercado.domain.model.DadosCadastroProduto;
import br.edu.ifrs.resinga.MeuMercado.domain.model.Produto;
import br.edu.ifrs.resinga.MeuMercado.repository.ProdutoRepository;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody DadosCadastroProduto dados){
        repository.save(new Produto(dados)); //converte o DTO para o tipo medico
        System.out.println(dados);
        return "produto cadastrado";
    }

    // @PostMapping
    // @Transactional
    // public void cadastrar(@RequestBody String dados){
    //     System.out.println(dados);
    // }

    // @GetMapping("/produtos")
    // public List<Produto> obterProdutos(){
    //     // List<Produto> produtos = repositorio.findAll();
    //     return produtos;
    // }

    // @PostMapping("/produtos")
    // public Produto cadastraProduto(@RequestBody Produto produto){
    //     Produto produtoSalva = repositorio.save(produto);
    //     return produtoSalva;
    // }

    // @PutMapping("/produtos")
    // public Produto atualizaProduto(@RequestBody Produto produto){


    //     Produto produtoSalva = repositorio.save(produto);
    //     return produtoSalva;
    // } 

    //  //ATUALIZACAO
    // @PutMapping("/atualiza/{id}")
    // public void atualizaProduto(@PathVariable int id, @RequestBody Produto produto){

    //     produto.getCodigo();
    //     Optional<Produto> recuperado = repositorio.findById(produto.getCodigo());

    //     if (produto.getCodigo(id) == id) {
    //         System.out.println("recuperado id: "+recuperado.getId()+" idAluno id: "+id);
    //         AlunoRepository.atualizar(Aluno, recuperado);
    //         System.out.println("atualizando Aluno");
    //        } else {
    //         System.out.println("Pessoa de id: "+Aluno.getId()+" nao encontrado");
    //        }
    // }

    // @DeleteMapping("/{id}")
    // @Transactional
    // public void removerProduto(@PathVariable Long id) {
    //     var produto = repositorio.getReferenceById(id);
    //     produto.;
    // }


    
}
