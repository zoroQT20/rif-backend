package com.rif.backend.Approver;

import com.rif.backend.Auth.User;

import javax.persistence.*;

@Entity
@Table(name = "approvers")
public class Approver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String professionalTitle;

    @Column
    private String postNominalTitle;

    @Lob
    @Column(name = "approver_photo", columnDefinition = "BLOB")
    private byte[] approverPhoto;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Default constructor
    public Approver() {
    }

    // Constructor with all fields
    public Approver(String professionalTitle, String postNominalTitle, byte[] approverPhoto, User user) {
        this.professionalTitle = professionalTitle;
        this.postNominalTitle = postNominalTitle;
        this.approverPhoto = approverPhoto;
        this.user = user;
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

    public byte[] getApproverPhoto() {
        return approverPhoto;
    }

    public void setApproverPhoto(byte[] approverPhoto) {
        this.approverPhoto = approverPhoto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
