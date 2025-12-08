package com.gharib.banking.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bank extends BaseEntity {

    private String Name;

    private String Address;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Branch> branchs = new HashSet<Branch>();
}
