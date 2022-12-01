package com.wavenet.call.center.controller;

import com.wavenet.call.center.A;
import com.wavenet.call.center.Test;
import com.wavenet.call.center.model.Status;
import com.wavenet.call.center.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/")
public class StatusController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class.getName());

    private StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @RequestMapping(value = "/status/add",method = RequestMethod.POST)
    public ResponseEntity<String> addStatus(@RequestBody Status status) {
        try {
            LOGGER.info("Add status /status/add {} {}","/status/add",status);
            if(status!=null){
                Status newStatus = statusRepository.save(status);
                if (newStatus!=null){
                    return new ResponseEntity(HttpStatus.CREATED);
                }
            }else {
                LOGGER.info("status cant be empty {}",status);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("status cant be empty!");
            }
        }catch (Exception e){
            LOGGER.error("status creation failed {}",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        LOGGER.info("status creation failed {}",status);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("status creation failed!");
    }

    @RequestMapping(value = "/byte",method = RequestMethod.POST)
    public Object getA(@RequestBody byte[] arr){
        Object deserialize = SerializationUtils.deserialize(arr);
        B b = (B) deserialize;
        return b;
    }

    @RequestMapping(value = "/tobyte",method = RequestMethod.POST)
    public byte[] getA() throws IOException {
        A a = new A();
        a.setId(1);
        a.setName("Buddhika");


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        byte[] yourBytes;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(a);
            out.flush();
            yourBytes = bos.toByteArray();
            Path path = Paths.get("D:\\aws\\aia-cheque-realisation\\src\\main\\resources\\A.byte");
            Files.write(path, yourBytes);
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return yourBytes;
    }

}
