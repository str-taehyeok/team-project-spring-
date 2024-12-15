package com.app.springpowpow.service;

import com.app.springpowpow.domain.PetDTO;
import com.app.springpowpow.domain.PetVO;
import com.app.springpowpow.repository.PetDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PetServiceImpl implements PetService {

    private final PetDAO petDAO;

    @Override
    public void write(PetVO petVO) {
        petDAO.save(petVO);

    }

    @Override
    public List<PetDTO> getList() {
        return petDAO.findAll();
    }

    @Override
    public Optional<PetDTO> getPet(Long id) {
        return petDAO.findById(id);
    }

    @Override
    public void modify(PetVO petVO) {
        petDAO.update(petVO);
    }

    @Override
    public void remove(Long id) {
        petDAO.delete(id);
    }

    @Override
    public void removeAll(Long memberId) {
        petDAO.deleteAll(memberId);
    }

    @Override
    public void modifyPetColor(PetVO petVO) {

        petDAO.updatePetColor(petVO);
    }
}
