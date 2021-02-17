package service;

import model.Advert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.AdvertRepository;
import repository.CrudRepository;

import java.util.List;

public class AdvertService {
    private final Logger logger = LogManager.getLogger(AdvertService.class);
    private final ValidatorService validatorService = new ValidatorService();

    private final CrudRepository<Advert> advertRepository = new AdvertRepository();

    public Advert save(Advert advert) {
        return (Advert) validatorService.validate(advertRepository.save(advert));
    }

    public Advert update(Advert advert) {
        return advertRepository.update(advert);
    }

    public boolean delete(Long id) {
        return advertRepository.delete(id);
    }

    public Advert findById(Long id) {
        return advertRepository.findById(id);
    }

    public List<Advert> findAll() {
        return advertRepository.findAll();
    }

}
