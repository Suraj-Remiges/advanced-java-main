package com.remiges.adv_java_assignment.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Ranks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rank_desc")
    private String rankDesc;

    @Column(name = "parent_rank_id)                                                                                                                                                             ", length = 50)
    private Integer parentRankId;

    @OneToMany(mappedBy = "rankid")
    @JsonBackReference
    //@JsonIgnoreProperties("rankid")
    private List<Employee> employee;

    public Ranks() {
    }

    public Ranks(Integer id, String rankDesc, Integer parentRankId, List<Employee> employee) {
        this.id = id;
        this.rankDesc = rankDesc;
        this.parentRankId = parentRankId;
        this.employee = employee;
    }

}
