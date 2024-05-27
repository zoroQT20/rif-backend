package com.rif.backend.Approver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/approvers")
public class ApproverController {

    @Autowired
    private ApproverService service;

    @PostMapping("/upload")
    public ResponseEntity<Approver> uploadApprover(
            @RequestParam("professionalTitle") String professionalTitle,
            @RequestParam("postNominalTitle") String postNominalTitle,
            @RequestParam(value = "file", required = false) MultipartFile file,
            Principal principal) throws Exception {

        Approver approver = new Approver();
        approver.setProfessionalTitle(professionalTitle);
        approver.setPostNominalTitle(postNominalTitle);
        if (file != null) {
            approver.setApproverPhoto(file.getBytes());
        }

        Approver savedApprover = service.saveOrUpdateApprover(approver, principal.getName());
        return ResponseEntity.ok(savedApprover);
    }

    @GetMapping
    public ResponseEntity<Approver> getApprover(Principal principal) {
        Approver approver = service.getApproverByEmail(principal.getName());
        if (approver == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(approver);
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getApproverImage(Principal principal) {
        Approver approver = service.getApproverByEmail(principal.getName());
        if (approver == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(approver.getApproverPhoto());
    }
}
