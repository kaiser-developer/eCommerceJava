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
        try{
            Categoria categoria = new Categoria();
            categoria.setDescricao(categoriaDTO.getDescricao());
            return ResponseEntity.ok().body(repository.save(categoria));
        }catch (Exception e){
            String erro = "Categoria ja existente";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarCategoria() {

        try {
            return ResponseEntity.ok().body(repository.findAll());
        } catch (Exception e) {
            String erro = "Categoria nao existe";
            return ResponseEntity.badRequest().body(erro);
        }

    }
}
