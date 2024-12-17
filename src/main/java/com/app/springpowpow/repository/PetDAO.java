package com.app.springpowpow.repository;

import com.app.springpowpow.domain.PetDTO;
import com.app.springpowpow.domain.PetVO;
import com.app.springpowpow.domain.PostVO;
import com.app.springpowpow.mapper.PetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PetDAO {
    private final PetMapper petMapper;

    public void save(PetVO petVO) {
        petMapper.insert(petVO);
    }
    public List<PetDTO> findAll(Long memberId) {
      return petMapper.selectAll(memberId);
    }
    public Optional<PetDTO> findById(Long id) {
        return petMapper.select(id);
    }
    public void update(PetVO petVO)
    {
        petMapper.update(petVO);
    }
    public void delete(Long id) {
        petMapper.delete(id);
    }
    public void deleteAll(Long memberId){
        petMapper.deleteAll(memberId);
    }
    public void updatePetColor(PetVO petVO){
        petMapper.updateColor(petVO);
    }

}
