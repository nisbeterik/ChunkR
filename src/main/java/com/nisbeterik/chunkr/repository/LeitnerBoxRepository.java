package com.nisbeterik.chunkr.repository;

import com.nisbeterik.chunkr.models.Chunk;
import com.nisbeterik.chunkr.models.LeitnerBox;

import java.util.*;

import static java.util.Objects.requireNonNull;

public class LeitnerBoxRepository implements Repository<LeitnerBox> {


    private final Set<LeitnerBox> boxes = new HashSet<>();
    protected LeitnerBoxRepository() {
    }

    public LeitnerBox createLeitnerBox(String name) {
        final LeitnerBox box = new LeitnerBox(name);
        boxes.add(box);
        return box;
    }

    public LeitnerBox getLeitnerBoxByName(String name) {
        for (LeitnerBox box : boxes) {
            System.out.println("Checking box: " + box.getName());
            if (box.getName().equals(name)) {
                return box;
            }
        }
        return null;
    }

    public void addChunkToBox(Chunk chunk, String name) {
        boxes.stream()
                .filter(leitnerBox -> leitnerBox.getBOX_ID().equals(UUID.fromString(name)))
                .findAny()
                .ifPresent(leitnerBox ->
                {
                    leitnerBox.addChunk(chunk);
                });
    }



    @Override
    public void load(Collection<LeitnerBox> leitnerBoxes) {
        requireNonNull(leitnerBoxes);

        this.boxes.addAll(leitnerBoxes);
    }

    @Override
    public Set<LeitnerBox> getAll() {
        return Collections.unmodifiableSet(this.boxes);
    }

    @Override
    public void clear() {

    }
}
