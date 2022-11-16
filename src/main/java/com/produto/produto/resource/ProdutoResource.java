package com.produto.produto.resource;

import com.produto.produto.entities.Produto;
import com.produto.produto.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> findAll(){
        return new ResponseEntity<>(produtoService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        return new ResponseEntity<>(produtoService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Produto> save(@RequestBody Produto produto){
        return new ResponseEntity<>(produtoService.saveProduto(produto), HttpStatus.OK);
    }

}
