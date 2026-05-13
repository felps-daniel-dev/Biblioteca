package br.com.labirintoliterario.controller;

import br.com.labirintoliterario.maper.StatusEmprestimo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.labirintoliterario.dto.EmprestimoRequestDTO;
import br.com.labirintoliterario.dto.EmprestimoRespsonseDTO;
import br.com.labirintoliterario.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping ("/emprestimo")
public class EmprestimoController {

    //Injeção de Dependências--- meio que chamar algum serviço
    @Autowired
    private EmprestimoService service;

    @PostMapping
    @Transactional
    //@RequestBody vai transformar o Json em dto
    public ResponseEntity<EmprestimoRespsonseDTO> salvar(@RequestBody EmprestimoRequestDTO dto){

        
        EmprestimoRespsonseDTO empreResponse = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(empreResponse);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id){
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoRespsonseDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EmprestimoRespsonseDTO>> listarPorStatus(@PathVariable StatusEmprestimo status) {
        List<EmprestimoRespsonseDTO> lista = service.listarPorStatus(status);
        return ResponseEntity.ok(lista);
    }

}
