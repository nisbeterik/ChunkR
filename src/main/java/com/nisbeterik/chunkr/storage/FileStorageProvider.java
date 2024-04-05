package com.nisbeterik.chunkr.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nisbeterik.chunkr.models.LeitnerBox;
import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;
import com.nisbeterik.chunkr.repository.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;

public final class FileStorageProvider implements StorageProvider {

    private static final String STORAGE_FOLDER = "storage";

    private static final String LEITNERBOXES_FILE = "leitnerboxes.json";

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    private final LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();


    FileStorageProvider() {
    }
    @Override
    public void save() throws IOException {
        ensureStorageFilesExist();

        saveRepository(LEITNERBOXES_FILE, leitnerBoxRepository);
    }

    private <T> void saveRepository(String fileName, Repository<T> repository) throws IOException {
        final Path path = Path.of(STORAGE_FOLDER + "/" +fileName);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), repository.getAll());
    }

    @Override
    public void load() throws IOException {
        ensureStorageFilesExist();

        loadLeitnerBoxes(LEITNERBOXES_FILE, leitnerBoxRepository);
    }

    private <T> void loadLeitnerBoxes(String fileName, LeitnerBoxRepository repository) throws IOException {
        final File file = new File(STORAGE_FOLDER + "/" + fileName);
        List<LeitnerBox> leitnerBoxes = objectMapper.readValue(file, new TypeReference<List<LeitnerBox>>() {});

        repository.load(leitnerBoxes);

    }

    private void ensureStorageFilesExist() throws IOException {
        ensureStorageFileExists(LEITNERBOXES_FILE);
    }

    private void ensureStorageFileExists(String file) throws IOException {
        final Path path = Path.of(STORAGE_FOLDER + "/" + file);
        if(Files.notExists(path, LinkOption.NOFOLLOW_LINKS)) {
            Files.createFile(path);

            objectMapper.writeValue(path.toFile(), JsonNodeFactory.instance.arrayNode());
        }
    }

    @Override
    public void reset() {

    }
}
