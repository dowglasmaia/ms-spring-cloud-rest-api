package org.maia.hrworker.controllers;

import java.util.List;

import org.maia.hrworker.entities.Worker;
import org.maia.hrworker.repositores.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 ** > 3.4 Actuator para atualizar configurações em runtime
	> Atenção: colocar @RefreshScope em toda classe que possua algum acesso às configurações
 **/

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

	
	private static Logger logger = LoggerFactory.getLogger(WorkerController.class);
	
	@Value("${test.config}")
	private String testConfig;

	@Autowired
	private Environment env;

	@Autowired
	private WorkerRepository repository;

	@GetMapping
	public ResponseEntity<List<Worker>> getAll() {
		List<Worker> list = repository.findAll();
		return ResponseEntity.ok(list);
	}
	
	/*TESTANDO ACESSO AS CONFIGURAÇÕES DO SERVE-CONFIG*/
	@GetMapping(value = "/configs")
	public ResponseEntity<Void> getConfigs() {
		
		//logger.info("CONFIG = " +env.getProperty("test.config"));
		
		logger.info("CONFIG = " +testConfig);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {
		
		/* simulando  timeout - por padrão e de  1ms
		
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
		
		/* SIMULANDO UMA EXEÇÃO 
		int x = 1;
		if (x == 1) {
			throw new RuntimeException("Teste Error");
		}
		*/
		
		
		logger.info("#####################################");
		logger.info("PORT = " + env.getProperty("server.port"));
		logger.info("#####################################");

		Worker result = repository.findById(id).get();
		return ResponseEntity.ok(result);
	}
}
