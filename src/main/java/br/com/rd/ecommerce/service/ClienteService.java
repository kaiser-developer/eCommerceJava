package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.model.dto.ClienteDTO;
import br.com.rd.ecommerce.model.entity.Cliente;
import br.com.rd.ecommerce.repository.ClienteRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("ClienteService")
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ResponseEntity criarCliente(ClienteDTO clienteDTO) {
        try {
            if (repository.findByEmail(clienteDTO.getEmail()) != null) {
                return ResponseEntity.ok().body(1);
            }
            if (repository.findByCpf(clienteDTO.getCpf()) != null) {
                return ResponseEntity.ok().body(2);
            }

            String senha = BCrypt.hashpw(clienteDTO.getSenha(), BCrypt.gensalt());

            Cliente cliente = new Cliente();
            cliente.setNome(clienteDTO.getNome());
            cliente.setCpf(clienteDTO.getCpf());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setSenha(senha);
            cliente.setTelefone(clienteDTO.getTelefone());
            cliente.setSexo(clienteDTO.getSexo());

            return ResponseEntity.ok().body(repository.save(cliente));
        }catch (Exception e){
            String erro = "Não foi possivel cadastrar o usuario!";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity fazerLogin(String email, String senha) {
        try {
            Cliente cliente = repository.findByEmail(email);
            if (cliente != null && BCrypt.checkpw(senha, cliente.getSenha())){
                return ResponseEntity.ok().body(cliente);
            } else {
                return ResponseEntity.ok().body("Email ou/e senha incorretos");
            }
        } catch (Exception e){
            String erro = "Não foi possivel autenticar tente novamente";
            return ResponseEntity.badRequest().body(erro);
        }
    }
}
