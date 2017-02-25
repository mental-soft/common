package service;

import dto.TitleListDto;
import entity.Title;
import jpa.TitleRepository;
import mapper.dto.TitleListDtoMapper;
import mapper.entity.TitleEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nyomoto on 18.2.2017.
 */
@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    TitleRepository titleRepository;

    @Override
    public long titleCount() {
        return titleRepository.count();
    }

    @Override
    public List<TitleListDto> getAll() {
        List<Title> list = titleRepository.findAll();
        return TitleListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public TitleListDto getByID(int titleID) {
        Title entity = titleRepository.getOne(titleID);
        return TitleListDtoMapper.mapEntityToDto(entity);
    }

    @Override
    public void deleteByID(int titleID) {
        titleRepository.delete(titleID);
    }

    @Override
    public void saveOrUpdate(TitleListDto dto) {
        Title entity = TitleEntityMapper.mapDtoToEntity(dto);
        titleRepository.save(entity);
    }

}
