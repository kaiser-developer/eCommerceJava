package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.EnderecoDTO;
import br.com.rd.ecommerce.model.entity.Endereco;
import br.com.rd.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EnderecoService")
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public ResponseEntity buscarEnderecos(Long codCliente){
        try {
            List<Endereco> enderecos = repository.findByCodCliente(codCliente);

            if (enderecos != null || enderecos.size() > 0){
                return ResponseEntity.ok().body(enderecos);
            }else{
                return ResponseEntity.ok().body(null);
            }
        }catch (Exception e){
            String erro = "Não foi possivel buscar os endereços";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity cadastrarEndereco(EnderecoDTO enderecoDTO){
        try{
            if(enderecoDTO.getCep() == null){
                return ResponseEntity.ok().body(null);
            }else if(enderecoDTO.getCep() == null || enderecoDTO.getBairro() == null || enderecoDTO.getCidade() == null &&
                    enderecoDTO.getDestinatario() == null || enderecoDTO.getEstado() == null && enderecoDTO.getLogradouro() == null
                    && enderecoDTO.getCodCliente() == null || enderecoDTO.getNumero() == null){
                return ResponseEntity.ok().body(null);
            }else {
                Endereco endereco = new Endereco();
                endereco.setBairro(enderecoDTO.getBairro());
                endereco.setCep(enderecoDTO.getCep());
                endereco.setCidade(enderecoDTO.getCidade());
                endereco.setComplemento(enderecoDTO.getComplemento());
                endereco.setDestinatario(enderecoDTO.getDestinatario());
                endereco.setCodCliente(enderecoDTO.getCodCliente());
                endereco.setNumero(enderecoDTO.getNumero());
                endereco.setLogradouro(enderecoDTO.getLogradouro());
                endereco.setEstado(enderecoDTO.getEstado());
                return ResponseEntity.ok().body(repository.save(endereco));
            }
        } catch (Exception e){
            String erro = "Erro ao cadastrar o endereço";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity buscarEndereco(Long id){
        return repository.findById(id).map(endereco -> ResponseEntity.ok().body(endereco))
                .orElse(ResponseEntity.badRequest().build());
    }

    public Endereco retornarEndereco(Long id){
        return repository.findById(id).orElse(null);
    }
}
