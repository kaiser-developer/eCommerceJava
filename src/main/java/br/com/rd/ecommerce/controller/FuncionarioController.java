package br.com.rd.ecommerce.controller;


import br.com.rd.ecommerce.model.dto.FuncionarioDTO;
import br.com.rd.ecommerce.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuncionarioController {
    @Autowired
    FuncionarioService service;

    @PostMapping("cadastrar-funcionario")
    public ResponseEntity cadastrarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) { return service.cadastrarFuncionario(funcionarioDTO); }

    @PostMapping("login-funcionario")
    public ResponseEntity realizarLogin(@RequestBody String[] dados) { return service.fazerLogin(dados[0],dados[1]); }
}
