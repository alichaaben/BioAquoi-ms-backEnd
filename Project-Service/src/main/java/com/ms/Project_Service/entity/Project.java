package com.ms.Project_Service.entity;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Long userId;


    // @ManyToOne
    // @JoinColumn(name = "user_id", nullable = false)
    // private Long userId;

    // @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    // private List<Components> components;

    // @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    // private List<Billing> billings;
}
