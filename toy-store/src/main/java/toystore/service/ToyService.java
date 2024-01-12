package toystore.service;

import toystore.model.Toy;

public interface ToyService {
    void saveToy(Toy toy);

    void updateToy(Toy toy);

    void deleteToy(Long id);

    Toy findById(Long id);

    Iterable<Toy> findAll();
}
