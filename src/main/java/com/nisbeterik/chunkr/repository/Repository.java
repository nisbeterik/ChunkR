package com.nisbeterik.chunkr.repository;

import java.util.Collection;

public interface Repository<T> {

    void load(Collection<T> collection);

    Collection<T> getAll();

    void clear();

}
