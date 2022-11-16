package com.produto.produto.services;

import com.produto.produto.dto.ProdutoCreatedEvent;
import com.produto.produto.entities.Produto;
import com.produto.produto.repositories.ProdutoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(Long id){
        return produtoRepository.findById(id).orElseThrow(
                ()-> new ObjectNotFoundException(null, " : Id não encontrado !" + id));
    }

    public Produto saveProduto(Produto produto){

       Produto obj = produtoRepository.save(produto);

        String exchange = "produtos.v1.produtos-create";

        ProdutoCreatedEvent produtoCreatedEvent = new ProdutoCreatedEvent(obj.getId(),
                obj.getNome(), obj.getPrice());

        rabbitTemplate.convertAndSend(exchange, "notafiscal", produtoCreatedEvent);

        return obj;
    }

}
