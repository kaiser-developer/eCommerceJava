package br.com.rd.ecommerce.service;

import br.com.rd.ecommerce.controller.EmailController;
import br.com.rd.ecommerce.model.dto.ClienteDTO;
import br.com.rd.ecommerce.model.entity.Cliente;
import br.com.rd.ecommerce.model.entity.Status;
import br.com.rd.ecommerce.repository.ClienteRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service("ClienteService")
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EmailController emailController;

    @PersistenceContext
    private EntityManager entityManager;

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
            cliente = repository.save(cliente);

            ClienteDTO clienteDTOcriado = new ClienteDTO(cliente.getCodCliente(), cliente.getNome(), cliente.getCpf(),
                    cliente.getEmail(), null, cliente.getTelefone(), cliente.getSexo());
            return ResponseEntity.ok().body(clienteDTOcriado);
        }catch (Exception e){
            String erro = "Não foi possivel cadastrar o usuario!";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity fazerLogin(String email, String senha) {
        try {
            Cliente cliente = repository.findByEmail(email);
            if (cliente != null && BCrypt.checkpw(senha, cliente.getSenha())){
                ClienteDTO clienteDTO = new ClienteDTO(cliente.getCodCliente(), cliente.getNome(), cliente.getCpf(),
                cliente.getEmail(), null, cliente.getTelefone(), cliente.getSexo());
                return ResponseEntity.ok().body(clienteDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            String erro = "Não foi possivel autenticar tente novamente";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity recuperarSenha(String email){
        try {
            Cliente cliente = repository.findByEmail(email);
            if(cliente != null){
                String codigo = RandomStringUtils.randomAlphanumeric(8);
                cliente.setCodRecuperarSenha(codigo);
                repository.save(cliente);
                emailController.emailRecuperacaoSenha(cliente);
                apagarCodRecuperarSenha(cliente);
                return ResponseEntity.ok().body(true);
            }else {
                return ResponseEntity.ok().body(false);
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Async
    public void apagarCodRecuperarSenha(Cliente cliente){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(600000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                cliente.setCodRecuperarSenha(null);
                repository.save(cliente);
            }
        }).start();
    }

    public ResponseEntity redefinirSenha(String[] dados){
        try {
            Cliente cliente = repository.findByEmail(dados[0]);
            if (cliente.getCodRecuperarSenha().equals(dados[1])) {
                cliente.setSenha(BCrypt.hashpw(dados[2], BCrypt.gensalt()));
                cliente.setCodRecuperarSenha(null);
                return ResponseEntity.ok().body(repository.save(cliente));
            } else {
                return null;
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public Cliente retonarCliente(Long codCliente){
        return repository.findById(codCliente).orElse(null);
    }
}
