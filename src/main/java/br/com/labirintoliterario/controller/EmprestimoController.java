package br.com.labirintoliterario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.labirintoliterario.dto.EmprestimoRequestDTO;
import br.com.labirintoliterario.dto.EmprestimoRespsonseDTO;
import br.com.labirintoliterario.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/emprestimo")
public class EmprestimoController {

    //Injeção de Dependências--- meio que chamar algum serviço
    @Autowired
    private EmprestimoService service;

    @PostMapping
    //@RequestBody vai transformar o Json em dto
    public ResponseEntity<EmprestimoRespsonseDTO> salvar(@RequestBody EmprestimoRequestDTO dto){

        // cria um objeto pra retornar e atribui o valor response nele pra retornar enviando o requestDTO pra service
        EmprestimoRespsonseDTO empreResponse = service.salvar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(empreResponse);
    }
}
