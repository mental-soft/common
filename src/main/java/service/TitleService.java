package service;

import dto.TitleListDto;

import java.util.List;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public interface TitleService {
    long titleCount();

    List<TitleListDto> getAll();

    TitleListDto getByID(int titleID);

    void deleteByID(int titleID);

    void saveOrUpdate(TitleListDto dto);
}
