package br.com.fiap.myflights.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.myflights.repository.VooRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.myflights.models.Voo;

@RestController
@RequestMapping("/api/voo")
public class VooController {

    Logger log = LoggerFactory.getLogger(VooController.class);

    @Autowired
    VooRepository repository;
    // private List<Voo> voos = new ArrayList<>();

    @GetMapping
    public List<Voo> showAll() {
        return repository.findAll();
    }
 
    @PostMapping
    public ResponseEntity<Voo> create(@RequestBody Voo voo) {
        log.info("cadastrar voo: " + voo);
        repository.save(voo);
        return ResponseEntity.status(HttpStatus.CREATED).body(voo);
    }

    @GetMapping("{id}")
    public ResponseEntity<Voo> show(@PathVariable Long id) {
        log.info("Buscar voo: " + id);
        var vooEncontrado = repository.findById(id);
        if (vooEncontrado.isEmpty()) return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(vooEncontrado.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Voo> delete(@PathVariable Long id) {
        log.info("apagando voo " + id);
        var vooEncontrado = repository.findById(id);
        if (vooEncontrado.isEmpty()) return ResponseEntity.notFound().build();
        repository.delete(vooEncontrado.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Voo> update(@PathVariable Long id, @RequestBody Voo voo){
        log.info("atualizando voo " + id);
        var vooEncontrado = repository.findById(id);
        if (vooEncontrado.isEmpty()) return ResponseEntity.notFound().build();
        voo.setId(id);
        repository.save(voo);
        return ResponseEntity.ok(voo);
    }

}

/*
 * package br.com.fiap.naofalindo.controllers;
 * import java.util.ArrayList;
 * import java.util.List;
 * import org.slf4j.Logger;
 * import org.slf4j.LoggerFactory;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.web.bind.annotation.DeleteMapping;
 * import org.springframework.web.bind.annotation.GetMapping;
 * import org.springframework.web.bind.annotation.PathVariable;
 * import org.springframework.web.bind.annotation.PostMapping;
 * import org.springframework.web.bind.annotation.PutMapping;
 * import org.springframework.web.bind.annotation.RequestBody;
 * import org.springframework.web.bind.annotation.RestController;
 * import br.com.fiap.naofalindo.models.Despesa;
 * 
 * @RestControllerpublic
 * class DespesaController {
 *     Logger log = LoggerFactory.getLogger(DespesaController.class);
 *     private List<Despesa> despesas = new ArrayList<>();
 * 
 *     @GetMapping("/api/despesas")
 *     public List<Despesa> index(){
 *         return despesas;
 *     }
 * 
 *     @PostMapping("/api/despesa")
 *     public ResponseEntity<Despesa> create(@RequestBody Despesa despesa){
 *         log.info("cadastrando despesa: " + despesa);
 *         despesa.setId(despesas.size() + 1l);
 *         despesas.add(despesa);
 *         return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
 *     }
 * 
 *     @GetMapping("api/despesa/{id}")
 *     public ResponseEntity<Despesa> show(@PathVariable Long id){
 *         log.info("Buscar despesa " + id);
 *         var despesaEncontrada = despesas.stream().filter(d ->
 * d.getId().equals(id)).findFirst();
 *         if(despesaEncontrada.isEmpty()){
 *             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 *        }
 *         return ResponseEntity.ok(despesaEncontrada.get());
 *     }
 * 
 *     @DeleteMapping("/api/despesa/{id}")
 *     public ResponseEntity<Despesa> delete(@PathVariable Long id){
 *         var despesaEncontrada = despesas.stream().filter(d ->
 * d.getId().equals(id)).findFirst();
 *         if(despesaEncontrada.isEmpty()){
 *             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 *        }
 *         despesas.remove(despesaEncontrada.get());
 *         return ResponseEntity.noContent().build();
 *     }
 * 
 *     @PutMapping("/api/despesa/{id}")
 *     public ResponseEntity<Despesa> update(@PathVariable Long id, @RequestBody
 * Despesa despesa){
 *         var despesaEncontrada = despesas.stream().filter(d ->
 * d.getId().equals(id)).findFirst();
 *         if(despesaEncontrada.isEmpty()){
 *             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 *        }
 *         despesas.remove(despesaEncontrada.get());
 *         despesa.setId(id);
 *         despesas.add(despesa);
 *         return ResponseEntity.ok(despesa);
 *     }}
 */