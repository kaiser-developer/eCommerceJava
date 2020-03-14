package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.ClienteDTO;
import br.com.rd.ecommerce.model.entity.Cliente;
import br.com.rd.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("ClienteService")
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ResponseEntity criarCliente(ClienteDTO clienteDTO){
        if(repository.findByEmail(clienteDTO.getEmail()) != null){
            return ResponseEntity.ok().body(1);
        }
        if(repository.findByCpf(clienteDTO.getCpf()) != null){
            return ResponseEntity.ok().body(2);
        }

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSenha(clienteDTO.getSenha());
        cliente.setTelefone(clienteDTO.getTelefone());

        return ResponseEntity.ok().body(repository.save(cliente));
    }
    public ResponseEntity fazerLogin(String email, String senha){
        Cliente cliente = repository.findByEmailAndSenha(email, senha);
        if(cliente != null){
            return ResponseEntity.ok().body(cliente);
        }else{
            return ResponseEntity.ok().body(null);
        }
    }
}
