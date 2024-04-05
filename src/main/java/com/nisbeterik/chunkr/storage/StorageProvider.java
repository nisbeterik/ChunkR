package com.nisbeterik.chunkr.storage;

import java.io.IOException;

public interface StorageProvider {
    void save() throws IOException;

    void load() throws IOException;

    void reset();
}
