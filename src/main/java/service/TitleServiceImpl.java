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

    //region Messages
    public static final String NOT_FOUND_MESSAGE = "Ünvan kaydı bulunamadı.";
    public static final String PARAMETERS_MUST_BE_NOT_NULL = "Parametre girilmesi gerekmektedir.";
    public static final String TITLE_NAME_MUST_BE_NOT_NULL = "Ünvan adı girilmesi gerekmektedir.";
    //endregion

    @Autowired
    TitleRepository titleRepository;

    @Override
    public List<TitleListDto> getAll() {
        List<Title> list = titleRepository.findAll();
        return TitleListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public TitleListDto getByID(int titleID) throws Exception {
        Title entity = titleRepository.getOne(titleID);

        if(entity == null) {
            throw new Exception(NOT_FOUND_MESSAGE);
        }

        return TitleListDtoMapper.mapEntityToDto(entity);
    }

    @Override
    public void deleteByID(int titleID) {
        titleRepository.delete(titleID);
    }

    @Override
    public int saveOrUpdate(TitleListDto dto) throws Exception {
        if(dto == null) {
            throw new Exception(PARAMETERS_MUST_BE_NOT_NULL);
        }

        if(dto.getName() == null || dto.getName().isEmpty()) {
            throw new Exception(TITLE_NAME_MUST_BE_NOT_NULL);
        }

        Title entity = TitleEntityMapper.mapDtoToEntity(dto);
        entity = titleRepository.save(entity);

        return entity.getId();
    }

}
