package com.nisbeterik.chunkr.storage;

import java.io.IOException;

public interface StorageProvider {
    void save();

    void load();

    void reset();
}
