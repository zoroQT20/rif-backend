package com.rif.backend.Esignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/esignatures")
public class ESignatureController {

    @Autowired
    private ESignatureService service;

    @PostMapping("/upload")
    public ResponseEntity<ESignature> uploadESignature(
        @RequestParam("professionalTitle") String professionalTitle,
        @RequestParam("postNominalTitle") String postNominalTitle,
        @RequestParam("file") MultipartFile file) throws Exception {
        
        ESignature eSignature = new ESignature();
        eSignature.setProfessionalTitle(professionalTitle);
        eSignature.setPostNominalTitle(postNominalTitle);
        eSignature.setESignaturePhoto(file.getBytes());
        
        ESignature savedESignature = service.saveESignature(eSignature);
        return ResponseEntity.ok(savedESignature);
    }
}
