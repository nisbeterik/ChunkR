package com.nisbeterik.chunkr.storage;

import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;

public final class FileStorageProvider implements StorageProvider {

    private static final String STORAGE_FOLDER = "storage";

    private static final String LEITNERBOXES_FILE = "leitnerboxes.json";

    private LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();


    FileStorageProvider() {


    }
    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    @Override
    public void reset() {

    }
}
