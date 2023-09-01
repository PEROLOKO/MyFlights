package br.com.fiap.myflights.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.myflights.repository.VooRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.myflights.models.Voo;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/myflights/api/voo")
public class VooController {

    Logger log = LoggerFactory.getLogger(VooController.class);

    @Autowired
    VooRepository repository;
    // private List<Voo> voos = new ArrayList<>();

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    @Operation(
            summary = "Listar voos",
            description = "Retorna todos os voos cadastrados."
    )
    //public List<Voo> index() { return repository.findAll(); }

    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable) {
        Page<Voo> voos = (busca == null) ?
                repository.findAll(pageable):
                repository.findByDestinoContaining(busca, pageable);

        return assembler.toModel(voos.map(Voo::toEntityModel));
    }

 
    @PostMapping
    @Operation(
            summary = "Cadastrar voo",
            description = "Cadastra um novo voo."
    )
    public ResponseEntity<Object> create(@RequestBody @Valid Voo voo) {
        log.info("cadastrar voo: " + voo);
        repository.save(voo);
        return ResponseEntity
                .created(voo.toEntityModel().getRequiredLink("self").toUri())
                .body(voo.toEntityModel());
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Detalhar voo",
            description = "Detalha um voo específico por ID."
    )
    public EntityModel<Voo> show(@PathVariable Long id) {
        log.info("Buscar voo: " + id);
        var voo = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "voo não encontrado"));
        return voo.toEntityModel();
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Deletar voo",
            description = "Deleta um voo específico por ID."
    )
    public ResponseEntity<Voo> delete(@PathVariable Long id) {
        log.info("apagando voo " + id);
        var vooEncontrado = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "voo não encontrado"));
        repository.delete(vooEncontrado);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Editar voo",
            description = "Edita um voo específico por ID."
    )
    public EntityModel<Voo> update(@PathVariable Long id, @RequestBody Voo voo){
        log.info("atualizando voo " + id);
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "voo não encontrado"));
        voo.setId(id);
        repository.save(voo);
        return voo.toEntityModel();
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