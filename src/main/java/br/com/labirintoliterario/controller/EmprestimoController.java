package br.com.labirintoliterario.controller;

import br.com.labirintoliterario.entity.Emprestimo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.labirintoliterario.dto.EmprestimoRequestDTO;
import br.com.labirintoliterario.dto.EmprestimoRespsonseDTO;
import br.com.labirintoliterario.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id){
        service.remover(id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<EmprestimoRespsonseDTO> listar(){
        return service.listar();
    }

}
