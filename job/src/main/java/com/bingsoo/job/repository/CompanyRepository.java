package com.bingsoo.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bingsoo.job.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, String> {

}