package com.baeldung.ls.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.repository.IProjectRepository;

@Repository
public class ProjectRepositoryImpl implements IProjectRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Optional<Project> findById(Long id) {
        Project item = em.find(Project.class, id);
        return item != null ? Optional.of(item) : Optional.empty();
    }

    @Transactional
    @Override
    public Project save(Project project) {
        em.persist(project);
        return project;
    }

    @Transactional
    @Override
    public List<Project> getAll() {
        TypedQuery<Project> query = em.createQuery("SELECT * FROM Project", Project.class);
        return query.getResultList();
    }

}
