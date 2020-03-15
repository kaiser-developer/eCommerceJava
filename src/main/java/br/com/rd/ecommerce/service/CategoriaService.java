package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.CategoriaDTO;
import br.com.rd.ecommerce.model.entity.Categoria;
import br.com.rd.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("CategoriaService")
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public ResponseEntity salvarCategoria(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria();
        categoria.setDescricao(categoriaDTO.getDescCategoria());
        return ResponseEntity.ok().body(repository.save(categoria));
    }

    public ResponseEntity buscarCategoria(){
        return ResponseEntity.ok().body(repository.findAll());
    }
}
