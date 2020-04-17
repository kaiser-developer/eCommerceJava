package br.com.rd.ecommerce.service;


import br.com.rd.ecommerce.model.dto.FuncionarioDTO;
import br.com.rd.ecommerce.model.entity.Funcionario;
import br.com.rd.ecommerce.repository.FuncionarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("FuncionarioService")
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public ResponseEntity cadastrarFuncionario(FuncionarioDTO funcionarioDTO){
        try {
            if(repository.findByMatricula(funcionarioDTO.getMatricula()) != null){
                String mensagem = "Usuario já cadastrado";
                return ResponseEntity.ok().body(mensagem);
            }else{
                Funcionario funcionario = new Funcionario();

                funcionario.setMatricula(funcionarioDTO.getMatricula());
                funcionario.setNome(funcionarioDTO.getNome());
                funcionario.setAdmin(funcionarioDTO.getAdmin());
                String senha = BCrypt.hashpw(funcionarioDTO.getSenha(), BCrypt.gensalt());
                funcionario.setSenha(senha);

                return ResponseEntity.ok().body(repository.save(funcionario));
            }
        }catch (Exception e){
            String erro = "Erro ao cadastrar funcionario";
            return ResponseEntity.badRequest().body(erro);
        }
    }

    public ResponseEntity fazerLogin(String matricula, String senha){
        try {
            Funcionario funcionario = repository.findByMatricula(matricula);
            if(funcionario!=null && BCrypt.checkpw(senha, funcionario.getSenha())){
                FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario.getMatricula(),funcionario.getNome(),null,null);
                return ResponseEntity.ok().body(funcionarioDTO);
            }else{
                String mensagem = "Matricula/Senha incorretos";
                return ResponseEntity.ok().body(mensagem);
            }
        }catch (Exception e){
            String erro = "Erro ao efetuar login";
            return ResponseEntity.badRequest().body(erro);
        }
    }

}
