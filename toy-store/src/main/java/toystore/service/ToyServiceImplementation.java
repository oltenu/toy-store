package toystore.service;

import org.springframework.stereotype.Service;
import toystore.data.ToyRepository;
import toystore.model.Toy;

import java.util.Optional;

@Service
public class ToyServiceImplementation implements ToyService{

    ToyRepository toyRepository;

    public ToyServiceImplementation(ToyRepository toyRepository){
        this.toyRepository = toyRepository;
    }

    @Override
    public void saveToy(Toy toy) {
        toyRepository.save(toy);
    }

    @Override
    public void updateToy(Toy toy) {
        toyRepository.save(toy);
    }

    @Override
    public void deleteToy(Long id) {
        toyRepository.deleteById(id);
    }

    @Override
    public Toy findById(Long id) {
        Optional<Toy> optionalToy = toyRepository.findById(id);

        return optionalToy.orElse(null);

    }

    @Override
    public Iterable<Toy> findAll() {
        return toyRepository.findAll();
    }
}
