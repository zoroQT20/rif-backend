package com.rif.backend.Esignature;

import javax.persistence.*;

@Entity
@Table(name = "eSignatures")
public class ESignature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String professionalTitle;

    @Column
    private String postNominalTitle;

    @Lob
    @Column(name = "e_signature_photo")
    private byte[] eSignaturePhoto;

    // Default constructor
    public ESignature() {
    }

    // Constructor with all fields
    public ESignature(String professionalTitle, String postNominalTitle, byte[] eSignaturePhoto) {
        this.professionalTitle = professionalTitle;
        this.postNominalTitle = postNominalTitle;
        this.eSignaturePhoto = eSignaturePhoto;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getPostNominalTitle() {
        return postNominalTitle;
    }

    public void setPostNominalTitle(String postNominalTitle) {
        this.postNominalTitle = postNominalTitle;
    }

    public byte[] getESignaturePhoto() {
        return eSignaturePhoto;
    }

    public void setESignaturePhoto(byte[] eSignaturePhoto) {
        this.eSignaturePhoto = eSignaturePhoto;
    }
}
