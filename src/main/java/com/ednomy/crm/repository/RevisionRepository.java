package com.ednomy.crm.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

public interface RevisionRepository<T, ID extends Serializable, N extends Number & Comparable<N>> {

    Revision<N, T> findLastChangeRevision(ID id);

    Revisions<N, T> findRevisions(ID id);

    Page<Revision<N, T>> findRevisions(ID id, Pageable pageable);
}